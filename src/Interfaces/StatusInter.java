package Interfaces;

import Entities.Status;

public interface StatusInter extends GlobalInter<Status>{
	public Status getById(long id);
}
