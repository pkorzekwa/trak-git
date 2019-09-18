package pl.pzu.trak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.Employee;
import pl.pzu.trak.domain.EmployeeQuery;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
//	@Query(value = "SELECT * from employee", nativeQuery = true)
//	List<Employee> all();
	
	
//	@Query(value = "SELECT e.first_name, e.last_name, e.team, e.workplace from employee e "
//			+ " LEFT JOIN employee_contract c ON e.id_employee = c.id_employee"
//			+ " LEFT JOIN  employee_company_dictionary cd ON c.id_company = cd.id_company"
//			+ " LEFT JOIN employee_systems s ON e.id_employee = s.id_employee"
//			+ " LEFT JOIN employee_systems_dictionary sd ON s.id_systems = sd.id_systems", nativeQuery = true)
//	List<EmployeeQuery> all();
	
		@Query("SELECT new pl.pzu.trak.domain.EmployeeQuery(e.id_employee, e.first_name, e.last_name, e.team, e.workplace, d.name, g.name, e.employee_status)"
			+ " FROM Employee e"
			+ " LEFT JOIN  e.employeeContract c"
			+ " LEFT JOIN  c.employeeCompanyDictionary d"
			+ " LEFT JOIN  e.employeeSystems s"
			+ " LEFT JOIN  s.employeeSystemsDictionary g")
		List<EmployeeQuery> all();
		

	
}



//@Query(value="select employee.id_pracownika, employee.imie, employee.nazwisko, employee.zespol, employee.stanowisko, employee_sl_spolka.nazwa, employee_sl_systemy_pracownika.nazwa, employee.status_pracownika"
//		+ " from Employee"
//		+ " LEFT JOIN  employee_contract ON employee.id_umowy = employee_contract.id_umowy"
//		+ " LEFT JOIN  employee_sl_spolka ON employee_contract.id_spolki = employee_sl_spolka.id_spolki"
//		+ " LEFT JOIN employee_systems ON employee.id_systemy_pracownika = employee_systems.id_systemy_pracownika"
//		+ " LEFT JOIN employee_sl_systemy_pracownika ON employee_systems.id_systemu = employee_sl_systemy_pracownika.id_systemu", nativeQuery = true)
//List<Employee> findAll();
//
//}



//select employee.imie, employee.nazwisko, employee.zespol, employee.stanowisko, employee_sl_spolka.nazwa, employee_sl_systemy_pracownika.nazwa, employee.status_pracownika, CASE WHEN employee.status_pracownika = 1 THEN 'Aktwyny' ELSE 'Nieaktywny'END as Statuss from employee
//LEFT JOIN employee_contract
//ON employee.id_umowy = employee_contract.id_umowy
//LEFT JOIN employee_sl_spolka
//ON employee_contract.id_spolki = employee_sl_spolka.id_spolki
//LEFT JOIN employee_systems
//ON employee.id_systemu = employee_systems.id_systemu
//LEFT JOIN employee_sl_systemy_pracownika
//ON employee_systems.id_systemu = employee_sl_systemy_pracownika.id_systemu;