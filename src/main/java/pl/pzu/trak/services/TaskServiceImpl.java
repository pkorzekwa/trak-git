package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pzu.trak.domain.Task;
import pl.pzu.trak.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void add(Task task) {
		taskRepository.save(task);
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public void remove(Short taskId) {
		taskRepository.deleteById(taskId);
	}

	@Override
	public void update(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void save(Task task) {
		taskRepository.save(task);
	}

	@Override
	public Task findOne(Short taskId) {
		return taskRepository.getOne(taskId);
	}

	public Task getTaskById(Short taskId) {
		Task taskById = null;
		for (Task task : taskRepository.findAll()) {
			if (task.getTaskId()==taskId) {
				taskById = task;
				break;
			}
		}
		if (taskById == null) {
			throw new IllegalArgumentException("Brak zadania o wskazanym id:" + taskId);
		}
		return taskById;
	}
	
	public Task getTaskByIdWithDetail(Short taskId) {
		return taskRepository.findByTaskWithDetail(taskId);
	}
}
