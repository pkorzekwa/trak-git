package pl.pzu.trak.services;
import java.util.List;
import pl.pzu.trak.domain.Task;

public interface TaskService {
	void add(Task task);
	List<Task> findAll();
	void remove(Short taskId);
	void update(Task task);
	void save(Task task);
	Task findOne(Short taskId);
	Task getTaskById(Short taskId);
	Task getTaskByIdWithDetail(Short taskId);
}
