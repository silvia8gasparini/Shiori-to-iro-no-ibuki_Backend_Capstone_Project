package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Tutte le prenotazioni di un utente
    Page<Reservation> findByUserId(int userId, Pageable pageable);

    // Tutte le prenotazioni associate a una tessera
    Page<Reservation> findByDigitalCardId(int cardId, Pageable pageable);

    // Prenotazioni per data
    Page<Reservation> findByDate(LocalDate date, Pageable pageable);

    // Contare le prenotazioni in una certa data (per esempio per sapere se è tutto pieno)
    long countByDate(LocalDate date);
}
