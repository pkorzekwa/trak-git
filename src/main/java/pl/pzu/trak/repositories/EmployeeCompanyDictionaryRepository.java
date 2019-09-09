package pl.pzu.trak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeCompanyDictionary;

@Repository
public interface EmployeeCompanyDictionaryRepository extends JpaRepository<EmployeeCompanyDictionary, Long>{
	
	@Query("select distinct z from EmployeeCompanyDictionary z")
	List<EmployeeCompanyDictionary> findAll();

}
