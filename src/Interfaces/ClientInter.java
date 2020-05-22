package Interfaces;

import java.util.List;

import Entities.Client;
import Entities.Reservation;

public interface ClientInter extends GlobalInter<Client>{
	public List<Reservation> trouverTousLesReservation(Client client);
	
}
