package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Page<Reservation> findByUserId(int userId, Pageable pageable);
    Page<Reservation> findByDigitalCardId(int cardId, Pageable pageable);
    List<Reservation> findByTearoomZoneId(int zoneId);
    Page<Reservation> findByTearoomZoneId(int zoneId, Pageable pageable);
    Page<Reservation> findByDate(LocalDate date, Pageable pageable);
    long countByDate(LocalDate date);
}
