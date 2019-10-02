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
	
	public boolean parkVehicleOnLevel(Vehicle v)
	{
		if (getFreeSpotsOnLevel() < v.getParkingSpotsNeeded())
		{
			return false;
		}
		
		for (int row = 0; row < rows.size(); row++)
		{
			try
			{
				int spot = getNextFreeSpotInRow(row, v);
				return parkVehicleInSpots(row, spot, v);
			}
			catch (NoFreeParkingSpotsException e) 
			{
				System.out.println(e);
			}
		}
		
		return false;
	}
	
	//Will park in many spots
	private boolean parkVehicleInSpots(int rowIndex, int spotIndex, Vehicle v)
	{
		v.leaveParkingSpots();
		boolean parked = true;
		ParkingSpot[] currentRow = rows.get(rowIndex);
		for (int spot = spotIndex, spotsRemaining = v.getParkingSpotsNeeded();
				spot < currentRow.length && spotsRemaining > 0; spot++, spotsRemaining--)
		{
			parked &= currentRow[spot].parkInSpot(v);
		}
		freeSpots -= v.getParkingSpotsNeeded();
		return parked;
	}
	
	// Will scan ahead if vehicle requires multiple spots
	// Returns index of spot(s) that are free and fit vehicle
	// Will throw exception if now (continuous) spots found
	private int getNextFreeSpotInRow(int rowIndex, Vehicle v) throws NoFreeParkingSpotsException
	{
		ParkingSpot[] currentRow = rows.get(rowIndex);
		for (int spot = 0; spot < currentRow.length; spot++)
		{
			if (currentRow[spot].spotFreeAndCanFitVehicle(v))
			{
				if (v.getParkingSpotsNeeded() == 1)
				{
					return spot;
				}
				
				// If more than one spot, check all sequential spots
				int checkedSpots = v.getParkingSpotsNeeded() - 1;
				for (int moreSpots = spot + 1; moreSpots < currentRow.length && checkedSpots > 0; moreSpots++)
				{	
					if (currentRow[moreSpots].spotFreeAndCanFitVehicle(v))
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
					return spot;
				}
			}
		}
		
		throw new NoFreeParkingSpotsException("Cannot find enough free parking spots for vehicle in row " + (rowIndex + 1));
	}
	
	public void freeUpSpot()
	{
		freeSpots++;
	}
}
