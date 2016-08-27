package cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.model.Seat;

public interface SeatRepository extends JpaRepository <Seat, Integer> {

}
