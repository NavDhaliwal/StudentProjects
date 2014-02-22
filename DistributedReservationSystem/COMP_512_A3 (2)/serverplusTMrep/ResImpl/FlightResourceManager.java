/*
 * Author: Navjot Singh
 */
package ResImpl;


import org.jgroups.Message;
import ResInterface.FlightInterface;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class FlightResourceManager
        extends TransGCResourceManager implements FlightInterface {

    public FlightResourceManager (String groupName, String msgFilePath) {
        super(groupName, msgFilePath);
    }

    public RMItem readData( int id, String key ) throws RemoteException
    {
        return readDatafromRM(id, key);
    }

    // Writes a data item
    /*public void writeData( int id, String key, RMItem value )
    {
    	super.writeData(id, key, value);
    }*/
    @Override
    public boolean deleteReservation(int id, int opID, String key,
                                     int reservedItemCount) throws RemoteException
    {
        return deleteReservationfromRM(id, opID, key, reservedItemCount);
    }

    @Override
    public synchronized boolean addFlight(int id, int opID, int flightNum, int flightSeats,
                                          int flightPrice) throws RemoteException
    {
        Trace.info("RM::addFlight(" + id + ", " + flightNum + ", $" + flightPrice + ", " + flightSeats + ") called");
        Flight curObj = (Flight) readData( id, Flight.getKey(flightNum) );
        if ( curObj == null ) {
            // doesn't exist...add it
            Flight newObj = new Flight( flightNum, flightSeats, flightPrice );
            writeData( id, opID, newObj.getKey(), newObj );
            ResImpl.Trace.info(
                    "RM::addFlight(" + id + ") created new flight " + flightNum + ", seats=" +
                            flightSeats + ", price=$" + flightPrice);
        } else {
            // add seats to existing flight and update the price...
            Flight newobj = curObj.clone();
            newobj.setCount( curObj.getCount() + flightSeats );
            if ( flightPrice > 0 ) {
                newobj.setPrice( flightPrice );
            } // if
            writeData( id, opID, newobj.getKey(), newobj );
            Trace.info(
                    "RM::addFlight(" + id + ") modified existing flight " +
                            flightNum + ", seats=" + curObj.getCount() + ", price=$" + flightPrice);
        } // else
        return(true);
    }

    @Override
    public synchronized boolean deleteFlight(int id, int opID, int flightNum) throws RemoteException
    {
        return deleteItem(id, opID, ResImpl.Flight.getKey(flightNum));
    }

    @Override
    public int queryFlight(int id, int opID, int flightNumber) throws RemoteException
    {
        return queryNum(id, opID, ResImpl.Flight.getKey(flightNumber));
    }

    @Override
    public int queryFlightPrice(int id, int opID, int flightNumber)
            throws RemoteException
    {
        return queryPrice(id, opID, Flight.getKey(flightNumber));
    }

    @Override
    public boolean reserveFlight(int id, int opID, String key)
            throws RemoteException
    {
        return reserveItem(id, opID, key);
    }

    public boolean shutdown() {
        return true;
    }

    public boolean commit(int txid) {
        return super.commit(txid);
    }

    public boolean abort(int txid)  {
        return super.abort(txid);
    }


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String server = "localhost";
        int port = 1099;

        if (args.length == 3) {
            server = server + ":" + args[0];
            port = Integer.parseInt(args[0]);
        } else if (args.length != 0 &&  args.length != 3) {
            System.err.println ("Wrong usage");
            System.out.println("Usage: java ResImpl.ResourceManagerImpl [port] groupName RMIObjectName");
            System.exit(1);
        }

        try {
            // create a new Server object
            FlightResourceManager obj = new FlightResourceManager(args[1], args[2]);
            // dynamically generate the stub (client proxy)
            FlightInterface rm =
                    (FlightInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(port);
            registry.rebind(args[2], rm);

            System.err.println("Flight Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    protected void realizeSyncResponse(String syncStr) {
	
	    String [] objsyncArray = syncStr.split(";");
	    System.out.println("objsyncArray length:" + objsyncArray.length);
	    for (int i = 0; i < objsyncArray.length; i++) {
		    String [] objarr = objsyncArray[i].split(",");
		    Flight f = new Flight(Integer.parseInt(objarr[0]),
				    Integer.parseInt(objarr[1]),
				    Integer.parseInt(objarr[2]));
		    f.setReserved(Integer.parseInt(objarr[3]));
		    saveData(f.getKey(), f);
	    }
    }


    @Override
    public Object handle(Message msg) throws Exception {
        super.handle(msg);
        String msgString = (String) msg.getObject();
        String [] arr = msgString.split(";");
        String type = arr[0];
        if (type.equals("write")) {
            System.out.println("received write message as " + arr[1]);
            String writeString = arr[1];
            String [] parameters = arr[1].split(",");
            Flight f = new Flight(Integer.parseInt(parameters[3]),
                    Integer.parseInt(parameters[4]),
                    Integer.parseInt(parameters[5]));
            f.setReserved(Integer.parseInt(parameters[6]));
            realizeWriteMessage(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]),
                    f.getKey(), f);
        }
        return "ACK";
    }
}
