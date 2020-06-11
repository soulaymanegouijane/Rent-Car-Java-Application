package Interfaces;

import Entities.Reservation;

public interface ReservationInter extends GlobalInter<Reservation> {
	public Reservation getById(long id);
}
