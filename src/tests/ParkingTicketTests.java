package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import main.*;

public class ParkingTicketTests 
{
	private ParkingTicket pt;
	
	@Before
	public void createParkingTicket()
	{
		pt = new ParkingTicket();
	}
	
	@Test
	public void testLessThanMinuteElapsedTimeOnParkingTicket()
	{
		try 
		{
			TimeUnit.SECONDS.sleep(1);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			fail();
		}
		long expected = 0;
		long actual = pt.getElapsedTicketTime();
		assertEquals(expected, actual);
	}
	
	//@Test
	public void testTwoMinuteElapsedTimeOnParkingTicket() 
	{
		try 
		{
			TimeUnit.MINUTES.sleep(1);
			TimeUnit.MINUTES.sleep(1);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			fail();
		}
		long expected = 2L;
		long actual = pt.getElapsedTicketTime();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLessThanMinuteParkingTicketPrice()
	{
		ParkingGarage p = new ParkingGarage(2);
		ParkingGarageExitGate exit = new ParkingGarageExitGate(p);
		
		double expected = 0.0;
		double actual = exit.getParkingTicketPrice(pt);
		assertEquals(expected, actual, 0);
	}
	
	//@Test
	public void testLessThanThirtyMinuteParkingTicketPrice()
	{
		ParkingGarage p = new ParkingGarage(2);
		ParkingGarageExitGate exit = new ParkingGarageExitGate(p);
		
		try 
		{
			TimeUnit.MINUTES.sleep(1);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			fail();
		}
		double expected = 2.0;
		double actual = exit.getParkingTicketPrice(pt);
		assertEquals(expected, actual, 0);
	}
	
	//@Test
	public void testOneHourParkingTicketPrice()
	{
		ParkingGarage p = new ParkingGarage(2);
		ParkingGarageExitGate exit = new ParkingGarageExitGate(p);
		
		try 
		{
			for (int i = 0; i < 30; i++)
				TimeUnit.MINUTES.sleep(1);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			fail();
		}
		double expected = 4.0;
		double actual = exit.getParkingTicketPrice(pt);
		assertEquals(expected, actual, 0);
	}
}
