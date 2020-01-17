package pl.pzu.trak.repositories;



import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.Role;
import pl.pzu.trak.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
      
    @Override
    void delete(User user);

	//User findOne(Long id);

	User findUserById(Long id);
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE User r SET r.firstName = :firstName, r.lastName = :lastName, r.login = :login, r.email = :email, r.enabled = :enabled WHERE r.id = :id")
    int updateUser(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("login") String login, @Param("email") String email,@Param("enabled") boolean enabled);

	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE User r SET r.roles = :roles WHERE r.id = :id")
    int updateUserRoles(@Param("id") Long id, @Param("roles") Collection<Role> roles);


}
