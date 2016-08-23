package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.GenreRepository;

public class GenreService {

	GenreRepository genreRepository;

	@Autowired
	public GenreService(GenreRepository genreRepository) {
		super();
		this.genreRepository = genreRepository;
	}
	
}
