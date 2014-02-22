/*
 * Author: Navjot Singh
 */
package ResImpl;

//import ResImpl.TransGenericResourceManager.Tuple3;
import ResInterface.CarInterface;
import ResInterface.FlightInterface;
import LockManager.InvalidTransactionException;
import LockManager.TransactionAbortedException;
import ResInterface.HotelInterface;
import ResInterface.ResourceManager;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

//import javax.sql.rowset.spi.SyncResolver;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
//import org.jgroups.util.Util;

import biz.source_code.base64Coder.Base64Coder;

//import java.util.Vector;


public class MiddlewareServer extends ReceiverAdapter implements ResourceManager 
{
	public int crashAt=-1;
	//1 for crash before executing request at middleware
	//2 for crash after executing and before sending the response
	//3 for crash after executing at middleware but before sending to RMs.
	//4 for crash after executing at primary middleware but before sending to secondary middlewares.
	
	boolean m_bsyncdone=false;
	final static int SYNCTIMEOUT=10;//waits for 10 secs to syncronize. 
	/** Read the object from Base64 string. */
    private static Object fromString( String s ) throws IOException ,ClassNotFoundException 
    {
        byte [] data = Base64Coder.decode( s );
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    private static String toString(Serializable o) throws IOException 
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return new String( Base64Coder.encode( baos.toByteArray() ) );
    }
    
    
	JChannel channel;
	String user_name=System.getProperty("user.name", "n/a");
	private void connectToGroup() throws Exception 
	{

		channel=new JChannel(); // use the default config, udp.xml
		channel.setReceiver(this);
		channel.connect("MiddlewareCluster28");
		channel.setDiscardOwnMessages(true);
		channel.getProtocolStack().setLevel("fatal");
		//channel.getState(channel.getView().getMembers().get(0), 0);
		
		
		//TODO: changed sending syncronize request to SyncChecker thread
		//sendCommand("syncronize");
	}

	public void viewAccepted(View new_view) 
	{
		System.out.println("#####View changed. New View: " + new_view);
	}


	public void receive(Message msg) 
	{
		//check that this code is only executed if this is not primary MiddleWare.
		try
		{
			System.out.println("Request recieved from "+msg.getSrc().toString());
			if(msg.getSrc().equals(channel.getAddress()))
				return;
			String command=(String)msg.getObject();
			String[] result = command.split(",");
			System.out.println(result[0]+" request recieved");
			switch(result[0])
			{
			case "start":
				start();
				break;
			case "abort":
				abort(Integer.parseInt(result[1]));
				break;
			case "commit":
				commit(Integer.parseInt(result[1]));
				break;
			case "writeData":
				int trxnID=Integer.parseInt(result[1]);
				int operationID=Integer.parseInt(result[2]);
				String key = result[3];
				String valueStr=null;
				RMItem value=null;
				//The object in string form might get tokenized. So rejoining.
				if(result.length>5)
				{
					System.out.println("\n\n**************Special case****************\n\n");
					valueStr=result[4];
					for(int i=5;i<result.length;i++)
					{
						valueStr=valueStr+","+result[i];
					}
					value = (RMItem)fromString(valueStr);
				}
				else if(result.length==5)
				{
					value = (RMItem)fromString(result[4]);
				}
				writeData(trxnID, operationID, key, value);
				break;
			case "removeData":
				trxnID=Integer.parseInt(result[1]);
				operationID=Integer.parseInt(result[2]);
				key = result[3];
				removeData(trxnID, operationID, key);
				break;
			case "Lock":
				trxnID=Integer.parseInt(result[1]);
				Lock(trxnID, result[2], result[3], Integer.parseInt(result[4]));
				break;
			case "syncronize":
				sendSyncMessageBack(msg.getSrc());
				break;
				
			case "syncCustomer":
				
				key = result[1];
				valueStr=null;
				value=null;
				//The object in string form might get tokenized. So rejoining.
				if(result.length>3)
				{
					System.out.println("\n\n**************Special case****************\n\n");
					valueStr=result[2];
					for(int i=3;i<result.length;i++)
					{
						valueStr=valueStr+","+result[i];
					}
					value = (RMItem)fromString(valueStr);
				}
				else if(result.length==3)
				{
					value = (RMItem)fromString(result[2]);
				}
				syncCustomer(key, value);
				break;
				
			case "syncOperationID":
				trxnID = Integer.parseInt(result[1]);
				int opID = Integer.parseInt(result[2]);
				valueStr=null;
				Object value1=null;
				//The object in string form might get tokenized. So rejoining.
				if(result.length>4)
				{
					System.out.println("\n\n**************Special case****************\n\n");
					valueStr=result[3];
					for(int i=4;i<result.length;i++)
					{
						valueStr=valueStr+","+result[i];
					}
					value1 = fromString(valueStr);
				}
				else if(result.length==4)
				{
					value1 = fromString(result[3]);
				}
				syncOperationID(trxnID,opID, value1);
				break;
			case "syncLock":
				trxnID = Integer.parseInt(result[1]);
				valueStr=null;
				LockGroup value2=null;
				//The object in string form might get tokenized. So rejoining.
				value2=new LockGroup(result[2], result[3], Integer.parseInt(result[4]));
				syncLock(trxnID,value2);
				break;
			case "syncLatestTransactionID":
				syncLatestTransactionID(Integer.parseInt(result[1]));
				break;
			case "syncdone":
				m_bsyncdone=true;
				break;
			}
		}
		catch(Exception e)
		{
		}
	}
private void sendSyncMessageBack(Address src)
	{
	System.out.println("In sendSyncMessageBack");
	String valueStr=null;
		String command=null;
		command="syncLatestTransactionID,"+TransactionMgr.LatestTransactionID;
		Message msg=new Message(src, null, command);
		try
		{
			channel.send(msg);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		synchronized (m_customerHT)
		{
			RMItem value=null;
			
			for (Enumeration e = m_customerHT.keys(); e.hasMoreElements();)
			{
				String key = (String) (e.nextElement());
				value=(RMItem)m_customerHT.get(key);
				try
				{
					valueStr=toString(value);
				} catch (IOException e2)
				{
					e2.printStackTrace();
				}
				command="syncCustomer,"+key+","+valueStr;
				System.out.println("\n\n"+channel.getAddressAsString()+" sending customer info to "+src.toString());
				System.out.println(command+"\n\n");
				msg=new Message(src, null, command);
				try
				{
					channel.send(msg);
				} 
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
				
		}
		synchronized (operationIDList)
		{
			System.out.println("sending operationIDList having size "+operationIDList.size()+"\n\n");
			for (Enumeration e = operationIDList.keys(); e.hasMoreElements();)
			{
				System.out.println("\n\n\n\nHere opID1\n\n\n\n");
				int txID=(int)e.nextElement();
				HashMap<Integer,Object> list=operationIDList.get(txID);
				for (Enumeration e1 = Collections.enumeration(list.keySet()); e1.hasMoreElements();)
				{
					System.out.println("\n\n\n\nHere opID2\n\n\n\n");
					int opID=(int)e1.nextElement();
					Object retObj=list.get(opID);
					try
					{
						valueStr=toString((Serializable)retObj);
					} catch (IOException e2)
					{
						e2.printStackTrace();
					}
					command="syncOperationID,"+txID+","+opID+","+valueStr;
					System.out.println("\n\n"+channel.getAddressAsString()+" sending operationID info to "+src.toString());
					System.out.println(command+"\n\n");
					msg=new Message(src, null, command);
					try
					{
						channel.send(msg);
					} 
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		synchronized (LockList)
		{
			System.out.println("sending LockList having size "+LockList.size()+"\n\n");
			for (Enumeration e = LockList.keys(); e.hasMoreElements();)
			{
				System.out.println("\n\n\n\nHere locklist\n\n\n\n");
				int txID=(int)e.nextElement();
				ArrayList<LockGroup> lgList=LockList.get(txID);
				for(int i=0;i<lgList.size();i++)
				{
					LockGroup lg=lgList.get(i);
					command="syncLock,"+txID+","+lg.strData+","+lg.param+","+lg.lockType;
					System.out.println("\n\n"+channel.getAddressAsString()+" sending LockList info to "+src.toString());
					System.out.println(command+"\n\n");
					msg=new Message(src, null, command);
					try
					{
						channel.send(msg);
					} 
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		//send sync done to src
		command="syncdone";
		msg=new Message(src, null, command);
		try
		{
			channel.send(msg);
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	
	private void sendCommand(String command)
	{
		try
		{
			if(!isPrimary())
			{
				if(command.equals("syncronize"))
				{
					System.out.println("\n\nSending syncronize request to primary"+channel.getView().getMembers().get(0).toString());
					Message msg=new Message(channel.getView().getMembers().get(0), null, command);
					channel.send(msg);
				}
				else
				{
					//commandList.get(txID).add(command);
				}
				return;
			}
			//commandList.get(txID).add(command);
			Message msg=new Message(null, null, command);
			channel.send(msg);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception while sending message to middleware group."+e.getMessage());
		}
	}
	@Override
	public boolean isPrimary() throws RemoteException
	{
		boolean bPrimary=false;
		Address adr1=channel.getAddress();
		Address adr2=channel.getView().getMembers().get(0);
		if(adr1.equals(adr2))
		{
			System.out.println("Primary found"+ adr1.toString());
			bPrimary=true;
		}
		return bPrimary;
	}
	class ServerDescriptorFlight {
        String serveraddr;
        int serverport;
        String servername;
        public ServerDescriptorFlight(String sa, int sp, String sn) {
            serveraddr = sa;
            serverport = sp;
            servername = sn;
        }
        //boolean livemark = true;
        FlightInterface rmobj=null;

        @Override
        public String toString() {
            return serveraddr + ":" + serverport + ":" + servername;
        }
    }
	class ServerDescriptorCar{
        String serveraddr;
        int serverport;
        String servername;
        public ServerDescriptorCar(String sa, int sp, String sn) {
            serveraddr = sa;
            serverport = sp;
            servername = sn;
        }
        //boolean livemark = true;
        CarInterface rmobj=null;

        @Override
        public String toString() {
            return serveraddr + ":" + serverport + ":" + servername;
        }
    }
	class ServerDescriptorHotel {
        String serveraddr;
        int serverport;
        String servername;
        public ServerDescriptorHotel(String sa, int sp, String sn) {
            serveraddr = sa;
            serverport = sp;
            servername = sn;
        }
        //boolean livemark = true;
        HotelInterface rmobj=null;

        @Override
        public String toString() {
            return serveraddr + ":" + serverport + ":" + servername;
        }
    }
	
	class SyncChecker implements Runnable
	{

		@Override
		public void run()
		{
			while(!m_bsyncdone)
			{
				try
				{
					if(isPrimary())
					{
						m_bsyncdone=true;
						break;
					}
				} catch (RemoteException e1)
				{
					e1.printStackTrace();
				}
				sendCommand("syncronize");
				try
				{
					Thread.sleep(SYNCTIMEOUT*1000);
				} 
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				if(!m_bsyncdone)
				{
					m_customerHT.clear();
					LockList.clear();
					operationIDList.clear();
					operationList.clear();
					System.out.println("\n\n>>>>>>>>>>>>>>>>>>>>>>>>>>>\nSyncronization not done in 10 secs" +
							"\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
				}
			}
			
		}
		
	}
	
	class RMLivenessChecker implements Runnable {

		ArrayList<ServerDescriptorFlight> FlightList=null;
		ArrayList<ServerDescriptorCar> CarList = null;
		ArrayList<ServerDescriptorHotel> HotelList = null;
		RMLivenessChecker(ArrayList<ServerDescriptorFlight> rmFlightList1,
				ArrayList<ServerDescriptorCar> rmCarList1,ArrayList<ServerDescriptorHotel> rmHotelList1)
		{
			FlightList=rmFlightList1;
			CarList=rmCarList1;
			HotelList=rmHotelList1;
		}
    	@Override
    	public void run() {
    		while (true) {

    			for (int i = 0; i < FlightList.size(); i++) {
    				try {
    					if(FlightList.get(i).rmobj!=null)
    					{
    						if(FlightList.get(i).rmobj.isPrimary())
    						{
    							System.out.println("\n\nPrimary Flight selected: "+FlightList.get(i).servername+"\n\n");
    							rmFlight=FlightList.get(i).rmobj;
    						}
    					}
    					else
    						throw new RemoteException();
    					
    				}
    				catch (RemoteException re) 
    				{
    					//re.printStackTrace();
    					try
    					{
    					Registry res = LocateRegistry.getRegistry(FlightList.get(i).serveraddr,
								FlightList.get(i).serverport);
						FlightList.get(i).rmobj = (FlightInterface)
								res.lookup(FlightList.get(i).servername);
						FlightList.get(i).rmobj.isPrimary();
						//FlightList.get(i).livemark = true;
						System.out.println("%%%%%%%%%Found flightRM alive "+FlightList.get(i).toString());
    					}
    					catch(Exception ex)
    					{
    						
    					}
    				}
    				 //catch (NotBoundException nbe) {
    					//nbe.printStackTrace();
    				//}
    			}
    			
    			for (int i = 0; i < CarList.size(); i++) {
    				try {
    					if(CarList.get(i).rmobj!=null)
    					{
    						if(CarList.get(i).rmobj.isPrimary())
    						{
    							rmCar=CarList.get(i).rmobj;
    						}
    					}
    					else
    						throw new RemoteException();
    					
    				}
    				catch (RemoteException re) 
    				{
    					//re.printStackTrace();
    					try
    					{
    					Registry res = LocateRegistry.getRegistry(CarList.get(i).serveraddr,
    							CarList.get(i).serverport);
    					CarList.get(i).rmobj = (CarInterface)
								res.lookup(CarList.get(i).servername);
    					CarList.get(i).rmobj.isPrimary();
    					//CarList.get(i).livemark = true;
						System.out.println("%%%%%%%%%Found carRM alive "+CarList.get(i).toString());
    					}
    					catch(Exception ex)
    					{
    						
    					}
    				}
    			}
    			
    			for (int i = 0; i < HotelList.size(); i++) {
    				try {
    					if(HotelList.get(i).rmobj!=null)
    					{
    						if(HotelList.get(i).rmobj.isPrimary())
    						{
    							rmHotel=HotelList.get(i).rmobj;
    						}
    					}
    					else
    						throw new RemoteException();
    					
    				}
    				catch (RemoteException re) 
    				{
    					//re.printStackTrace();
    					try
    					{
    					Registry res = LocateRegistry.getRegistry(HotelList.get(i).serveraddr,
    							HotelList.get(i).serverport);
    					HotelList.get(i).rmobj = (HotelInterface)
								res.lookup(HotelList.get(i).servername);
    					HotelList.get(i).rmobj.isPrimary();
    					//HotelList.get(i).livemark = true;
						System.out.println("%%%%%%%%%Found hotelRM alive "+HotelList.get(i).toString());
    					}
    					catch(Exception ex)
    					{
    						
    					}
    				}
    			}
    			try
        		{
        			Thread.sleep(1 * 1000);
        		}
        		catch (InterruptedException ie) {
    				//ie.printStackTrace();
    			}

    		} 
    		
    	}

    }
	public void findPrimaryFlightRM()//here assign rmFlight to Primary flight RM
	{
		System.out.println("\n%%%%%%%%%%%%%%\nIn findPrimaryFlightRM\n");
		//TODO:commented below 
		//rmFlight=null;
		for(int i=0;i<rmFlightList.size();i++)
		{
			try
			{
				if(rmFlightList.get(i).rmobj!=null && rmFlightList.get(i).rmobj.isPrimary())
				{
					rmFlight=rmFlightList.get(i).rmobj;
					System.out.println("\n%%%%%%%%%%%%%%\nrmFlight set\n");
					break;
				}
			}
			catch(RemoteException e)
			{
				//rmFlightList.get(i).livemark = false;
				rmFlightList.get(i).rmobj = null;
                System.out.println("%%%%%%%%\n"+rmFlightList.get(i) + " is dead");
			}
		}
		if(rmFlight==null)
			System.out.println("\n%%%%%%%Problem%%%%%%%\nrmFlight is null\n");

	}
	public void findPrimaryCarRM()//here assign rmFlight to Primary flight RM
	{
		//rmCar=null;
		for(int i=0;i<rmCarList.size();i++)
		{
			try
			{
				if(rmCarList.get(i).rmobj!=null && rmCarList.get(i).rmobj.isPrimary())
				{
					rmCar=rmCarList.get(i).rmobj;
					break;
				}
			}
			catch(RemoteException e)
			{
				//rmCarList.get(i).livemark = false;
				rmCarList.get(i).rmobj = null;
                System.out.println(rmCarList.get(i) + " is dead");
			}
		}

	}
	public void findPrimaryHotelRM()//here assign rmFlight to Primary flight RM
	{
		//rmHotel=null;
		for(int i=0;i<rmHotelList.size();i++)
		{
			try
			{
				if(rmHotelList.get(i).rmobj!=null && rmHotelList.get(i).rmobj.isPrimary())
				{
					rmHotel=rmHotelList.get(i).rmobj;
					break;
				}
			}
			catch(RemoteException e)
			{
				//rmHotelList.get(i).livemark = false;
				rmHotelList.get(i).rmobj = null;
                System.out.println(rmHotelList.get(i) + " is dead");
			}
		}
	}
	//key=trxnID
	//value=List all operationIDs done.
	//This list keeps operationIDs and the results so that we do not repeat the same operations in case of failures. 
	private ConcurrentHashMap<Integer, HashMap<Integer,Object>> operationIDList=
			new ConcurrentHashMap<Integer, HashMap<Integer,Object>>();
	
	class LockGroup
	{
		String strData;
		String param;
		int lockType;
		LockGroup(String strData1, String param1, int lockType2)
		{
			strData=strData1;
			param=param1;
			lockType=lockType2;
		}
	}
	private ConcurrentHashMap<Integer,ArrayList<LockGroup>> LockList = new ConcurrentHashMap<Integer,ArrayList<LockGroup>>();

	class Tuple3 {
		String key;
		int operation;    //  1 - write, 2 - delete
		RMItem value;
		Tuple3(String k, int op, RMItem nv) {
			key = k;
			operation = op;
			value = nv;
		}
	}

	//Undo operationList
	private ConcurrentHashMap<Integer, ArrayList<Tuple3>> operationList =
			new ConcurrentHashMap<Integer, ArrayList<Tuple3>>();//transaction_id -> tuple3 list

	private void checkOperationQueue(int id) 
	{
		if(!operationList.containsKey(id))
			//if (!operationList.contains(id)) 
		{
			System.out.println("Adding new trxn to operationList");
			operationList.put(id, new ArrayList<Tuple3>());
		}
	}
	protected RMHashtable m_customerHT = new RMHashtable();
	private Map<String,Boolean> m_RMState;
	FlightInterface rmFlight = null;
	CarInterface rmCar = null;
	HotelInterface rmHotel = null;
	//static int MaxFlightRMID=10;
	//static int MaxCarRMID=10;
	//static int MaxHotelRMID=10;
	ArrayList<ServerDescriptorFlight> rmFlightList = new ArrayList<ServerDescriptorFlight>();
	ArrayList<ServerDescriptorCar> rmCarList = new ArrayList<ServerDescriptorCar>();
	ArrayList<ServerDescriptorHotel> rmHotelList = new ArrayList<ServerDescriptorHotel>();
	private TransactionMgr trxnMgr;
	private int mport;
	private TimeoutThread timeout_thread;

	public int start() throws RemoteException
	{
//		if(crashAt==1)
//		{
//			System.exit(1);
//		}
		System.out.println("\n\nTransaction started\n\n");
		int txID=trxnMgr.start();
		String command="start";
		//commandList.put(txID,new ArrayList<String>());
		sendCommand(command);
		operationIDList.put(txID,new HashMap<Integer,Object>());
//		if(crashAt==2)
//		{
//			System.exit(1);
//		}
		return txID;
	}
	public void abort(int txID) throws RemoteException,InvalidTransactionException, TransactionAbortedException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		System.out.println("Middleware abort called");
		UndoCustomerTrxnData(txID);
		operationList.remove(txID);
		operationIDList.remove(txID);
		//commandList.remove(txID);
		LockList.remove(txID);
		trxnMgr.abortFromMW(txID);
		if(crashAt==4)
		{
			System.exit(1);
		}
		String command="abort,"+txID;
		sendCommand(command);
		System.out.println("\n\nTransaction "+txID+" aborted\n\n");
		if(crashAt==2)
		{
			System.exit(1);
		}
	}
	public boolean commit(int txID) throws RemoteException, TransactionAbortedException,
	InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		operationList.remove(txID);
		operationIDList.remove(txID);
		//commandList.remove(txID);
		LockList.remove(txID);
		boolean bcommit=trxnMgr.commit(txID);
		if(crashAt==4)
		{
			System.exit(1);
		}
		String command="commit,"+txID;
		sendCommand(command);
		if(bcommit)
			System.out.println("\n\nTransaction "+txID+" commited\n\n");
		if(crashAt==2)
		{
			System.exit(1);
		}
		return bcommit;
	}
	public boolean Lock(int txID, String strData, String param1, int lockType) throws RemoteException,TransactionAbortedException,InvalidTransactionException
	{
		//try
		//{

		System.out.println("Trying to Lock on "+strData+"="+param1);
		
		boolean bLock=trxnMgr.Lock(txID, strData, param1, lockType);
		if(bLock)
		{
			synchronized (LockList)
			{
				if(LockList.containsKey(txID))
				{
					LockList.get(txID).add(new LockGroup(strData,param1,lockType));
				}
				else
				{
					LockList.put(txID,new ArrayList<LockGroup>());
					LockList.get(txID).add(new LockGroup(strData,param1,lockType));
				}
			}
			String command="Lock,"+txID+","+strData+","+param1+","+lockType;
			sendCommand(command);
			System.out.println("Lock on "+strData+"="+param1+" is successful");
		}
		return bLock;
		//}
		/*catch(TransactionAbortedException e)
		{
			UndoCustomerTrxnData(txID);
			throw e;
		}*/
	}

	public boolean shutdown() throws RemoteException, NotBoundException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		boolean bCar;
		boolean bHotel;
		boolean bFlight;
		bFlight=bHotel=bCar=true;

		if(rmFlight!=null)
		{
			System.out.println("Calling Flight RM shutdown");
			bFlight=rmFlight.shutdown();
		}
		if(rmCar!=null)
		{
			System.out.println("Calling Car RM shutdown");
			bCar=rmCar.shutdown();
		}
		if(rmHotel!=null)
		{
			System.out.println("Calling Hotel RM shutdown");
			bHotel=rmHotel.shutdown();
		}
		Registry registry = LocateRegistry.getRegistry(mport);

		// Unregister middleware obj
		registry.unbind("Group28ResourceManager");

		// Unexport; this will also remove us from the RMI runtime
		UnicastRemoteObject.unexportObject(this, true);

		timeout_thread.kill=true;

		System.out.println("Middleware Server exiting.");
		String command="shutdown";
		sendCommand(command);
		if(crashAt==2)
		{
			System.exit(1);
		}
		if(bFlight && bCar && bHotel)
			return true;
		else
			return false;
	}
	// Reads a data item
	private RMItem readData( int id, String key )
	{
		synchronized(m_customerHT) 
		{
			return (RMItem) m_customerHT.get(key);
		}
	}

	private void UndoCustomerTrxnData(int txID) throws RemoteException, TransactionAbortedException, InvalidTransactionException
	{
		ArrayList<Tuple3> list=this.operationList.get(txID);
		if(list==null)
			return;
		int size=list.size();
		System.out.println("UndoCustomerTrxnData called. Operation list contains "+size+" operations to undo");
		for(int i=0;i<size;i++)
		{
			Tuple3 obj=list.get(i);
			if(obj.operation==1)//was write operation
			{
				if(obj.value==null)
				{
					synchronized(m_customerHT)
					{
						System.out.println("New customer was called. So deleting the customer.");
						//no need to call delete customer as the cancelling of reservations 
						//are handled by each RM.
						//int custID=Customer.getCustomerID(obj.key);
						//System.out.println("Deleting customer"+custID);
						//deleteCustomer(txID, custID);

						m_customerHT.remove(obj.key);
					}
				}
				else
				{
					synchronized(m_customerHT)
					{
						System.out.println("Resetting the customer to its old value.");

						System.out.println("old Value is");
						System.out.println(((Customer)obj.value).printBill());
						System.out.println("new Value is");
						System.out.println(((Customer)readData(txID, obj.key)).printBill());
						m_customerHT.put(obj.key,obj.value);
					}
				}
			}
			else if(obj.operation==2)//was delete operation
			{
				synchronized(m_customerHT)
				{
					System.out.println("Deletecustomer was called. So Resetting the customer to its old value.");
					m_customerHT.put(obj.key,obj.value);
				}
			}
		}
	}
	
	//called for syncronizing
	private void syncCustomer(String key, RMItem value )
	{
		synchronized (m_customerHT)
		{
			System.out.println("Customer syncronized\n"+((Customer)value).printBill());
			m_customerHT.put(key, value);
		}
	}
	private void syncOperationID(int trxnID,int opID,Object value )
	{
		synchronized (operationIDList)
		{
			System.out.println("Syncing for operationIDList");
			HashMap<Integer,Object> list=operationIDList.get(trxnID);
			if(list==null)
			{
				list=new HashMap<Integer,Object>();
				operationIDList.put(trxnID, list);
			}
			operationIDList.get(trxnID).put(opID,value);
			System.out.println("OperationIDList syncronized for trxn="+trxnID+" operation="+opID);
			
		}
	}
	private void syncLock(int trxnID,LockGroup lg)
	{
		try
		{
			System.out.println("Syncing and Locking on="+trxnID);
			Lock(trxnID, lg.strData, lg.param, lg.lockType);
		}
		catch (RemoteException | TransactionAbortedException
				| InvalidTransactionException e)
		{
			e.printStackTrace();
		}
		
	}
	private void syncLatestTransactionID(int LatestTransactionID)
	{
		System.out.println("Received LatestTransactionID="+LatestTransactionID);
		TransactionMgr.LatestTransactionID=LatestTransactionID;
	}
	
	// Writes a data item
	private void writeData( int id,int operationID, String key, RMItem value )
	{
		HashMap<Integer,Object> list=operationIDList.get(id);
		if(list.containsKey(operationID)) //operation already performed
		{
			System.out.println("\n%%%%%%%%%%%\nOperation already performed\n%%%%%%%%%%%%%%%%%%\n");
			return;
		}
		synchronized(m_customerHT) 
		{
			RMItem oldvalue = readData(id, key);
			System.out.println("********************Start**************************");
			System.out.println("Writing data in customer DB.");
			System.out.println("old Value is");
			if(oldvalue!=null)
				System.out.println(((Customer)oldvalue).printBill());
			else
				System.out.println("Old value was null");
			System.out.println("new Value is");
			System.out.println(((Customer)value).printBill());

			checkOperationQueue(id);
			this.operationList.get(id).add(0,new Tuple3(key,1,oldvalue));
			System.out.println("Operation List has "+this.operationList.get(id).size()+" operations");
			m_customerHT.put(key, value);
			if(crashAt==4)
			{
				System.exit(1);
			}
			String valueStr="";
			try
			{
				valueStr = toString(value);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			synchronized (operationIDList)
			{
				list.put(operationID,value);
			}
			
			String command="writeData,"+id+","+operationID+","+key+","+valueStr;
			sendCommand(command);
			
			System.out.println("*********************End*************************");
		}
	}

	// Remove the item out of storage
	protected RMItem removeData(int id,int operationID, String key) 
	{
		HashMap<Integer,Object> list=operationIDList.get(id);
		if(list.containsKey(operationID)) //operation already performed
		{
			System.out.println("\n%%%%%%%%%%%\nOperation already performed\n%%%%%%%%%%%%%%%%%%\n");
			return (RMItem)list.get(operationID);
		}
		synchronized(m_customerHT) 
		{
			RMItem oldvalue = readData(id, key);
			checkOperationQueue(id);
			this.operationList.get(id).add(0,new Tuple3(key,2,oldvalue));

			RMItem ret=(RMItem)m_customerHT.remove(key);
			if(crashAt==4)
			{
				System.exit(1);
			}
			synchronized (operationIDList)
			{
				list.put(operationID,ret);
			}
			
			String command="removeData,"+id+","+operationID+","+key;
			sendCommand(command);
			return ret;
		}
	}

	@Override
	public boolean addFlight(int id,int operationID, int flightNum, int flightSeats,
			int flightPrice) throws RemoteException,TransactionAbortedException,
			InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmFlight==null || m_RMState.isEmpty() || !m_RMState.get("flight"))
				{
					Trace.info("RM::addFlight called. Cannot add flight as RM is down.");
					return false;
				}
				String param1=Integer.toString(flightNum);
				if(Lock(id, "flight",param1, TransactionMgr.WRITE)==false)
					return false;
				//System.out.println("newFlight lock acquired.");
				if(rmFlight.addFlight(id,operationID, flightNum, flightSeats, flightPrice))
				{
					if(crashAt==2)
					{
						System.exit(1);
					}
					return true;
				}
				else
					return false;
			}
			catch(RemoteException e)
			{
				findPrimaryFlightRM();
			}
		}
	}

	@Override
	public boolean addCars(int id,int operationID, String location, int numCars, int price)
			throws RemoteException,TransactionAbortedException,
			InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmCar==null || m_RMState.isEmpty() || !m_RMState.get("car"))
				{
					Trace.info("RM::addcar called. Cannot add car as RM is down.");
					return false;
				}
				if(Lock(id, "car",location, TransactionMgr.WRITE)==false)
					return false;

				if(rmCar.addCars(id,operationID, location, numCars, price))
				{
					if(crashAt==2)
					{
						System.exit(1);
					}
					return true;
				}
				else
					return false;
			}
			catch(RemoteException e)
			{
				findPrimaryCarRM();
			}
		}
	}

	@Override
	public boolean addRooms(int id,int operationID, String location, int numRooms, int price)
			throws RemoteException ,TransactionAbortedException,
			InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmHotel==null || m_RMState.isEmpty() || !m_RMState.get("hotel"))
				{
					Trace.info("RM::addRooms called. Cannot add room as RM is down.");
					return false;
				}
				if(Lock(id, "hotel",location, TransactionMgr.WRITE)==false)
					return false;

				if(rmHotel.addRooms(id,operationID, location, numRooms, price))
				{
					if(crashAt==2)
					{
						System.exit(1);
					}
					return true;
				}
				else
					return false;
			}
			catch(RemoteException e)
			{
				findPrimaryHotelRM();
			}
		}
	}

	@Override
	public int newCustomer(int id,int operationID) throws RemoteException ,TransactionAbortedException,
	InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		Trace.info("INFO: RM::newCustomer(" + id + ") called" );
		// Generate a globally unique ID for the new customer
		int cid = Integer.parseInt( String.valueOf(id) +
				String.valueOf(Calendar.getInstance().get(Calendar.MILLISECOND)) +
				String.valueOf( Math.round( Math.random() * 100 + 1 )));
		if(Lock(id, "customer",Integer.toString(cid), TransactionMgr.WRITE)==false)
			return -1;
		Customer cust = new Customer( cid );
		writeData( id,operationID, cust.getKey(), cust );
		Trace.info("RM::newCustomer(" + cid + ") returns ID=" + cid );
		if(crashAt==2)
		{
			System.exit(1);
		}
		return cid;
	}

	@Override
	public boolean newCustomer(int id,int operationID, int customerID) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		Trace.info("INFO: RM::newCustomer(" + id + ", " + customerID + ") called" );
		if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.READ)==false)
			return false;
		Customer cust = (Customer) readData( id, Customer.getKey(customerID) );
		if ( cust == null ) {
			if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.WRITE)==false)
				return false;
			cust = new Customer(customerID);
			
			writeData( id,operationID, cust.getKey(), cust );
			Trace.info("INFO: RM::newCustomer(" + id + ", " + customerID + ") created a new customer" );
			if(crashAt==2)
			{
				System.exit(1);
			}
			return true;
		} else {
			Trace.info("INFO: RM::newCustomer(" + id + ", " + customerID + ") failed--customer already exists");
			return false;
		} // else
	}

	@Override
	public boolean deleteFlight(int id,int operationID, int flightNum) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmFlight!=null)
				{
					String param1=Integer.toString(flightNum);
					if(Lock(id, "flight",param1, TransactionMgr.WRITE)==false)
						return false;
					boolean ret=rmFlight.deleteFlight(id,operationID, flightNum);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return false;
			}
			catch(RemoteException e)
			{
				findPrimaryFlightRM();
			}
		}
	}

	@Override
	public boolean deleteCars(int id,int operationID, String location) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmCar!=null)
				{
					if(Lock(id, "car",location, TransactionMgr.WRITE)==false)
						return false;
					boolean ret=rmCar.deleteCars(id,operationID, location);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return false;
			}
			catch(RemoteException e)
			{
				findPrimaryCarRM();
			}
		}
	}

	@Override
	public boolean deleteRooms(int id,int operationID, String location) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmHotel!=null)
				{
					if(Lock(id, "hotel",location, TransactionMgr.WRITE)==false)
						return false;
					boolean ret=rmHotel.deleteRooms(id,operationID, location);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return false;
			}
			catch(RemoteException e)
			{
				findPrimaryHotelRM();
			}
		}
	}

	@Override
	public boolean deleteCustomer(int id,int operationID, int customerID) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		Trace.info("RM::deleteCustomer(" + id + ", " + customerID + ") called" );
		if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.READ)==false)
			return false;
		Customer cust = (Customer) readData( id, Customer.getKey(customerID) );
		if ( cust == null ) {
			Trace.warn("RM::deleteCustomer(" + id + ", " + customerID + ") failed--customer doesn't exist" );
			return false;
		} else 
		{    
			if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.WRITE)==false)
				return false;
			// Increase the reserved numbers of all reservable items which the customer reserved.
			
			RMHashtable reservationHT = cust.getReservations();
			for (Enumeration e = reservationHT.keys(); e.hasMoreElements();) 
			{        
				String reservedkey = (String) (e.nextElement());
				ReservedItem reserveditem = cust.getReservedItem(reservedkey);
				Trace.info("RM::deleteCustomer(" + id + ", " + customerID + ") has reserved " + reserveditem.getKey() + " " +  reserveditem.getCount() +  " times"  );
				String strData=reserveditem.getKey();
				int index=strData.indexOf("-");
				String strData1=strData.substring(0,index);
				String strData2=strData.substring(index+1);
				System.out.println("Deleting the customer type="+strData1+" number="+strData2);
				ReservableItem item;
				boolean primaryRMfound=true;
				while(primaryRMfound)
				{
					try
					{
						if(rmFlight!=null && strData1.equals("flight"))
						{
							//Could be erroneous. #Check#.
							if(Lock(id,strData1,strData2,TransactionMgr.READ)==false)
								return false;
							item = (ReservableItem) rmFlight.readData(id, reserveditem.getKey());
							if(item!=null)//Item was a flight
							{
								Trace.info("RM::deleteCustomer(" + id + ", " + customerID + ") has reserved " + reserveditem.getKey() + "which is reserved" +  item.getReserved() +  " times and is still available " + item.getCount() + " times"  );
								if(Lock(id,strData1,strData2,TransactionMgr.WRITE)==false)
									return false;
								boolean rt=rmFlight.deleteReservation(id,operationID,reserveditem.getKey(),reserveditem.getCount());
								if(crashAt==2)
								{
									System.exit(1);
								}
								if(rt)
									primaryRMfound=false;
							}
						}
					}
					catch(RemoteException ec)
					{
						primaryRMfound=true;
						findPrimaryFlightRM();
					}
				}
				primaryRMfound=true;
				while(primaryRMfound)
				{
					try
					{
						if(rmCar!=null && strData1.equals("car"))
						{
							if(Lock(id,strData1,strData2,TransactionMgr.READ)==false)
								return false;
							item = (ReservableItem) rmCar.readData(id, reserveditem.getKey());
							if(item!=null)//Item was a flight
							{
								Trace.info("RM::deleteCustomer(" + id + ", " + customerID + ") has reserved " + reserveditem.getKey() + "which is reserved" +  item.getReserved() +  " times and is still available " + item.getCount() + " times"  );
								if(Lock(id,strData1,strData2,TransactionMgr.WRITE)==false)
									return false;
								boolean rt=rmCar.deleteReservation(id,operationID,reserveditem.getKey(),reserveditem.getCount());
								if(crashAt==2)
								{
									System.exit(1);
								}
								if(rt)
									primaryRMfound=false;
							}
						}
					}
					catch(RemoteException ec)
					{
						primaryRMfound=true;
						findPrimaryCarRM();
					}
				}
				primaryRMfound=true;
				while(primaryRMfound)
				{
					try
					{
						if(rmHotel!=null && strData1.equals("hotel"))
						{
							if(Lock(id,strData1,strData2,TransactionMgr.READ)==false)
								return false;
							item = (ReservableItem) rmHotel.readData(id, reserveditem.getKey());
							if(item!=null)//Item was a flight
							{
								Trace.info("RM::deleteCustomer(" + id + ", " + customerID + ") has reserved " + reserveditem.getKey() + "which is reserved" +  item.getReserved() +  " times and is still available " + item.getCount() + " times"  );
								if(Lock(id,strData1,strData2,TransactionMgr.WRITE)==false)
									return false;
								boolean rt=rmHotel.deleteReservation(id,operationID,reserveditem.getKey(),reserveditem.getCount());
								if(crashAt==2)
								{
									System.exit(1);
								}
								if(rt)
									primaryRMfound=false;
							}
						}
					}
					catch(RemoteException ec)
					{
						primaryRMfound=true;
						findPrimaryHotelRM();
					}
				}
			}
			

			// remove the customer from the storage
			removeData(id,operationID, cust.getKey());

			Trace.info("RM::deleteCustomer(" + id + ", " + customerID + ") succeeded" );
			return true;
		} // if
	}

	@Override
	public int queryFlight(int id,int operationID, int flightNumber) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmFlight!=null)
				{
					String param1=Integer.toString(flightNumber);
					if(Lock(id, "flight",param1, TransactionMgr.READ)==false)
						return -1;
					int ret=rmFlight.queryFlight(id,operationID, flightNumber);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return -1;
			}
			catch(RemoteException e)
			{
				findPrimaryFlightRM();
			}
		}
	}

	@Override
	public int queryCars(int id,int operationID, String location) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmCar!=null)
				{
					if(Lock(id, "car",location, TransactionMgr.READ)==false)
						return -1;
					int ret=rmCar.queryCars(id,operationID, location);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return -1;
			}
			catch(RemoteException e)
			{
				findPrimaryCarRM();
			}
		}
	}

	@Override
	public int queryRooms(int id,int operationID, String location) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmHotel!=null)
				{
					if(Lock(id, "hotel",location, TransactionMgr.READ)==false)
						return -1;
					int ret=rmHotel.queryRooms(id,operationID, location);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return -1;
			}
			catch(RemoteException e)
			{
				findPrimaryHotelRM();
			}
		}
	}

	@Override
	public String queryCustomerInfo(int id,int operationID, int customerID)
			throws RemoteException ,TransactionAbortedException,
			InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		Trace.info("RM::queryCustomerInfo(" + id + ", " + customerID + ") called" );
		if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.READ)==false)
			return "";
		Customer cust = (Customer) readData( id, Customer.getKey(customerID) );
		if ( cust == null ) {
			Trace.warn("RM::queryCustomerInfo(" + id + ", " + customerID + ") failed--customer doesn't exist" );
			return "";   // NOTE: don't change this--WC counts on this value indicating a customer does not exist...
		} else {
			String s = cust.printBill();
			Trace.info("RM::queryCustomerInfo(" + id + ", " + customerID + "), bill follows..." );
			System.out.println( s );
			if(crashAt==2)
			{
				System.exit(1);
			}
			return s;
		} // if
	}

	@Override
	public int queryFlightPrice(int id,int operationID, int flightNumber)
			throws RemoteException ,TransactionAbortedException,
			InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmFlight!=null)
				{
					String param1=Integer.toString(flightNumber);
					if(Lock(id, "flight",param1, TransactionMgr.READ)==false)
						return -1;
					int ret=rmFlight.queryFlightPrice(id,operationID, flightNumber);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return -1;
			}
			catch(RemoteException e)
			{
				findPrimaryFlightRM();
			}
		}
	}

	@Override
	public int queryCarsPrice(int id,int operationID, String location) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException 
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmCar!=null)
				{
					if(Lock(id, "car",location, TransactionMgr.READ)==false)
						return -1;
					int ret=rmCar.queryCarsPrice(id,operationID, location);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return -1;
			}
			catch(RemoteException e)
			{
				findPrimaryCarRM();
			}
		}
	}

	@Override
	public int queryRoomsPrice(int id,int operationID, String location) throws RemoteException,TransactionAbortedException,
	InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		while(true)
		{
			try
			{
				if(rmHotel!=null)
				{
					if(Lock(id, "hotel",location, TransactionMgr.READ)==false)
						return -1;
					int ret=rmHotel.queryRoomsPrice(id,operationID, location);
					if(crashAt==2)
					{
						System.exit(1);
					}
					return ret;
				}
				return -1;
			}
			catch(RemoteException e)
			{
				findPrimaryHotelRM();
			}
		}
	}

	@Override
	public boolean reserveFlight(int id,int operationID, int customerID, int flightNumber)
			throws RemoteException ,TransactionAbortedException,
			InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
				if(rmFlight==null)
				{
					System.out.println("Flight RM is down.");
					return false;
				}
				String key = Flight.getKey(flightNumber);
				String location = String.valueOf(flightNumber);
				Trace.info("RM::reserveItem( " + id + ", customer=" + customerID + ", " +key+ ", "+location+" ) called" );        
				// Read customer object if it exists (and read lock it)
				if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.READ)==false)
					return false;
				Customer cust = (Customer) readData( id, Customer.getKey(customerID) );        
				if ( cust == null ) {
					Trace.warn("RM::reserveFlight( " + id + ", " + customerID + ", " + key + ", "+location+")  failed--customer doesn't exist" );
					return false;
				} 
				String param1=Integer.toString(flightNumber);
				if(Lock(id, "flight",param1, TransactionMgr.READ)==false)
					return false;
				// check if the item is available
				ReservableItem item=null;
				boolean bPrimaryRMnotFound=true;
				while(bPrimaryRMnotFound)
				{
					try
					{
						item = (ReservableItem)rmFlight.readData(id, key);
						bPrimaryRMnotFound=false;
					}
					catch(RemoteException e)
					{
						bPrimaryRMnotFound=true;
						findPrimaryFlightRM();
						if(rmFlight==null)
						{
							System.out.println("Flight RM is down.");
							return false;
						}
					}
				}
				if ( item == null ) {
					Trace.warn("RM::reserveItem( " + id + ", " + customerID + ", " + key+", " +location+") failed--item doesn't exist" );
					return false;
				} else if (item.getCount()==0) {
					Trace.warn("RM::reserveItem( " + id + ", " + customerID + ", " + key+", " + location+") failed--No more items" );
					return false;
				} else 
				{   
					if(Lock(id, "flight",param1, TransactionMgr.WRITE)==false)
						return false;
					if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.WRITE)==false)
						return false;
					Customer newcustomer=cust.clone();
					newcustomer.reserve( key, location, item.getPrice());
					writeData( id,operationID, newcustomer.getKey(), newcustomer );
					
					//should be consistent if fails here
					//TODO: check
					if(crashAt==3)
					{
						System.exit(1);
					}

					// decrease the number of available items in the storage
					//if(primary)
					bPrimaryRMnotFound=true;
					while(bPrimaryRMnotFound)
					{
						try
						{
							rmFlight.reserveFlight(id,operationID, key);
							bPrimaryRMnotFound=false;
						}
						catch( RemoteException e)
						{
							bPrimaryRMnotFound=true;
							findPrimaryFlightRM();
						}
					}

					//should be consistent if fails here
					//TODO: check
					if(crashAt==2)
					{
						System.exit(1);
					}

					//set state()
					Trace.info("RM::reserveItem( " + id + ", " + customerID + ", " + key + ", " +location+") succeeded" );
					return true;
				}
				//bExceptionOccured=false;//if everything ok would not reach this statement
			
		
	}

	@Override
	public boolean reserveCar(int id,int operationID, int customerID, String location)
			throws RemoteException ,TransactionAbortedException,
			InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		if(rmCar==null)
		{
			System.out.println("Car RM is down.");
			return false;
		}
		String key = Car.getKey(location);
		Trace.info("RM::reserveItem( " + id + ", customer=" + customerID + ", " +key+ ", "+location+" ) called" );        
		// Read customer object if it exists (and read lock it)
		if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.READ)==false)
			return false;
		Customer cust = (Customer) readData( id, Customer.getKey(customerID) );        
		if ( cust == null ) {
			Trace.warn("RM::reserveCar( " + id + ", " + customerID + ", " + key + ", "+location+")  failed--customer doesn't exist" );
			return false;
		} 

		if(Lock(id, "car",location, TransactionMgr.READ)==false)
			return false;
		// check if the item is available
		ReservableItem item=null;
		boolean bPrimaryRMnotFound=true;
		while(bPrimaryRMnotFound)
		{
			try
			{
				item = (ReservableItem)rmCar.readData(id, key);
				bPrimaryRMnotFound=false;
			}
			catch(RemoteException e)
			{
				bPrimaryRMnotFound=true;
				findPrimaryCarRM();
			}
		}
		if ( item == null ) {
			Trace.warn("RM::reserveItem( " + id + ", " + customerID + ", " + key+", " +location+") failed--item doesn't exist" );
			return false;
		} else if (item.getCount()==0) {
			Trace.warn("RM::reserveItem( " + id + ", " + customerID + ", " + key+", " + location+") failed--No more items" );
			return false;
		} else 
		{            
			if(Lock(id, "car",location, TransactionMgr.WRITE)==false)
				return false;
			if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.WRITE)==false)
				return false;
			Customer newcustomer=cust.clone();
			newcustomer.reserve( key, location, item.getPrice());
			writeData( id,operationID, cust.getKey(), newcustomer );

			if(crashAt==3)
			{
				System.exit(1);
			}
			bPrimaryRMnotFound=true;
			while(bPrimaryRMnotFound)
			{
				try
				{
					// decrease the number of available items in the storage
					rmCar.reserveCar(id,operationID,key);
					bPrimaryRMnotFound=false;
				}
				catch(RemoteException e)
				{
					bPrimaryRMnotFound = true;
					findPrimaryCarRM();
				}
			}
			if(crashAt==2)
			{
				System.exit(1);
			}

			Trace.info("RM::reserveItem( " + id + ", " + customerID + ", " + key + ", " +location+") succeeded" );
			return true;
		} 

			}

	@Override
	public boolean reserveRoom(int id,int operationID, int customerID, String location)
			throws RemoteException ,TransactionAbortedException,
			InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		if(rmHotel==null)
		{
			System.out.println("Hotel RM is down.");
			return false;
		}
		String key = Hotel.getKey(location);
		Trace.info("RM::reserveItem( " + id + ", customer=" + customerID + ", " +key+ ", "+location+" ) called" );        
		// Read customer object if it exists (and read lock it)
		if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.READ)==false)
			return false;
		Customer cust = (Customer) readData( id, Customer.getKey(customerID) );        
		if ( cust == null ) {
			Trace.warn("RM::reserveRoom( " + id + ", " + customerID + ", " + key + ", "+location+")  failed--customer doesn't exist" );
			return false;
		} 

		if(Lock(id, "hotel",location, TransactionMgr.READ)==false)
			return false;
		ReservableItem item=null;
		boolean bPrimaryRMnotFound=true;
		while(bPrimaryRMnotFound)
		{
			try
			{
				// check if the item is available
				item = (ReservableItem)rmHotel.readData(id, key);
				bPrimaryRMnotFound=false;
			}
			catch(RemoteException e)
			{
				bPrimaryRMnotFound=true;
				findPrimaryHotelRM();
			}
		}
		if ( item == null ) {
			Trace.warn("RM::reserveItem( " + id + ", " + customerID + ", " + key+", " +location+") failed--item doesn't exist" );
			return false;
		} else if (item.getCount()==0) {
			Trace.warn("RM::reserveItem( " + id + ", " + customerID + ", " + key+", " + location+") failed--No more items" );
			return false;
		} else 
		{            
			if(Lock(id, "hotel",location, TransactionMgr.WRITE)==false)
				return false;
			if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.WRITE)==false)
				return false;
			Customer newcustomer=cust.clone();
			newcustomer.reserve( key, location, item.getPrice());
			writeData( id,operationID, cust.getKey(), newcustomer );

			if(crashAt==3)
			{
				System.exit(1);
			}
			bPrimaryRMnotFound=true;
			while(bPrimaryRMnotFound)
			{
				try
				{
					// decrease the number of available items in the storage
					rmHotel.reserveRoom(id,operationID,key);
					bPrimaryRMnotFound=false;
				}
				catch(RemoteException e)
				{
					bPrimaryRMnotFound=true;
					findPrimaryHotelRM();
				}
			}
			if(crashAt==2)
			{
				System.exit(1);
			}

			Trace.info("RM::reserveItem( " + id + ", " + customerID + ", " + key + ", " +location+") succeeded" );
			return true;
		} 
	}

	@Override
	public boolean itinerary(int id,int operationID, int customerID, Vector flightNumbers,
			String location, boolean Car, boolean Room) throws RemoteException ,TransactionAbortedException,
			InvalidTransactionException
	{
		if(crashAt==1)
		{
			System.exit(1);
		}
		if(Lock(id, "customer",Integer.toString(customerID), TransactionMgr.READ)==false)
			return false;
		Customer cust = (Customer) readData( id, Customer.getKey(customerID) );
		if ( cust == null ) 
		{
			Trace.warn("RM::reserveItinerary (" + id + ", " + customerID + ") failed--customer doesn't exist" );   
			return false;
		}
		boolean bItineraryReservable = true;
		int flightNumber;
		int opID=operationID;
		for(int i=0;bItineraryReservable && i<flightNumbers.size();i++)
		{
			flightNumber = Integer.valueOf((String) flightNumbers.get(i));
			String param1=Integer.toString(flightNumber);
			if(Lock(id, "flight",param1, TransactionMgr.READ)==false)
				bItineraryReservable = false;
			if(bItineraryReservable && queryFlight(id,opID, flightNumber)<=0)
			{
				bItineraryReservable = false;
			}
		}
		if(bItineraryReservable && Car)
		{
			if(Lock(id, "car",location, TransactionMgr.READ)==false)
				bItineraryReservable = false;
			if(bItineraryReservable && queryCars(id,opID, location)<=0)
			{
				bItineraryReservable = false;
			}
		}
		if(bItineraryReservable && Room)
		{
			if(Lock(id, "hotel",location, TransactionMgr.READ)==false)
				bItineraryReservable = false;
			if(bItineraryReservable && queryRooms(id,opID, location)<=0)
			{
				bItineraryReservable = false;
			}
		}
		//If everything reservable, then reserve the itinerary
		if(bItineraryReservable)
		{
			for(int i=0;i<flightNumbers.size();i++)
			{
				flightNumber = Integer.valueOf((String) flightNumbers.get(i));
				String param1=Integer.toString(flightNumber);
				if(Lock(id, "flight",param1, TransactionMgr.WRITE)==false)
				{
					bItineraryReservable = false;
				}
				if(!reserveFlight(id,opID, customerID, flightNumber))
				{
					//Could have inconsistent data
					Trace.info("RM::reserveFlight( " + id + ", " + customerID + ", " +flightNumber+") failed" );

					bItineraryReservable = false;
				}
				opID++;
			}
			opID=operationID+flightNumbers.size();
			if(Car)
			{
				if(Lock(id, "car",location, TransactionMgr.WRITE)==false)
				{
					bItineraryReservable = false;
				}
				if(!reserveCar(id,opID, customerID, location))
				{
					//Could have inconsistent data
					Trace.info("RM::reserveCar( " + id + ", " + customerID + ", " +location+") failed" );

					bItineraryReservable = false;
				}
				opID++;
			}
			if(Room)
			{
				if(Lock(id, "hotel",location, TransactionMgr.WRITE)==false)
				{
					bItineraryReservable = false;
				}
				if(!reserveRoom(id,opID, customerID, location))
				{
					//Could have inconsistent data
					Trace.info("RM::reserveRoom( " + id + ", " + customerID + ", " +location+") failed" );

					bItineraryReservable = false;
				}
				opID++;
			}
		}
		else//if not reservable
		{
			//trxnMgr.abort(id);
		}
		return bItineraryReservable;// actually send a string saying transaction aborted
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Figure out where server is running
		String server = "localhost";
		String serverLookUpName = "Group28ResourceManager";
		ArrayList<String> flightServer = new ArrayList<String>();
		ArrayList<String> carServer = new ArrayList<String>();
		ArrayList<String> hotelServer = new ArrayList<String>();
		ArrayList<String> flightRMLookUp = new ArrayList<String>();
		ArrayList<String> carRMLookUp = new ArrayList<String>();
		ArrayList<String> hotelRMLookUp = new ArrayList<String>();
		int port = 1099;

		if(args.length==0)
		{
			System.err.println ("Please enter arguments 1) <port> \n2)<Middleware Server Lookup name>" +
					"\nand then any RMs which" +
					" are running, as Strings followed by the machines on which they are running." +
					"and RMI lookup names" +
					"\nExample:\n 8228 Group28ResourceManager1 crashAt flight <machine name> <RMI lookup name> flight <machine name> " +
					"<RMI lookup name>" +
					" car <machine name> <RMI lookup name> " +
					"hotel <machine name> <RMI lookup name> hotel <machine name> <RMI lookup name>\n");
			System.exit(1);
		}

		MiddlewareServer obj = new MiddlewareServer();
		TransactionMgr trxnMgr = new TransactionMgr();
		obj.trxnMgr=trxnMgr;
		trxnMgr.mdServer=obj;
		TimeoutThread t1 = new TimeoutThread(trxnMgr);
		obj.timeout_thread=t1;
		t1.start();
		obj.m_RMState = new HashMap<String, Boolean>();
		// Initialize frequency table from command line
		port = Integer.parseInt(args[0]);
		serverLookUpName = args[1];
		obj.mport=port;
		obj.crashAt=Integer.parseInt(args[2]);
		for(int i=3; i < args.length-2; i++)
		{
			if (args[i].equals("flight")) {
				flightServer.add(args[i+1]);//machine name
				flightRMLookUp.add(args[i+2]);//Remote object name
				obj.m_RMState.put(args[i], true);
			}
			if (args[i].equals("car")) {
				carServer.add(args[i+1]);
				carRMLookUp.add(args[i+2]);
				obj.m_RMState.put(args[i], true);
			}
			if (args[i].equals("hotel")) {
				hotelServer.add(args[i+1]);
				hotelRMLookUp.add(args[i+2]);
				obj.m_RMState.put(args[i], true);
			}
		}
		//Check for remote objects of RMs. Acts as Client to RMs.
		try 
		{
			if(!obj.m_RMState.isEmpty())
			{
				if(obj.m_RMState.get("flight")!=null)
				{
					for(int itr=0;itr<flightServer.size();itr++)
					{
						
						// get a reference to the rmiregistry
						Registry registry = LocateRegistry.getRegistry(flightServer.get(itr), port);
						// get the proxy and the remote reference by rmiregistry lookup
						ServerDescriptorFlight sdf=obj.new ServerDescriptorFlight(flightServer.get(itr),port, flightRMLookUp.get(itr));
						obj.rmFlightList.add(sdf);
						//sdf.livemark=false;
						try
						{
						sdf.rmobj=(FlightInterface) registry.lookup(flightRMLookUp.get(itr));
						//sdf.livemark=true;
						}
						catch(RemoteException | NotBoundException ex)
						{
							System.out.println("failed to connect to remote object, " + sdf.toString() +
		                            ", adding it as dead server");
						}
					}
					obj.findPrimaryFlightRM();
					if(obj.rmFlight!=null)
					{
						System.out.println("\nConnected to FlightRM\n");
						TransactionMgr.rmFlight = obj.rmFlight;
					}
					else
					{
						System.out.println("\nConnection unsuccessful to Flight RM\n");
					}
				}
				if(obj.m_RMState.get("car")!=null)
				{
					for(int itr=0;itr<carServer.size();itr++)
					{
						// get a reference to the rmiregistry
						Registry registry = LocateRegistry.getRegistry(flightServer.get(itr), port);
						// get the proxy and the remote reference by rmiregistry lookup
						ServerDescriptorCar sdc=obj.new ServerDescriptorCar(carServer.get(itr),port, carRMLookUp.get(itr));
						obj.rmCarList.add(sdc);
						//sdc.livemark=false;
						try
						{
							sdc.rmobj=(CarInterface) registry.lookup(carRMLookUp.get(itr));
							//sdc.livemark=true;
						}
						catch(RemoteException | NotBoundException ex)
						{
							System.out.println("failed to connect to remote object, " + sdc.toString() +
		                            ", adding it as dead server");
						}
					}
					obj.findPrimaryCarRM();
					if(obj.rmCar!=null)
					{
						System.out.println("\nConnected to CarRM\n");
						TransactionMgr.rmCar=obj.rmCar;
					}
					else
					{
						System.out.println("\nConnection unsuccessful to Car RM\n");
					}
				}
				if(obj.m_RMState.get("hotel")!=null)
				{
					for(int itr=0;itr<hotelServer.size();itr++)
					{
						// get a reference to the rmiregistry
						Registry registry = LocateRegistry.getRegistry(flightServer.get(itr), port);
						// get the proxy and the remote reference by rmiregistry lookup
						ServerDescriptorHotel sdh=obj.new ServerDescriptorHotel(hotelServer.get(itr),port, hotelRMLookUp.get(itr));
						obj.rmHotelList.add(sdh);
						//sdh.livemark=false;
						try
						{
							sdh.rmobj=(HotelInterface) registry.lookup(hotelRMLookUp.get(itr));
							//sdh.livemark=true;
						}
						catch(RemoteException | NotBoundException ex)
						{
							System.out.println("failed to connect to remote object, " + sdh.toString() +
		                            ", adding it as dead server");
						}
					}
					obj.findPrimaryHotelRM();
					if(obj.rmHotel!=null)
					{
						System.out.println("\nConnected to HotelRM\n");
						TransactionMgr.rmHotel=obj.rmHotel;
					}
					else
					{
						System.out.println("\nConnection unsuccessful to Hotel RM\n");
					}
				}
			}
		} 
		catch (Exception e) 
		{    
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
		
		Thread checkerthread = new Thread(obj.new RMLivenessChecker(obj.rmFlightList,obj.rmCarList,obj.rmHotelList));
        checkerthread.start();

		//Register MiddlewareServer object. Acts as server for Clients.
		server = server + ":" + args[0];
		try
		{
			obj.connectToGroup();
			Thread synccheckerThread= new Thread(obj.new SyncChecker());
			synccheckerThread.start();
			ResourceManager rm = (ResourceManager) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry(port);
			registry.rebind(serverLookUpName, rm);


			System.err.println("Server ready");
		}
		catch (Exception e) 
		{
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}




	}
}
