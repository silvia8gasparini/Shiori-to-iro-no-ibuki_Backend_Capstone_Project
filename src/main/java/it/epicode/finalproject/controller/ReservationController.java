package it.epicode.finalproject.controller;

import io.jsonwebtoken.security.Request;
import it.epicode.finalproject.dto.ReservationDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.Reservation;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.ReservationRepository;
import it.epicode.finalproject.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation create(@RequestBody @Valid ReservationDto reservationDto,
                              @RequestParam int zoneId) throws NotFoundException {
        return reservationService.createReservation(reservationDto, zoneId);
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable int id) throws NotFoundException {
        return reservationService.getReservationById(id);
    }

    @GetMapping
    public Page<Reservation> getAll(Pageable pageable) {
        return reservationService.getAllReservations(pageable);
    }

    @GetMapping("/user/{userId}")
    public Page<Reservation> getByUserId(@PathVariable int userId, Pageable pageable) {
        return reservationService.getByUserId(userId, pageable);
    }

    @GetMapping("/me")
    public Page<Reservation> getMyReservations(@AuthenticationPrincipal User user, Pageable pageable) {
        return reservationService.getByUserId(user.getId(), pageable);
    }

    @GetMapping("/by-card/{cardId}")
    public Page<Reservation> getByCardId(@PathVariable int cardId, Pageable pageable) {
        return reservationService.getByCardId(cardId, pageable);
    }

    @GetMapping("/by-date")
    public Page<Reservation> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                       Pageable pageable) {
        return reservationService.getByDate(date, pageable);
    }

    @GetMapping("/by-zone/{zoneId}")
    public List<Reservation> getByZone(@PathVariable int zoneId) {
        return reservationService.getByZoneId(zoneId);
    }

    @GetMapping("/count")
    public long countByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reservationService.countByDate(date);
    }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable int id, @RequestBody @Valid Reservation updated) throws NotFoundException {
        return reservationService.updateReservation(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        reservationService.deleteReservation(id);
    }

}
