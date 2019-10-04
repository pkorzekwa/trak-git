package pl.pzu.trak.services;

import java.util.Collection;
import java.util.List;

import pl.pzu.trak.domain.EmployeeCompanyDictionary;

public interface EmployeeCompanyDictionaryService {

	Collection<EmployeeCompanyDictionary> findAllNameCompanyDictionary();
	Collection<EmployeeCompanyDictionary> findAll();
}
