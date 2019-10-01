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
		int halfHourAmount = (int) Math.ceil(ticketTime / 30);
		return halfHourAmount * parkingGarage.getParkingRatePerHalfHour();
	}
}
