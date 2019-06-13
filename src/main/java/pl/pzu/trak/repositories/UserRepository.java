package pl.pzu.trak.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    @Query("UPDATE User r SET r.firstName = :firstName, r.lastName = :lastName, r.login = :login, r.enabled = :enabled WHERE r.id = :id")
    int updateUser(@Param("id") Long id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("login") String login, @Param("enabled") boolean enabled);



}
