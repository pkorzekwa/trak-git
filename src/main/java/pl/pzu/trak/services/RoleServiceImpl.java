package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.Role;
import pl.pzu.trak.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void add(Role role) {
		roleRepository.save(role);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public void remove(Long roleId) {
		roleRepository.deleteById(roleId);
	}
	@Override
	public void removeRole(Long Id) {
		roleRepository.deleteRoleById(Id);
	}
	@Override
	public void update(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	@Override
	public Role findOne(Long id) {		
		return roleRepository.findById(id).get();
	}

	public void updateRole(Long id, String name)
	{
		roleRepository.updateRole(id, name);
	}

	@Override
	public List<Role> ListAllRolesUserList(Long userId) {
		return roleRepository.AllRolesUserList(userId);
	}

}
