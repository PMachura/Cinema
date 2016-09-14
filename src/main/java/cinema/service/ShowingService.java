package cinema.service;

import cinema.model.Showing;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.ShowingRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        
        public List<Showing> findAll(){
            return showingRepository.findAll();
        }
        
        public Showing save(Showing showing){
            return showingRepository.save(showing);
        }
        
        public void delete(Integer id){
            showingRepository.delete(id);
          
        }
}
