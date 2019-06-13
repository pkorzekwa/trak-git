package pl.pzu.trak.services;

import java.util.List;
import pl.pzu.trak.domain.Role;

public interface RoleService
{
	void add(Role role);
	List<Role> findAll();
	void remove(Long roleId);
	void update(Role role);
	void save(Role role);
	Role findOne(Long id);
	void updateRole(Long id, String name);

}
