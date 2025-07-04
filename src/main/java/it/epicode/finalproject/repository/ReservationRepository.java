package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Page<Reservation> findByUserId(int userId, Pageable pageable);
    Page<Reservation> findByDigitalCardId(int cardId, Pageable pageable);
    Page<Reservation> findByDate(LocalDate date, Pageable pageable);
    long countByDate(LocalDate date);
}
