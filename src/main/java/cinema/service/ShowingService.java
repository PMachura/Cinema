package cinema.service;

import cinema.model.Showing;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.ShowingRepository;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ShowingService extends BaseService {

    ShowingRepository showingRepository;

    @Autowired
    public ShowingService(ShowingRepository showingRepository) {
        super();
        this.showingRepository = showingRepository;
    }

    public List<Showing> findByMovieId(Integer id) {
        return showingRepository.findByMovieId(id);
    }

    public Showing findOne(Integer id) {
        return showingRepository.findOne(id);
    }

    public List<Showing> findAll() {
        return showingRepository.findAll();
    }

    public Showing save(Showing showing) {
        return showingRepository.save(showing);
    }

    public void delete(Integer id) {
        showingRepository.delete(id);

    }

    public List<Showing> filteredFindAll(Map<String, String> params) {
        List<Showing> showings = showingRepository.findAll();
        if (params.isEmpty()) {
            return showings;
        }

        if (params.containsKey("movieTitle") && !params.get("movieTitle").equals("")) {
            showings = filterByMoviesTitle(showings, params.get("movieTitle"));
        }
        if (params.containsKey("date") && !params.get("date").equals("")) {
            showings = filterByDates(showings, params.get("date"));
        }

        return showings;
    }

    public List<Showing> filterByDates(List<Showing> showings, String dates) {
        List<Showing> filteredShowings = new ArrayList<Showing>();
        for (String date : dates.split(",")) {
            try {
                LocalDate parsedDate = LocalDate.parse(date, dateTimeFormatter);
                for (Showing showing : showings) {
                    if (showing.getDate().equals(parsedDate)) {
                        filteredShowings.add(showing);
                    }
                }
            } catch (DateTimeParseException exception) {
            }

        }
        return filteredShowings;
    }

    public List<Showing> filterByMoviesTitle(List<Showing> showings, String titles) {
        List<Showing> filteredShowings = new ArrayList<Showing>();
        for (String title : titles.split(",")) {
            for (Showing showing : showings) {
                if (showing.getMovie().getTitle().equals(title)) {
                    filteredShowings.add(showing);
                }
            }
        }

        return filteredShowings;
    }
}
