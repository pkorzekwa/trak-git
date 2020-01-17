package pl.pzu.trak.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pzu.trak.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName(String name);
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Role r SET r.name = :name WHERE r.id = :id")
    int updateRole(@Param("id") Long id, @Param("name") String name);

	@Override
	void delete(Role role);
	
	@Query(value="SELECT * from Role r WHERE r.id NOT IN (SELECT ur.role_id FROM users_roles ur WHERE ur.user_id = :userId)", nativeQuery = true)
	List<Role> AllRolesUserList(@Param("userId") Long userId);

}
