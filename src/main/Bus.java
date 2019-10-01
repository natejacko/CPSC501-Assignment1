package main;
import java.util.ArrayList;

public class Bus
{
	protected ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
	protected String licensePlate;
	protected int parkingSpotsNeeded;
	protected ParkingSpotSize size;
	protected ParkingTicket parkingTicket;
	
	public Bus(String licensePlate, int parkingSpotLength)
	{
		this.parkingSpotsNeeded = parkingSpotLength;
		this.licensePlate = licensePlate;
		this.size = ParkingSpotSize.NORMAL;
	}
	
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
	
	public int getParkingSpotsNeeded()
	{
		return parkingSpotsNeeded;
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

	protected boolean canParkInSpot(ParkingSpot ps) 
	{
		return ps.getSpotSize() == ParkingSpotSize.NORMAL;
	}
}
