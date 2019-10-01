package main;
import java.util.ArrayList;

public class ParkingGarageLevel 
{
	private int levelNumber;
	private ArrayList<ParkingSpot[]> rows;
	private int freeSpots;
	
	private static final double FRACTION_MOTORBIKE_SPOTS = 0.25;
	private static final double FRACTION_COMPACT_SPOTS = 0.25;
	
	public ParkingGarageLevel(int levelNumber, int rowAmount, int spotsPerRow)
	{	
		this.levelNumber = levelNumber;
		rows = new ArrayList<ParkingSpot[]>(rowAmount);
		
		int motorbikePerRow = (int) (spotsPerRow * FRACTION_MOTORBIKE_SPOTS);
		int compactPerRow = (int) (spotsPerRow * FRACTION_COMPACT_SPOTS);
		
		for (int row = 0; row < rowAmount; row++)
		{
			ParkingSpot[] ps = new ParkingSpot[spotsPerRow];
			for (int spot = 0; spot < spotsPerRow; spot++)
			{
				ParkingSpotSize size = ParkingSpotSize.NORMAL;
				if (spot < motorbikePerRow)
				{
					size = ParkingSpotSize.MOTORBIKE;
				}
				else if (spot < motorbikePerRow + compactPerRow)
				{
					size = ParkingSpotSize.COMPACT;
				}
				ps[spot] = new ParkingSpot(this, size);
			}
			rows.add(ps);
		}
		
		freeSpots = rowAmount * spotsPerRow;
	}
	
	public int getFreeSpotsOnLevel()
	{
		return freeSpots;
	}
	
	public boolean parkVehicle(Vehicle v)
	{
		for (int row = 0; row < rows.size(); row++)
		{
			ParkingSpot[] currentRow = rows.get(row);
			int freeSpot = -1;
			for (int spot = 0; spot < currentRow.length; spot++)
			{
				if (currentRow[spot].spotFreeAndCanFitVehicle(v))
				{
					freeSpot = spot;
					break;
				}
			}
			if (freeSpot != -1)
			{
				return parkVehicle(row, freeSpot, v);
			}
		}
		
		return false;
	}
	
	public boolean parkBus(Bus b)
	{
		if (getFreeSpotsOnLevel() < b.getParkingSpotsNeeded())
		{
			return false;
		}
		
		for (int row = 0; row < rows.size(); row++)
		{
			ParkingSpot[] currentRow = rows.get(row);
			int freeSpot = -1;
			for (int spot = 0; spot < currentRow.length; spot++)
			{
				if (currentRow[spot].spotFreeAndCanFitBus(b)) 
				{					
					// If more than one spot, check all sequential spots
					int checkedSpots = b.getParkingSpotsNeeded() - 1;
					for (int moreSpots = spot + 1; moreSpots < currentRow.length && checkedSpots > 0; moreSpots++)
					{	
						if (currentRow[moreSpots].spotFreeAndCanFitBus(b))
						{
							checkedSpots--;
						}
						else
						{
							break;
						}
					}
					if (checkedSpots == 0)
					{
						freeSpot = spot;
					}
				}
			}
			if (freeSpot != -1)
			{
				return parkBus(row, freeSpot, b);
			}
		}
		
		return false;
	}
	
	private boolean parkVehicle(int rowIndex, int spotIndex, Vehicle v)
	{
		v.leaveParkingSpots();
		freeSpots--;
		return rows.get(rowIndex)[spotIndex].parkVehicle(v);
	}
	
	// Will park in many spots
	private boolean parkBus(int rowIndex, int spotIndex, Bus b)
	{
		b.leaveParkingSpots();
		boolean parked = true;
		ParkingSpot[] currentRow = rows.get(rowIndex);
		for (int spot = spotIndex, spotsRemaining = b.getParkingSpotsNeeded();
				spot < currentRow.length && spotsRemaining > 0; spot++, spotsRemaining--)
		{
			parked &= currentRow[spot].parkBus(b);
		}
		freeSpots -= b.getParkingSpotsNeeded();
		return parked;
	}
	
	public void freeUpSpot()
	{
		freeSpots++;
	}
}
