package main;

public class ParkingSpot 
{
	private Vehicle currentVehicle;
	private ParkingSpotSize size;
	private ParkingGarageLevel level;
	
	public ParkingSpot(ParkingGarageLevel level, ParkingSpotSize size)
	{
		this.currentVehicle = null;
		this.size = size;
		this.level = level;
	}
	
	public ParkingSpotSize getSpotSize()
	{
		return size;
	}
	
	public boolean isSpotFree()
	{
		return currentVehicle == null;
	}
	
	public boolean spotFreeAndCanFitVehicle(Vehicle v)
	{
		return isSpotFree() && v.canParkInSpot(this);
	}
	
	public boolean spotFreeAndCanFitBus(Bus b)
	{
		return spotFreeAndCanFitVehicle(b);
	}
	
	public boolean parkVehicle(Vehicle v)
	{
		if (spotFreeAndCanFitVehicle(v))
		{
			currentVehicle = v;
			v.park(this);
			return true;
		}
		return false;
	}
	
	public boolean parkBus(Bus b)
	{
		return parkVehicle(b);
	}
	
	public void freeUpSpot()
	{
		level.freeUpSpot();
		currentVehicle = null;
	}
}
