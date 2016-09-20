package cinema.service;

import cinema.model.Section;
import cinema.model.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import cinema.repository.SectionRepository;
import cinema.repository.TopicRepository;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        super();
        this.topicRepository = topicRepository;
    }

    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Topic findOne(Integer id) {
        return topicRepository.findOne(id);
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public void delete(Integer id) {
    	topicRepository.delete(id);
    }

}
