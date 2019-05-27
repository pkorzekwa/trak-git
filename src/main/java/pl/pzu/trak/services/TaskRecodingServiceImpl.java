package pl.pzu.trak.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pzu.trak.domain.TaskRecoding;
import pl.pzu.trak.repositories.TaskRecodingRepository;

@Service
public class TaskRecodingServiceImpl implements TaskRecodingService
{
	@Autowired
	private TaskRecodingRepository taskRecodingRepository;
	
	@Override
	public void add(TaskRecoding taskRecoding) {
		taskRecodingRepository.save(taskRecoding);
	}

	@Override
	public List<TaskRecoding> findAll() {
		return taskRecodingRepository.findAll();
	}

	@Override
	public void remove(Integer taskRecodingId) {
		taskRecodingRepository.deleteById(taskRecodingId);
	}

	@Override
	public void update(TaskRecoding taskRecoding) {
		taskRecodingRepository.save(taskRecoding);
	}

	@Override
	public void save(TaskRecoding taskRecoding) {
		taskRecodingRepository.save(taskRecoding);
	}

	@Override
	public TaskRecoding findOne(Integer taskRecodingId) {
		return taskRecodingRepository.getOne(taskRecodingId);
	}
}
