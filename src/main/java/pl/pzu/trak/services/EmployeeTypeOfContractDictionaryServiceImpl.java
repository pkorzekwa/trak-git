package pl.pzu.trak.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.EmployeeTypeOfContractDictionary;
import pl.pzu.trak.repositories.EmployeeTypeOfContractDictionaryRepository;

@Service
public class EmployeeTypeOfContractDictionaryServiceImpl implements EmployeeTypeOfContractDictionaryService{

	@Autowired
	private EmployeeTypeOfContractDictionaryRepository employeeTypeOfContractDictionaryRepository;
	
	@Override
	public Collection<EmployeeTypeOfContractDictionary> findContractsAll (Long id_employee) {
		return employeeTypeOfContractDictionaryRepository.employeeListContracts(id_employee);
	}
	
	@Override
	public Collection<EmployeeTypeOfContractDictionary> findAll (){
		return employeeTypeOfContractDictionaryRepository.findAll();
	}
	
}
