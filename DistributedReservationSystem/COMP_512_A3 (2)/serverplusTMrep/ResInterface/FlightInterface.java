/*
 * Author: Navjot Singh
 */
package ResInterface;

import ResImpl.RMItem;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface FlightInterface extends Remote 
{
	public RMItem readData( int id, String key ) throws RemoteException;
    //public void writeData( int id, String key, RMItem value );
    public boolean deleteReservation(int id, int opID, String key,int reservedItemCount) throws RemoteException;
	
	/* Add seats to a flight.  In general this will be used to create a new
     * flight, but it should be possible to add seats to an existing flight.
     * Adding to an existing flight should overwrite the current price of the
     * available seats.
     *
     * @return success.
     */
    public boolean addFlight(int id, int opID, int flightNum, int flightSeats, int flightPrice)
	throws RemoteException;
    /**
     *   Delete the entire flight.
     *   deleteflight implies whole deletion of the flight.  
     *   all seats, all reservations.  If there is a reservation on the flight, 
     *   then the flight cannot be deleted
     *
     * @return success.
     */   
    public boolean deleteFlight(int id, int opID, int flightNum)
	throws RemoteException; 
    /* queryFlight returns the number of empty seats. */
    public int queryFlight(int id, int opID, int flightNumber)
	throws RemoteException;
    /* queryFlightPrice returns the price of a seat on this flight. */
    public int queryFlightPrice(int id, int opID, int flightNumber)
	throws RemoteException;
    /* Reserve a seat on this flight*/
    public boolean reserveFlight(int id, int opID, String key)
	throws RemoteException;
    public boolean shutdown() throws RemoteException;
    public boolean commit(int txid) throws RemoteException;
    public boolean abort(int txid) throws RemoteException;

    public boolean isPrimary() throws RemoteException;
}
