package pl.pzu.trak.services;

import java.util.List;

import pl.pzu.trak.domain.EmployeeSystems;

public interface EmployeeSystemsService {
	
	List<EmployeeSystems> findSystems (Long id_employee);

}
