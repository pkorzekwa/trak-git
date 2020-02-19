package pl.pzu.trak.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pzu.trak.domain.Syst;

import pl.pzu.trak.repositories.SystRepository;

@Service
public class SystServiceImpl implements SystService{

	@Autowired
	private SystRepository systRepository;
	
	@Override
	public List<Syst> findAll() {
		return systRepository.findAll();
	}
	
	@Override
	public void add(Syst syst) {
		systRepository.save(syst);
	}
	
	@Override
	public void update(Syst syst) {
		systRepository.save(syst);
	}

	@Override
	public void remove(Short systemId) {
		systRepository.deleteById(systemId);
	}
	
	@Override
	public Syst findOne(Short systemId) {
		return systRepository.getOne(systemId);
	}
	
	public void updateSystem(Short systemId, String systemName)
	{
		systRepository.updateSystem(systemId, systemName);
	}
}
