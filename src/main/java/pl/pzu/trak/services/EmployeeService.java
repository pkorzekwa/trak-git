package pl.pzu.trak.services;

import java.util.List;

import pl.pzu.trak.domain.Employee;
import pl.pzu.trak.domain.EmployeeQuery;


public interface EmployeeService {
	List<Employee> all();
	
}
