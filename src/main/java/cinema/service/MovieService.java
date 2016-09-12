package cinema.service;

import cinema.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.MovieRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}
        
        public List<Movie> findAll(){
           return movieRepository.findAll();
        }
        
        public Movie findOne(Integer id){
            return movieRepository.findOne(id);
        }
        
        public Movie save(Movie movie){
            return movieRepository.save(movie);
        }
        
        public void delete(Integer id){
            movieRepository.delete(id);
        }
}
