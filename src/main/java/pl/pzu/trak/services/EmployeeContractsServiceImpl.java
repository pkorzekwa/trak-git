package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.EmployeeContract;

import pl.pzu.trak.repositories.EmployeeContractRepository;

@Service
public class EmployeeContractsServiceImpl implements EmployeeContractsService {
	
	@Autowired
	private EmployeeContractRepository employeeContractRepository;
	

	@Override
	public List<EmployeeContract> findContracts (Long id_employee) {
		return employeeContractRepository.employeeContractsListContracts(id_employee);
	}

	@Override
	public void add(EmployeeContract employeeContract) {
		employeeContractRepository.save(employeeContract);
	}

}
