package pl.pzu.trak.services;

import java.util.List;

import pl.pzu.trak.domain.EmployeeContract;

public interface EmployeeContractsService {
	
	List<EmployeeContract> findContracts (Long id_employee);

}
