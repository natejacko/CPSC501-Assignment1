package tests;

import static org.junit.Assert.*;

import org.junit.*;
import main.*;

public class BusRefactorPropertyTests 
{

	@Test
	public void testBusSize() 
	{
		Bus b = new Bus("b", 6);
		ParkingSpotSize expected = ParkingSpotSize.NORMAL;
		ParkingSpotSize actual = b.getParkingSpotSize();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testBusParkingSpotsNeeded()
	{
		int expected = 6;
		Bus b = new Bus("b", expected);
		int actual = b.getParkingSpotsNeeded();
		
		assertEquals(expected, actual);
	}

}
