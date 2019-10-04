package pl.pzu.trak.services;

import java.util.Collection;
import java.util.List;


import pl.pzu.trak.domain.EmployeeTypeOfContractDictionary;

public interface  EmployeeTypeOfContractDictionaryService {
	Collection<EmployeeTypeOfContractDictionary> findContractsAll (Long id_employee);
	
	Collection<EmployeeTypeOfContractDictionary> findAll();

}
