package Interfaces;

import Entities.Role;

public interface RoleInter extends GlobalInter<Role> {
	public Role getById(long id);
}
