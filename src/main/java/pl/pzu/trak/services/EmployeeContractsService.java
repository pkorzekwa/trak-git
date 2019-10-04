package pl.pzu.trak.services;

import java.util.Collection;
import java.util.List;

import pl.pzu.trak.domain.EmployeeContract;

public interface EmployeeContractsService {
	
	Collection<EmployeeContract> findContracts (Long id_employee);
	void add(EmployeeContract employeeContract);
	void remove(Long id_contract);
	Collection<EmployeeContract> employeeDetailsCompanyById (Long id_employee);
}
