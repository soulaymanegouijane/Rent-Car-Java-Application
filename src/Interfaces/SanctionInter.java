package Interfaces;

import Entities.Sanction;

public interface SanctionInter extends GlobalInter<Sanction> {
	public Sanction getById(long id);
}
