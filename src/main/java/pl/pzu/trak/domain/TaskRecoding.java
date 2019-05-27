package pl.pzu.trak.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sl_zadania_przekodowania")
public class TaskRecoding
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int taskRecodingId;
	
	@Column(name="nazwa_zadania", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
	private String taskRecodingName;

	@ManyToOne
	@JoinColumn(name = "id_zadania")
	private Task task;


	public String getTaskRecodingName()
	{
		return taskRecodingName;
	}

	public void setTaskRecodingName(String taskRecodingName)
	{
		this.taskRecodingName = taskRecodingName;
	}

	public Task getTask()
	{
		return task;
	}

	public void setTask(Task task)
	{
		this.task = task;
	}

	public int getTaskRecodingId()
	{
		return taskRecodingId;
	}

	public void setTaskRecodingId(int taskRecodingId)
	{
		this.taskRecodingId = taskRecodingId;
	}
	
	public TaskRecoding withTask(Task task)
	{
		this.task = task;
		return this;
	}

	@Override
	public String toString()
	{
		return "TaskRecoding [taskRecodingId=" + taskRecodingId + ", taskRecodingName=" + taskRecodingName + ", task="
				+ task + "]";
	}
	


}