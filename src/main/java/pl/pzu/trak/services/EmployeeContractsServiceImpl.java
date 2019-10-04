package pl.pzu.trak.services;

import java.util.Collection;
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
	public Collection<EmployeeContract> findContracts (Long id_employee) {
		return employeeContractRepository.employeeContractsListContracts(id_employee);
	}

	@Override
	public void add(EmployeeContract employeeContract) {
		employeeContractRepository.save(employeeContract);
	}

	
	@Override
	public void remove(Long id_contract) {
		employeeContractRepository.deleteById(id_contract);
	}	
	@Override
	public Collection<EmployeeContract> employeeDetailsCompanyById (Long id_employee) {
		return employeeContractRepository.employeeDetailsCompanyById(id_employee);
	}
}
