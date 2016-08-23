package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.HallRepository;

public class HallService {

	HallRepository hallRepository;

	@Autowired
	public HallService(HallRepository hallRepository) {
		super();
		this.hallRepository = hallRepository;
	}
}
