package Interfaces;

import Entities.Marque;

public interface MarqueInter extends GlobalInter<Marque> {
	public Marque getById(long id);
}
