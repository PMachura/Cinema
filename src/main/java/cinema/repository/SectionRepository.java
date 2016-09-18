package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Section;

public interface SectionRepository extends JpaRepository<Section,Integer> {

}
