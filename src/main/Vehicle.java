package main;
import java.util.ArrayList;

public abstract class Vehicle 
{
	protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
	protected String licensePlate;
	protected ParkingSpotSize size;
	protected ParkingTicket parkingTicket;
	
	public ParkingSpotSize getParkingSpotSize()
	{
		return size;
	}
	
	public void receiveParkingTicket(ParkingTicket pt)
	{
		parkingTicket = pt;
	}
	
	public ParkingTicket getParkingTicket()
	{
		return parkingTicket;
	}
	
	public boolean payParkingTicket(double amount)
	{
		// Payment outside the scope of this code. Assume payment always is completed
		this.parkingTicket = null;
		return true;
	}
	
	public void park(ParkingSpot ps)
	{
		parkingSpots.add(ps);
	}
	
	public void leaveParkingSpots()
	{
		for (int spot = 0; spot < parkingSpots.size(); spot++)
		{
			parkingSpots.get(spot).freeUpSpot();
		}
		parkingSpots.clear();
	}
	
	protected abstract boolean canParkInSpot(ParkingSpot ps);
}
