package pl.pzu.trak.services;

import java.util.Collection;
import java.util.List;


import pl.pzu.trak.domain.EmployeeSystems;
import pl.pzu.trak.domain.EmployeeSystemsQuery;

public interface EmployeeSystemsService {
	
	Collection<EmployeeSystems> findSystems (Long id_employee);
	void add(EmployeeSystems employeeSystems);
	void remove(Long id_employee_systems);
	Collection<EmployeeSystems> employeeDetails (Long id_employee);
	Collection<EmployeeSystems> employeeDetailsSystemsById (Long id_employee);
	//Collection<EmployeeSystems> employeeDetailsCompany (Long id_employee);
	EmployeeSystems findSystemsById (Long id_employee);

}
