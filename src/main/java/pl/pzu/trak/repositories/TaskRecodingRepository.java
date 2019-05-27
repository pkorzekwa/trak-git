package pl.pzu.trak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pzu.trak.domain.TaskRecoding;


@Repository
public interface TaskRecodingRepository extends JpaRepository<TaskRecoding, Integer> {

}
