package Interfaces;

import Entities.Vehicule;

public interface VehiculeInter extends GlobalInter<Vehicule> {
	public Vehicule getById(String matricule);
}
