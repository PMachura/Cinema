package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Hall;
import cinema.model.Section;
import cinema.model.Topic;

public interface TopicRepository extends JpaRepository<Topic,Integer> {

}
