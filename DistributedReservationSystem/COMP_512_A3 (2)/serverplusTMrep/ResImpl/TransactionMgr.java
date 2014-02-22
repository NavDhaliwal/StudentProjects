package ResImpl;

import LockManager.DeadlockException;
import LockManager.InvalidTransactionException;
import LockManager.LockManager;
import LockManager.TransactionAbortedException;
import ResInterface.CarInterface;
import ResInterface.FlightInterface;
import ResInterface.HotelInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionMgr
{

	/**
	 * @param args
	 */
	public static int LatestTransactionID=0;
	public static final int NumTransactions=1000;//Max num of trxns allowed
	//private ConcurrentHashMap<Integer,Boolean> TrxnIDList;//maintains the transactions IDs
	public static final int READ = LockManager.READ;
    public static final int WRITE = LockManager.WRITE;
	static FlightInterface rmFlight = null;
	static CarInterface rmCar = null;
	static HotelInterface rmHotel = null;
	private ConcurrentHashMap<Integer,ArrayList<String>> TxnRsMgrMap;//maintains the RMs which the trxn is communicating
	ConcurrentHashMap<Integer,Long> TxnTimestampMap;//For timeout of trxns
    private LockManager lm;
    public MiddlewareServer mdServer;
    TransactionMgr()
    {
    	lm = new LockManager();
    	//TrxnIDList = new ConcurrentHashMap<Integer, Boolean>();
    	/*for(int i=1;i<=NumTransactions;i++)
    	{
    		TrxnIDList.put(i, false);
    	}*/
    	TxnRsMgrMap = new ConcurrentHashMap<Integer, ArrayList<String>>();
    	TxnTimestampMap = new ConcurrentHashMap<Integer, Long>();
    }
    public int start() throws RemoteException
	{
		//int txID=-1;
		/*for(int i=1;i<=NumTransactions;i++)
    	{
			if(TrxnIDList.get(i)==false)
			{
				txID=i;
				TrxnIDList.put(i, true);
				break;
			}
    	}*/
		//if(txID==-1)
			//return txID;
    	LatestTransactionID++;
    	int txID=LatestTransactionID;
		TxnRsMgrMap.put(txID,new ArrayList<String>());
		Date d = new Date();
		long currtime=d.getTime();
		TxnTimestampMap.put(txID, currtime);
		return txID;
	}
	boolean commit(int txID) throws RemoteException,TransactionAbortedException,InvalidTransactionException
	{
		if(TxnTimestampMap.get(txID)==null)
			throw new InvalidTransactionException(txID,"Please restart the transaction");
		int size=0;
		String[] txnsStr=null;
		if(TxnRsMgrMap.get(txID)!=null)
		{
			txnsStr = TxnRsMgrMap.get(txID).toArray(new String[0]);
			size=txnsStr.length;
		}
		
		for(int i=0;i<size;i++)
		{
			switch(txnsStr[i])
			{
			case "flight":
				while(true)
				{
					try
					{
						if(rmFlight!=null)
						{
							rmFlight.commit(txID);
							break;
						}
					}
					catch(RemoteException e)
					{
						mdServer.findPrimaryFlightRM();
						rmFlight=mdServer.rmFlight;
					}
				}
				break;
			case "car":
				while(true)
				{
					try
					{
						if(rmCar!=null)
						{
							rmCar.commit(txID);
							break;
						}
					}
					catch(RemoteException e)
					{
						mdServer.findPrimaryCarRM();
						rmCar=mdServer.rmCar;
					}
				}
				break;
			case "hotel":
				while(true)
				{
					try
					{
						if(rmHotel!=null)
						{
							rmHotel.commit(txID);
							break;
						}
					}
					catch(RemoteException e)
					{
						mdServer.findPrimaryHotelRM();
						rmHotel=mdServer.rmHotel;
					}
				}
				break;
			}

		}
		TxnRsMgrMap.remove(txID);
		TxnTimestampMap.remove(txID);
		//TrxnIDList.put(txID, false);
		//synchronized(lm)
		{
			lm.UnlockAll(txID);
		}
		return true;
	}
	
	
	void abortFromTimeout(int txID) throws RemoteException,InvalidTransactionException, TransactionAbortedException
	{
		mdServer.abort(txID);
		throw new InvalidTransactionException(txID,"Transaction Timedout");
	}
	
	
	void abortFromMW(int txID) throws RemoteException,InvalidTransactionException
	{
		if(TxnTimestampMap.get(txID)==null)
			throw new InvalidTransactionException(txID,"Please restart the transaction");
		String[] txnsStr = TxnRsMgrMap.get(txID).toArray(new String[0]);
		int size=txnsStr.length;
		
		for(int i=0;i<size;i++)
		{
			switch(txnsStr[i])
			{
			case "flight":
				while(true)
				{
					try
					{
						if(rmFlight!=null)
						{
							rmFlight.abort(txID);
							break;
						}
					}
					catch(RemoteException e)
					{
						mdServer.findPrimaryFlightRM();
						rmFlight=mdServer.rmFlight;
					}
				}
				break;
			case "car":
				while(true)
				{
					try
					{
						if(rmCar!=null)
						{
							rmCar.abort(txID);
							break;
						}
					}
					catch(RemoteException e)
					{
						mdServer.findPrimaryCarRM();
						rmCar=mdServer.rmCar;
					}
				}
				break;
			case "hotel":
				while(true)
				{
					try
					{
						if(rmHotel!=null)
						{
							rmHotel.abort(txID);
							break;
						}
					}
					catch(RemoteException e)
					{
						mdServer.findPrimaryHotelRM();
						rmHotel=mdServer.rmHotel;
					}
				}
				break;
			}
		}
		TxnRsMgrMap.remove(txID);
		TxnTimestampMap.remove(txID);
		//TrxnIDList.put(txID, false);
		//synchronized(lm)
		{
			lm.UnlockAll(txID);
		}
	}
	//param1=flightNum for flight RM
	//param1=locations for Hotel and Car RM
	public boolean Lock(int txID, String strData, String param1, int lockType) throws RemoteException,TransactionAbortedException,InvalidTransactionException
	{
		boolean found=false;
		ArrayList<String> ListStr=TxnRsMgrMap.get(txID);
		if(ListStr==null)//Transaction was not started or was aborted
		{
			if(mdServer.isPrimary())
			{
				throw new InvalidTransactionException(txID,"Please restart the transaction");
			}
			else
			{
				TxnRsMgrMap.put(txID,new ArrayList<String>());
			}
		}
		String[] txnsStr = ListStr.toArray(new String[0]);
		int size=txnsStr.length;
		for(int i=0;i<size;i++)
		{
			if(txnsStr[i].equals(strData))
				found=true;
		}
		if(!found)
		{
			TxnRsMgrMap.get(txID).add(strData);
		}
		strData=strData+param1;
		Date d = new Date();
		long currtime=d.getTime();
		TxnTimestampMap.put(txID, currtime);
		try
		{
			//synchronized(lm)
			{
				return lm.Lock(txID, strData, lockType);
			}
		}
		catch(DeadlockException e)
		{
			//abortFromMW(txID);
			mdServer.abort(txID);
			throw new TransactionAbortedException(txID,"Please restart the transaction");
		}
	}

}

class TimeoutThread extends Thread {

	TransactionMgr tm;
	boolean kill;
	long timeoutVal=60000*10;//30 sec
    public TimeoutThread (TransactionMgr tm) {
    	this.tm=tm;
    	kill=false;
    }

    public void run () 
    {
    	ArrayList<Integer> ListtxIDDel = new ArrayList<Integer>();
    	while(!kill)
    	{
    		Integer[] key_Set=tm.TxnTimestampMap.keySet().toArray(new Integer[0]);
    		int size=key_Set.length;
    		if(size>0)
    		{
    			//System.out.println("Timeout Thread checking transactions.");
	    		Date d = new Date();
	    		long currtime=d.getTime();
	    		for(int i=0;i<size;i++)
	    		{
	    			if(currtime-tm.TxnTimestampMap.get(key_Set[i])>timeoutVal)
	    			{
	    				ListtxIDDel.add(key_Set[i]);
                        
	    			}
	    		}
	    		for(int i=0;i<ListtxIDDel.size();i++)
	    		{
	    			try {
    				    tm.abortFromTimeout(ListtxIDDel.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
	    			//tm.TxnTimestampMap.remove(ListtxIDDel.get(i));
	    		}
	    		ListtxIDDel.clear();
    		}
	    	try 
	    	{
	    		TimeoutThread.sleep (2000);
		    }
		    catch (InterruptedException e) 
		    { 
		    }
    	}
    }
}