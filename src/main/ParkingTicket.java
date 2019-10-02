package main;

import java.time.Instant;

public class ParkingTicket 
{
	private long startTime;
	
	public ParkingTicket()
	{
		startTime = getCurrentTimeMilli();
	}
	
	// returns in minutes
	public long getElapsedTicketTime()
	{
		long endTime = getCurrentTimeMilli();
		long elapsedTimeMinutes = convertMilliToMinutes(endTime) - convertMilliToMinutes(startTime);
		return elapsedTimeMinutes;
	}
	
	private long getCurrentTimeMilli()
	{
		return Instant.now().toEpochMilli();
	}
	
	private long convertMilliToMinutes(long milli)
	{
		long seconds = milli / 1000;
		return seconds / 60;
	}
}
