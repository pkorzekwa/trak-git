package pl.pzu.trak.services;

import java.util.List;


import pl.pzu.trak.domain.EmployeeTypeOfContractDictionary;

public interface  EmployeeTypeOfContractDictionaryService {
	List<EmployeeTypeOfContractDictionary> findContractsAll (Long id_employee);
	
	List<EmployeeTypeOfContractDictionary> findAll();

}
