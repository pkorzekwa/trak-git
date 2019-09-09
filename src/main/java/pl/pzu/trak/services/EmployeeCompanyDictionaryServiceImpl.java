package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.EmployeeCompanyDictionary;
import pl.pzu.trak.repositories.EmployeeCompanyDictionaryRepository;

@Service
public class EmployeeCompanyDictionaryServiceImpl implements EmployeeCompanyDictionaryService{
	
	@Autowired
	private EmployeeCompanyDictionaryRepository employeeCompanyDictionaryRepository;
	
	@Override
	public List<EmployeeCompanyDictionary> findAll(){
		return employeeCompanyDictionaryRepository.findAll();
	}

}
