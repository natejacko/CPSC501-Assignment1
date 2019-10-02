package main;

public class ParkingGarageExitGate extends ParkingGarageGate 
{	
	public ParkingGarageExitGate(ParkingGarage parkingGarage)
	{
		super(parkingGarage);
	}
	
	public double getParkingTicketPrice(ParkingTicket pt)
	{
		long ticketTime = pt.getElapsedTicketTime();
		int halfHourAmount = convertMinutesToHalfHour(ticketTime);
		return getParkingTicketPrice(halfHourAmount);
	}
	
	// Returns ceiling
	private int convertMinutesToHalfHour(long minutes)
	{
		return (int) Math.ceil(minutes / 30.0);
	}
	
	private double getParkingTicketPrice(int halfHourAmount)
	{
		return halfHourAmount * parkingGarage.getParkingRatePerHalfHour();
	}
}
