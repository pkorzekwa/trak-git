package pl.pzu.trak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeSystemsRoleDictionary;

@Repository
public interface EmployeeSystemsRoleDictionaryRepository extends JpaRepository<EmployeeSystemsRoleDictionary, Long>{

}
