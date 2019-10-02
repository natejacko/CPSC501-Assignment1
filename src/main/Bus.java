package main;

public class Bus extends Vehicle
{
	public Bus(String licensePlate, int parkingSpotLength)
	{
		this.parkingSpotsNeeded = parkingSpotLength;
		this.licensePlate = licensePlate;
		this.size = ParkingSpotSize.NORMAL;
	}

	protected boolean canParkInSpot(ParkingSpot ps) 
	{
		return ps.getSpotSize() == ParkingSpotSize.NORMAL;
	}
}
