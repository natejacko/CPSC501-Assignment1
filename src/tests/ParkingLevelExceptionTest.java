package tests;

import static org.junit.Assert.*;

import org.junit.*;
import main.*;

public class ParkingLevelExceptionTest 
{

	@Test
	public void testParkBusNotEnoughContinuousSpots() 
	{
		ParkingGarage p = new ParkingGarage(1);
		
		Vehicle nc1 = new NormalCar("nc1");
		Vehicle nc2 = new NormalCar("nc2");
		Vehicle nc3 = new NormalCar("nc3");
		Vehicle nc4 = new NormalCar("nc3");
		Vehicle b = new Bus("b", 3);
		
		assertTrue(p.parkVehicle(nc1));
		assertTrue(p.parkVehicle(nc2));
		assertTrue(p.parkVehicle(nc3));
		assertTrue(p.parkVehicle(nc4));
		assertTrue(p.removeVehicleFromGarage(nc2));
		assertFalse(p.parkVehicle(b));
	}

}
