package cinema.service;

import cinema.model.Showing;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.ShowingRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShowingService {
	
	ShowingRepository showingRepository;

	@Autowired
	public ShowingService(ShowingRepository showingRepository) {
		super();
		this.showingRepository = showingRepository;
	}
        
        public List<Showing> findByMovieId(Integer id){
            return showingRepository.findByMovieId(id);
        }
        
        public Showing findOne(Integer id){
            return showingRepository.findOne(id);
        }
}
