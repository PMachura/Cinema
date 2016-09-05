package cinema.service;

import cinema.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.SeatRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
	
	SeatRepository seatRepository;

	@Autowired
	public SeatService(SeatRepository seatRepository) {
		super();
		this.seatRepository = seatRepository;
	}
        
        public List<Seat> findEmptySeatsForShowing(Integer hallId, Integer showingId){
            return seatRepository.findEmptySeatsForShowing(hallId, showingId);
        }
        
        public Seat save(Seat seat){
            return seatRepository.save(seat);
        }
        
        public List<Seat> findByIdIn(Integer [] ids){
            return seatRepository.findByIdIn(ids);
        }
}
