package Interfaces;

import Entities.TypeReservation;

public interface TypeReservationInter extends GlobalInter<TypeReservation> {
	public TypeReservation getById(long id);
}
