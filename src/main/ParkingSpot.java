package main;

public class ParkingSpot 
{
	private Vehicle currentVehicle;
	private Bus currentBus;
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
		return currentVehicle == null && currentBus == null;
	}
	
	public boolean spotFreeAndCanFitVehicle(Vehicle v)
	{
		return isSpotFree() && v.canParkInSpot(this);
	}
	
	public boolean spotFreeAndCanFitBus(Bus b)
	{
		return isSpotFree() && b.canParkInSpot(this);
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
		if (spotFreeAndCanFitBus(b))
		{
			currentBus = b;
			b.park(this);
			return true;
		}
		return false;
	}
	
	public void freeUpSpot()
	{
		level.freeUpSpot();
		currentVehicle = null;
		currentBus = null;
	}
}
