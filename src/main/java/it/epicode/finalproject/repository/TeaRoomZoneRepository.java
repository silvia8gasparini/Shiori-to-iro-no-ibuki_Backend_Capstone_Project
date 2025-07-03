package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.TeaRoomZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeaRoomZoneRepository extends JpaRepository<TeaRoomZone, Integer> {
    Optional<TeaRoomZone> findByName(String name);
}
