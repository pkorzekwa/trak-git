package pl.pzu.trak.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.EmployeeCompanyDictionary;
import pl.pzu.trak.domain.EmployeeSystemsDictionary;
import pl.pzu.trak.repositories.EmployeeCompanyDictionaryRepository;
import pl.pzu.trak.repositories.EmployeeSystemsDictionaryRepository;

@Service
public class EmployeeSystemsDictionaryServiceImpl implements EmployeeSystemsDictionaryService{

	@Autowired
	private EmployeeSystemsDictionaryRepository employeeSystemsDictionaryRepository;
	
	@Override
	public Collection<EmployeeSystemsDictionary> findAllNameSystemsDictionary(){
		return employeeSystemsDictionaryRepository.findAllNameSystemsDictionary();
	}
}
