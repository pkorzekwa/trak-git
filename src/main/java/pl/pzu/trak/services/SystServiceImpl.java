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

}
