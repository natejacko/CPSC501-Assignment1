package main;
public class ParkingGarage 
{
	private ParkingGarageLevel[] levels;
	private double parkingRatePerHalfHour;
	private final int GARAGELEVELS = 1;
	private ParkingGarageEntranceGate entrance;
	private ParkingGarageExitGate exit;
	
	public ParkingGarage(double parkingRatePerHalfHour)
	{
		levels = new ParkingGarageLevel[GARAGELEVELS];
		for (int level = 0; level < GARAGELEVELS; level++)
		{
			levels[level] = new ParkingGarageLevel(level + 1, 1, 12); 
		}
		this.parkingRatePerHalfHour = parkingRatePerHalfHour;
		entrance = new ParkingGarageEntranceGate(this);
		exit = new ParkingGarageExitGate(this);
	}
	
	public boolean parkVehicleInGarage(Vehicle v)
	{
		if (getFreeSpots() == 0)
		{
			return false;
		}
		
		for (int level = 0; level < levels.length; level++)
		{
			if (levels[level].parkVehicleOnLevel(v))
			{
				entrance.getParkingTicketForVehicle(v);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean removeVehicleFromGarage(Vehicle v)
	{
		v.leaveParkingSpots();
		double ticketPrice = exit.getParkingTicketPrice(v.getParkingTicket());
		return v.payParkingTicket(ticketPrice);
	}
	
	public double getParkingRatePerHalfHour()
	{
		return parkingRatePerHalfHour;
	}
	
	public int getFreeSpots()
	{
		int freeSpots = 0;
		for (int level = 0; level < levels.length; level++)
		{
			freeSpots += levels[level].getFreeSpotsOnLevel();
		}
		return freeSpots;
	}
}
