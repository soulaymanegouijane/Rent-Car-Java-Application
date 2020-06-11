package Interfaces;

import Entities.Factures;

public interface FactureInter extends GlobalInter<Factures> {
	public Factures getById(long id);
}
