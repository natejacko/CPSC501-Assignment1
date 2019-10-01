package main;
import java.time.Instant;

public class ParkingTicket 
{
	private long startTime;
	
	public ParkingTicket()
	{
		startTime = Instant.now().toEpochMilli();
	}
	
	// returns in minutes
	public long getElapsedTicketTime()
	{	
		return (Instant.now().getEpochSecond() - startTime) / 1000;
	}
}
