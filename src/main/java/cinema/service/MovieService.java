package cinema.service;

import cinema.model.Employee;
import cinema.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.MovieRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        super();
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findOne(Integer id) {
        return movieRepository.findOne(id);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Integer id) {
        movieRepository.delete(id);
    }

    public List<Movie> filteredFindAll(Map<String, String> params) {

        if (params.isEmpty()) {
            return movieRepository.findAll();
        }

        List<Movie> movies = new ArrayList<Movie>(0);
        if (params.containsKey("title")) {
            movies.addAll(findByTitles(params.get("title")));
        }
        return movies;
    }

    public List<Movie> findByTitles(String titles) {
        List<Movie> movies = new ArrayList<Movie>();
        for (String title : titles.split(",")) {
            Movie movie = movieRepository.findByTitle(title);
            if (movie != null) {
                movies.add(movie);
            }
        }
        return movies;
    }
}
