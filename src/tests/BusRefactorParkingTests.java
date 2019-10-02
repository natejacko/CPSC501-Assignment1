package tests;

import static org.junit.Assert.*;

import org.junit.*;
import main.*;

public class BusRefactorParkingTests 
{
	private ParkingGarage p;
	
	@Before
	public void createNewParkingGarage()
	{
		p = new ParkingGarage(1);
	}
	
	@Test
	public void testBusParking() 
	{	
		Vehicle b = new Bus("b", 6);
		
		assertTrue(p.parkVehicleInGarage(b));
	}

	@Test
	public void testOtherVehicleParking()
	{
		Vehicle cc = new CompactCar("cc");
		Vehicle nc = new NormalCar("nc");
		Vehicle mb = new Motorbike("mb");
		
		assertTrue(p.parkVehicleInGarage(cc));
		assertTrue(p.parkVehicleInGarage(nc));
		assertTrue(p.parkVehicleInGarage(mb));
	}
	
	@Test
	public void testParkTooLongBusForGarage()
	{
		Vehicle b = new Bus("b", 7);
		
		assertFalse(p.parkVehicleInGarage((Bus)b));
	}
	
	@Test
	public void testFillNormalSpotsWithBuses()
	{
		Vehicle b1 = new Bus("b1", 6);
		Vehicle b2 = new Bus("b2", 6);
		
		assertTrue(p.parkVehicleInGarage(b1));
		assertFalse(p.parkVehicleInGarage(b2));
	}
	
	@Test
	public void testFillWholeParkingGarage()
	{	
		Vehicle nc1 = new NormalCar("nc1");
		Vehicle nc2 = new NormalCar("nc2");
		Vehicle nc3 = new NormalCar("nc3");
		Vehicle nc4 = new NormalCar("nc4");
		Vehicle b1 = new Bus("b1", 3);
		Vehicle b2 = new Bus("b2", 3);
		
		assertTrue(p.parkVehicleInGarage(nc1));
		assertTrue(p.parkVehicleInGarage(nc2));
		assertTrue(p.parkVehicleInGarage(nc3));
		assertTrue(p.parkVehicleInGarage(b1));
		assertFalse(p.parkVehicleInGarage(nc4));
		assertFalse(p.parkVehicleInGarage(b2));
		
		Vehicle cc1 = new CompactCar("cc1");
		Vehicle cc2 = new CompactCar("cc2");
		Vehicle cc3 = new CompactCar("cc3");
		Vehicle cc4 = new CompactCar("cc4");
		
		assertTrue(p.parkVehicleInGarage(cc1));
		assertTrue(p.parkVehicleInGarage(cc2));
		assertTrue(p.parkVehicleInGarage(cc3));
		assertFalse(p.parkVehicleInGarage(cc4));
		
		Vehicle mb1 = new Motorbike("mb1");
		Vehicle mb2 = new Motorbike("mb2");
		Vehicle mb3 = new Motorbike("mb3");
		Vehicle mb4 = new Motorbike("mb4");
		
		assertTrue(p.parkVehicleInGarage(mb1));
		assertTrue(p.parkVehicleInGarage(mb2));
		assertTrue(p.parkVehicleInGarage(mb3));
		assertFalse(p.parkVehicleInGarage(mb4));
	}
	
	@Test
	public void testParkAndRemoveBus()
	{
		Vehicle b1 = new Bus("b1", 6);
		Vehicle b2 = new Bus("b2", 6);
		
		assertTrue(p.parkVehicleInGarage(b1));
		assertTrue(p.removeVehicleFromGarage(b1));
		assertTrue(p.parkVehicleInGarage(b2));
	}
}
