package it.epicode.finalproject.service;

import it.epicode.finalproject.dto.ReservationDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.DigitalCard;
import it.epicode.finalproject.model.Reservation;
import it.epicode.finalproject.model.TeaRoomZone;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.DigitalCardRepository;
import it.epicode.finalproject.repository.ReservationRepository;
import it.epicode.finalproject.repository.TeaRoomZoneRepository;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DigitalCardRepository digitalCardRepository;

    @Autowired
    private TeaRoomZoneRepository teaRoomZoneRepository;

    public Reservation createReservation(ReservationDto dto, int zoneId) throws NotFoundException {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + dto.getUserId()));

        DigitalCard digitalCard = digitalCardRepository.findById(dto.getDigitalCardId())
                .orElseThrow(() -> new NotFoundException("Digital card not found with id: " + dto.getDigitalCardId()));

        TeaRoomZone zone = teaRoomZoneRepository.findById(zoneId)
                .orElseThrow(() -> new NotFoundException("TeaRoomZone not found with id: " + zoneId));

        Reservation reservation = new Reservation();
        reservation.setDate(dto.getDate());
        reservation.setTimeSlot(dto.getTimeSlot());
        reservation.setUser(user);
        reservation.setDigitalCard(digitalCard);
        reservation.setTearoomZone(zone);

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
        existing.setTimeSlot(updated.getTimeSlot());
        existing.setTearoomZone(updated.getTearoomZone());
        return reservationRepository.save(existing);
    }

    public void deleteReservation(int id) throws NotFoundException {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }

    public List<Reservation> getByZoneId(int zoneId) {
        return reservationRepository.findByTearoomZoneId(zoneId);
    }
}
