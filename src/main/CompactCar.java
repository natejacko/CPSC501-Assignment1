package main;
public class CompactCar extends Vehicle
{
	public CompactCar(String licensePlate)
	{
		this.licensePlate = licensePlate;
		this.size = ParkingSpotSize.COMPACT;
	}

	protected boolean canParkInSpot(ParkingSpot ps) 
	{
		ParkingSpotSize psSize = ps.getSpotSize();
		return psSize == ParkingSpotSize.COMPACT || psSize == ParkingSpotSize.NORMAL;
	}
}
