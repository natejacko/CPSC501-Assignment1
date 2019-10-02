package main;
public class ParkingGarageEntranceGate extends ParkingGarageGate
{	
	public ParkingGarageEntranceGate(ParkingGarage parkingGarage)
	{
		super(parkingGarage);
	}
	
	public void getParkingTicketForVehicle(Vehicle v)
	{
		ParkingTicket newTicket = new ParkingTicket();
		v.receiveParkingTicket(newTicket);
	}
}
