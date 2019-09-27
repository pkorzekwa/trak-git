package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.Employee;
import pl.pzu.trak.domain.EmployeeQuery;

import pl.pzu.trak.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeQuery> all(){
		return employeeRepository.all();
	}
	
	public Employee findOne(Long id_employee) {
		return employeeRepository.getOne(id_employee);
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void add(Employee employee)
	{
		employeeRepository.save(employee);
	}
	
	public void updateEmployee(Long id_employee, String first_name, String last_name, String team, String workplace, boolean employee_status)
	{
		employeeRepository.updateEmployee(id_employee, first_name, last_name, team, workplace, employee_status);
	}
	
}

