package main;

public class Motorbike extends Vehicle
{
	public Motorbike(String licensePlate)
	{
		this.parkingSpotsNeeded = 1;
		this.licensePlate = licensePlate;
		this.size = ParkingSpotSize.MOTORBIKE;
	}

	protected boolean canParkInSpot(ParkingSpot ps) 
	{
		ParkingSpotSize psSize = ps.getSpotSize();
		return psSize == ParkingSpotSize.MOTORBIKE || psSize == ParkingSpotSize.COMPACT
				|| psSize == ParkingSpotSize.NORMAL;
	}
}
