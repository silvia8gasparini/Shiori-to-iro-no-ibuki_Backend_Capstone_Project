package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.DigitalCard;
import it.epicode.finalproject.model.Reservation;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.DigitalCardRepository;
import it.epicode.finalproject.repository.ReservationRepository;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DigitalCardRepository digitalCardRepository;

    public Reservation createReservation(Reservation reservation, int userId, int cardId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        DigitalCard digitalCard = digitalCardRepository.findById(cardId)
                .orElseThrow(() -> new NotFoundException("Digital card not found with id: " + cardId));

        reservation.setUser(user);
        reservation.setDigitalCard(digitalCard);

        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(int id) throws NotFoundException {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found with id: " + id));
    }

    public Page<Reservation> getAllReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    public Page<Reservation> getByUserId(int userId, Pageable pageable) {
        return reservationRepository.findByUserId(userId, pageable);
    }

    public Page<Reservation> getByCardId(int cardId, Pageable pageable) {
        return reservationRepository.findByDigitalCardId(cardId, pageable);
    }

    public Page<Reservation> getByDate(LocalDate date, Pageable pageable) {
        return reservationRepository.findByDate(date, pageable);
    }

    public long countByDate(LocalDate date) {
        return reservationRepository.countByDate(date);
    }

    public Reservation updateReservation(int id, Reservation updated) throws NotFoundException {
        Reservation existing = getReservationById(id);
        existing.setDate(updated.getDate());
        return reservationRepository.save(existing);
    }

    public void deleteReservation(int id) throws NotFoundException {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
}
