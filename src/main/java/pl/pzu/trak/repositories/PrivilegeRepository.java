package pl.pzu.trak.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.pzu.trak.domain.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

    @Query(value="SELECT * from Privilege p WHERE p.id NOT IN (SELECT rp.privilege_id FROM roles_privileges rp WHERE rp.role_id = :roleId)", nativeQuery = true)
    List<Privilege> AllPrivilegesRoleList(@Param("roleId") Long roleId);
}
