package pl.pzu.trak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.pzu.trak.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
	
	@Override
	void delete(Role role);
}
