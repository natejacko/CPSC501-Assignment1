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
		
		assertTrue(p.parkVehicleInGarage(nc1));
		assertTrue(p.parkVehicleInGarage(nc2));
		assertTrue(p.parkVehicleInGarage(nc3));
		assertTrue(p.parkVehicleInGarage(nc4));
		assertTrue(p.removeVehicleFromGarage(nc2));
		assertFalse(p.parkVehicleInGarage(b));
	}

}
