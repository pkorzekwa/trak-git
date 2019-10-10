package pl.pzu.trak.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.EmployeeSystems;
import pl.pzu.trak.domain.EmployeeSystemsQuery;
import pl.pzu.trak.repositories.EmployeeSystemsRepository;

@Service
public class EmployeeSystemsServiceImpl implements EmployeeSystemsService {

	@Autowired
	private EmployeeSystemsRepository employeeSystemsRepository;
	
	@Override
	public EmployeeSystems findSystemsById (Long id_employee) {
		return employeeSystemsRepository.findById(id_employee).get();
	}
	
	@Override
	public Collection<EmployeeSystems> findSystems (Long id_employee) {
		return employeeSystemsRepository.employeeListSystems(id_employee);
	}
	@Override
	public Collection<EmployeeSystems> employeeDetails (Long id_employee) {
		return employeeSystemsRepository.employeeDetails(id_employee);
	}
	@Override
	public Collection<EmployeeSystems> employeeDetailsSystemsById (Long id_employee) {
		return employeeSystemsRepository.employeeDetailsSystemsById(id_employee);
	}
//	@Override
//	public Collection<EmployeeSystems> employeeDetailsCompany (Long id_employee) {
//		return employeeSystemsRepository.employeeDetailsCompany(id_employee);
//	}
	@Override
	public void add(EmployeeSystems employeeSystems) {
		employeeSystemsRepository.save(employeeSystems);
	}
	
	@Override
	public void remove(Long id_employee_systems) {
		employeeSystemsRepository.deleteById(id_employee_systems);
	}	
	

}
