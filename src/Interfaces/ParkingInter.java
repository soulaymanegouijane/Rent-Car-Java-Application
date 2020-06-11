package Interfaces;

import Entities.Parking;

public interface ParkingInter extends GlobalInter<Parking>{
	public Parking getById(long id);
}
