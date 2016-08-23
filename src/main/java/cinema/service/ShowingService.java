package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.ShowingRepository;

public class ShowingService {
	
	ShowingRepository showingRepository;

	@Autowired
	public ShowingService(ShowingRepository showingRepository) {
		super();
		this.showingRepository = showingRepository;
	}
}
