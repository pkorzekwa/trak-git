package pl.pzu.trak.services;


import java.util.List;

import pl.pzu.trak.domain.Privilege;

public interface PrivilegeService 
{
	void add(Privilege privilege);
	List<Privilege> findAll();
	public void remove(Long Id);
	void update(Privilege privilege);
	void save(Privilege privilege);
	Privilege findOne(Long id);
	List<Privilege> ListAllPrivilegesRoleList(Long roleId);
	void updatePrivilege(Long Id, String name);

}
