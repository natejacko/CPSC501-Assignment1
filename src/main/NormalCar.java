package main;

public class NormalCar extends Car
{
	public NormalCar(String licensePlate)
	{
		super();
		this.licensePlate = licensePlate;
		this.size = ParkingSpotSize.NORMAL;
	}

	protected boolean canParkInSpot(ParkingSpot ps) 
	{
		return ps.getSpotSize() == ParkingSpotSize.NORMAL;
	}
}
