package pl.pzu.trak.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.EmployeeSystemsRoleDictionary;
import pl.pzu.trak.repositories.EmployeeSystemsRoleDictionaryRepository;

@Service
public class EmployeeSystemsRoleDictionaryServiceImpl implements EmployeeSystemsRoleDictionaryService{

	@Autowired
	private EmployeeSystemsRoleDictionaryRepository employeeSystemsRoleDictionaryRepository;
	
	@Override
	public Collection<EmployeeSystemsRoleDictionary> findAll(){
		return employeeSystemsRoleDictionaryRepository.findAll();
	}
}
