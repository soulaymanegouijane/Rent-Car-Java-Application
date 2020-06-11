package Interfaces;

import Entities.Carburant;
import Entities.Marque;

public interface CarburantInter extends GlobalInter<Carburant> {
	public Carburant getById(long id);
}
