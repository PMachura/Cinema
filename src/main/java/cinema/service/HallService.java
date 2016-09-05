package cinema.service;

import cinema.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;

import cinema.repository.HallRepository;
import org.springframework.stereotype.Service;

@Service
public class HallService {

	HallRepository hallRepository;

	@Autowired
	public HallService(HallRepository hallRepository) {
		super();
		this.hallRepository = hallRepository;
	}
        
        public Hall findOne(Integer id){
            return hallRepository.findOne(id);
        }
}
