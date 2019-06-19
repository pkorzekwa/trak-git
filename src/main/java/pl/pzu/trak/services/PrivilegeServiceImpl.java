package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.Privilege;
import pl.pzu.trak.repositories.PrivilegeRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService
{
	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public void add(Privilege privilege) {
		privilegeRepository.save(privilege);
	}

	@Override
	public List<Privilege> findAll() {
		return privilegeRepository.findAll();
	}
	
	@Override
	public void remove(Long privilegeId) {
		privilegeRepository.deleteById(privilegeId);
	}

	@Override
	public void update(Privilege privilege) {
		privilegeRepository.save(privilege);
	}

	@Override
	public void save(Privilege privilege) {
		privilegeRepository.save(privilege);
	}
	
	@Override
	public Privilege findOne(Long id) {
		return privilegeRepository.getOne(id);
	}
	
	public List<Privilege> ListAllPrivilegesRoleList(Long roleId){
		return privilegeRepository.AllPrivilegesRoleList(roleId);
	}
}
