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

}
