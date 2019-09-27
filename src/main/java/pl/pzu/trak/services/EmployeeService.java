package pl.pzu.trak.services;

import java.util.List;

import pl.pzu.trak.domain.Employee;
import pl.pzu.trak.domain.EmployeeQuery;

public interface EmployeeService {
	List<EmployeeQuery> all();
	Employee findOne(Long id_employee);
	List<Employee> findAll();
	void add(Employee employee);
	void updateEmployee(Long id_employee, String first_name, String last_name, String team, String workplace, boolean employee_status);
}
