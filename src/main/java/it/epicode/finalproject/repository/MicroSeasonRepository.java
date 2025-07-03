package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.MicroSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MicroSeasonRepository extends JpaRepository<MicroSeason, Integer> {
    // Trova per nome (ignorando maiuscole/minuscole)
    List<MicroSeason> findByItalianNameContainingIgnoreCase(String name);
}
