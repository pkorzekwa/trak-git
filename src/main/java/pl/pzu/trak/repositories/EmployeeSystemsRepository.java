package pl.pzu.trak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.EmployeeSystems;

@Repository
public interface EmployeeSystemsRepository extends JpaRepository<EmployeeSystems, Long>{
	
	@Query(value = "SELECT * FROM employee_systems WHERE id_employee = :id_employee", nativeQuery = true)
	List<EmployeeSystems> employeeListSystems (@Param("id_employee") Long id_employee);

}
