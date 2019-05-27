package pl.pzu.trak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.pzu.trak.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Short>{
	@Query("select distinct z from Task z left join fetch "+
		   "z.tasksRecoding r where z.id=?1")
	Task findByTaskWithDetail(Short taskId);
}
