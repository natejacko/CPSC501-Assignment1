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
}
