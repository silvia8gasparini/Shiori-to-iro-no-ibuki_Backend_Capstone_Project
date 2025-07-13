package it.epicode.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private DigitalCard digitalCard;

    @ManyToOne
    private TeaRoomZone tearoomZone;

    private boolean confirmed = false;
    private boolean cancelled = false;
}
