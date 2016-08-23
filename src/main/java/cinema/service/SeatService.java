package cinema.service;

import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.SeatRepository;

public class SeatService {
	
	SeatRepository seatRepository;

	@Autowired
	public SeatService(SeatRepository seatRepository) {
		super();
		this.seatRepository = seatRepository;
	}
}
