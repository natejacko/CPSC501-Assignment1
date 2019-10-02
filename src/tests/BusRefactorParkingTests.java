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
	public void testNormalBusParking() 
	{	
		Bus b = new Bus("b", 6);
		
		assertTrue(p.parkBus(b));
	}
	
	@Test
	public void testVehicleBusParking() 
	{	
		Vehicle b = new Bus("b", 6);
		
		assertTrue(p.parkBus((Bus)b));
	}

	@Test
	public void testOtherVehicleParking()
	{
		Vehicle cc = new CompactCar("cc");
		Vehicle nc = new NormalCar("nc");
		Vehicle mb = new Motorbike("mb");
		
		assertTrue(p.parkVehicle(cc));
		assertTrue(p.parkVehicle(nc));
		assertTrue(p.parkVehicle(mb));
	}
	
	@Test
	public void testParkTooLongBusForGarage()
	{
		Vehicle b = new Bus("b", 7);
		
		assertFalse(p.parkBus((Bus)b));
	}
	
	@Test
	public void testFillNormalSpotsWithBuses()
	{
		Bus b1 = new Bus("b1", 6);
		Bus b2 = new Bus("b2", 6);
		
		assertTrue(p.parkBus(b1));
		assertFalse(p.parkBus(b2));
	}
	
	@Test
	public void testFillWholeParkingGarage()
	{	
		Vehicle nc1 = new NormalCar("nc1");
		Vehicle nc2 = new NormalCar("nc2");
		Vehicle nc3 = new NormalCar("nc3");
		Vehicle nc4 = new NormalCar("nc4");
		Bus b1 = new Bus("b1", 3);
		Bus b2 = new Bus("b2", 3);
		
		assertTrue(p.parkVehicle(nc1));
		assertTrue(p.parkVehicle(nc2));
		assertTrue(p.parkVehicle(nc3));
		assertTrue(p.parkBus(b1));
		assertFalse(p.parkVehicle(nc4));
		assertFalse(p.parkBus(b2));
		
		Vehicle cc1 = new CompactCar("cc1");
		Vehicle cc2 = new CompactCar("cc2");
		Vehicle cc3 = new CompactCar("cc3");
		Vehicle cc4 = new CompactCar("cc4");
		
		assertTrue(p.parkVehicle(cc1));
		assertTrue(p.parkVehicle(cc2));
		assertTrue(p.parkVehicle(cc3));
		assertFalse(p.parkVehicle(cc4));
		
		Vehicle mb1 = new Motorbike("mb1");
		Vehicle mb2 = new Motorbike("mb2");
		Vehicle mb3 = new Motorbike("mb3");
		Vehicle mb4 = new Motorbike("mb4");
		
		assertTrue(p.parkVehicle(mb1));
		assertTrue(p.parkVehicle(mb2));
		assertTrue(p.parkVehicle(mb3));
		assertFalse(p.parkVehicle(mb4));
	}
	
	@Test
	public void testParkAndRemoveBus()
	{
		Bus b1 = new Bus("b1", 6);
		Bus b2 = new Bus("b2", 6);
		
		assertTrue(p.parkBus(b1));
		assertTrue(p.removeBusFromGarage(b1));
		assertTrue(p.parkBus(b2));
	}
}
