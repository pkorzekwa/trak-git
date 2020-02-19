package pl.pzu.trak.services;
import java.util.List;
import pl.pzu.trak.domain.Syst;
public interface SystService {
//	void add(pl.pzu.trak.domain.System system);
	List<Syst> findAll();
//	void remove(String systemId);
//	void update(pl.pzu.trak.domain.System system);
//	void save(pl.pzu.trak.domain.System system);
//	pl.pzu.trak.domain.System findOne(String systemId);
//	pl.pzu.trak.domain.System getSystemById(String systemId);
	void add(Syst syst);
	void remove(Short systemId);
	void update(Syst syst);
	Syst findOne(Short systemId);
	void updateSystem(Short systemId, String systemName);

}
