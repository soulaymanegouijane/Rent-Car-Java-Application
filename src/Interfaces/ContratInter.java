package Interfaces;

import Entities.Contrat;

public interface ContratInter extends GlobalInter<Contrat> {
	public Contrat getById(long id);
}
