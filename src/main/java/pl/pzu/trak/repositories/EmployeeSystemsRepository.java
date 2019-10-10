package pl.pzu.trak.repositories;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeSystems;
import pl.pzu.trak.domain.EmployeeSystemsQuery;

@Repository
public interface EmployeeSystemsRepository extends JpaRepository<EmployeeSystems, Long>{
	
	@Query(value = "SELECT * FROM employee_systems WHERE id_employee = :id_employee", nativeQuery = true)
	Collection<EmployeeSystems> employeeListSystems (@Param("id_employee") Long id_employee);
	
	@Query("SELECT id_systems, id_company FROM EmployeeSystems WHERE id_employee = :id_employee")
	Collection<EmployeeSystems> employeeDetails (@Param("id_employee") Long id_employee);
	
//	@Query(value = "SELECT distinct * FROM employee_systems s JOIN s.employee_systems_dictionary sd WHERE s.id_employee = :id_employee", nativeQuery = true)
//	Collection<EmployeeSystems> employeeDetailsSystems (@Param("id_employee") Long id_employee);
//	
//	@Query(value = "SELECT distinct * FROM employee_contract c JOIN c.employee_company_dictionary cd WHERE c.id_employee = :id_employee", nativeQuery = true)
//	Collection<EmployeeSystems> employeeDetailsCompany (@Param("id_employee") Long id_employee);
	
	
//	@Query("SELECT DISTINCT s, sd FROM EmployeeSystems s "
//			+ " JOIN s.employeeSystemsDictionary sd "
//			+ " JOIN s.employeeCompanyDictionary cd"
//			+ " WHERE s.id_employee = :id_employee")
//			//+ " GROUP BY cd.name, cd.id_company, sd.id_systems, sd.name")
//	Collection<EmployeeSystems> employeeDetailsSystemsById (@Param("id_employee") Long id_employee);
	
	@Query("SELECT DISTINCT new pl.pzu.trak.domain.EmployeeSystemsQuery (cd.name, sd.name) FROM EmployeeSystems AS s "
			+ " JOIN s.employeeSystemsDictionary AS sd "
			+ " JOIN s.employeeCompanyDictionary AS cd"
			+ " WHERE s.id_employee = :id_employee")
			//+ " GROUP BY cd.name, cd.id_company, sd.id_systems, sd.name")
	Collection<EmployeeSystemsQuery> employeeDetailsSystemsById (@Param("id_employee") Long id_employee);
	
//	@Query("SELECT cd FROM EmployeeContract c JOIN c.employeeCompanyDictionary cd WHERE c.id_employee = :id_employee")
//	Collection<EmployeeSystems> employeeDetailsCompany (@Param("id_employee") Long id_employee);
	
	
	
	@Transactional
	@Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM employee_systems WHERE id_employee_systems = :id_employee_systems", nativeQuery = true)
    void deleteById(@Param("id_employee_systems") Long id_employee_systems);
	
	
	
}
