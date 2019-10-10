package pl.pzu.trak.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeCompanyDictionary;
import pl.pzu.trak.domain.Role;

@Repository
public interface EmployeeCompanyDictionaryRepository extends JpaRepository<EmployeeCompanyDictionary, Long>{
	
	@Query("select distinct z from EmployeeCompanyDictionary z")
	Collection<EmployeeCompanyDictionary> findAllNameCompanyDictionary();
	

}
