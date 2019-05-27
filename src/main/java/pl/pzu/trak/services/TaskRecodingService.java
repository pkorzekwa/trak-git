package pl.pzu.trak.services;

import java.util.List;
import pl.pzu.trak.domain.TaskRecoding;

public interface TaskRecodingService
{
	void add(TaskRecoding taskRecoding);
	List<TaskRecoding> findAll();
	void remove(Integer taskRedodingId);
	void update(TaskRecoding taskRecoding);
	void save(TaskRecoding taskRecoding);
	TaskRecoding findOne(Integer taskRecodingId);
}
