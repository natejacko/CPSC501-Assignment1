package main;
public class NormalCar extends Vehicle
{
	public NormalCar(String licensePlate)
	{
		this.licensePlate = licensePlate;
		this.size = ParkingSpotSize.NORMAL;
	}

	protected boolean canParkInSpot(ParkingSpot ps) 
	{
		return ps.getSpotSize() == ParkingSpotSize.NORMAL;
	}
}
