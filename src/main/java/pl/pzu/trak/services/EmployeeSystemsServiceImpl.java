package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.EmployeeSystems;
import pl.pzu.trak.repositories.EmployeeSystemsRepository;

@Service
public class EmployeeSystemsServiceImpl implements EmployeeSystemsService {

	@Autowired
	private EmployeeSystemsRepository employeeSystemsRepository;
	
	@Override
	public List<EmployeeSystems> findSystems (Long id_employee) {
		return employeeSystemsRepository.employeeListSystems(id_employee);
	}
}
