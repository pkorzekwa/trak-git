package pl.pzu.trak.repositories;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pzu.trak.domain.Syst;

@Repository
public interface SystRepository extends JpaRepository<Syst, Short> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Syst r SET r.systemName = :systemName WHERE r.id = :systemId")
    int updateSystem(@Param("systemId") Short systemId, @Param("systemName") String systemName);

}
