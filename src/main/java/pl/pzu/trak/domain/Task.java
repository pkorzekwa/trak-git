package pl.pzu.trak.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sl_zadania")
public class Task
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Short taskId;
	@Column(name = "nazwa_zadania", nullable = false, unique = true, columnDefinition = "NVARCHAR(255)")
	private String taskName;
	@Column(name = "system", nullable = false, columnDefinition = "NCHAR(10)")
	private String taskSystem;
	@Column(name = "waga", columnDefinition = "DECIMAL(18,2)")
	private BigDecimal taskWeight;

	@OneToMany(mappedBy = "task")
	List<TaskRecoding> tasksRecoding;

	public Short getTaskId()
	{
		return taskId;
	}

	public void setTaskId(Short taskId)
	{
		this.taskId = taskId;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getTaskSystem()
	{
		return taskSystem;
	}

	public void setTaskSystem(String taskSystem)
	{
		this.taskSystem = taskSystem;
	}

	public BigDecimal getTaskWeight()
	{
		return taskWeight;
	}

	public void setTaskWeight(BigDecimal taskWeight)
	{
		this.taskWeight = taskWeight;
	}

	public List<TaskRecoding> getTasksRecoding()
	{
		return tasksRecoding;
	}

	public void setTasksRecoding(List<TaskRecoding> tasksRecoding)
	{
		this.tasksRecoding = tasksRecoding;
	}

	 @Override
	    public String toString() {
	        return "Task{" +
	                "id=" + taskId +
	                ", name='" + taskName + '\'' +
	                "}";
	 
	    }

}
