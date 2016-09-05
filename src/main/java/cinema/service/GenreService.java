package cinema.service;

import cinema.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.GenreRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

	GenreRepository genreRepository;

	@Autowired
	public GenreService(GenreRepository genreRepository) {
		super();
		this.genreRepository = genreRepository;
	}
        
        public List<Genre> findAll(){
            return genreRepository.findAll();
        }
	
}
