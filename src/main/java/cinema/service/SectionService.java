package cinema.service;

import cinema.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import cinema.repository.SectionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        super();
        this.sectionRepository = sectionRepository;
    }

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Section findOne(Integer id) {
        return sectionRepository.findOne(id);
    }

    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    public void delete(Integer id) {
    	sectionRepository.delete(id);
    }

}
