package it.epicode.finalproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TeaRoomZone {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String imageUrl;
}
