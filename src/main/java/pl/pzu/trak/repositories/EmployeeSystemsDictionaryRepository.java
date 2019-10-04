package pl.pzu.trak.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeSystemsDictionary;

@Repository
public interface EmployeeSystemsDictionaryRepository  extends JpaRepository<EmployeeSystemsDictionary, Long>{

	@Query("select distinct z from EmployeeSystemsDictionary z")
	Collection<EmployeeSystemsDictionary> findAllNameSystemsDictionary();
	
}
