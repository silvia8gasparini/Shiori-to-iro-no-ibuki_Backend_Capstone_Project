package it.epicode.finalproject.model;

import it.epicode.finalproject.enumeration.TimeSlot;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private int id;

    private LocalDate date;

    @Enumerated
    private TimeSlot timeSlot;

    @ManyToOne
    private User user;

    @ManyToOne
    private DigitalCard digitalCard;

    @ManyToOne
    private TeaRoomZone tearoomZone;

    private boolean confirmed = false;
    private boolean cancelled = false;
}
