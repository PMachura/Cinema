package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.MovieRepository;

public class MovieService {

	MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}
}
