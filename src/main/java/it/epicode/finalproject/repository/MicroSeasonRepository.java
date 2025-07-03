package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.MicroSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MicroSeasonRepository extends JpaRepository<MicroSeason, Integer> {
    // Trova tutte le microstagioni attive in una data specifica
    List<MicroSeason> findByStartDateBeforeAndEndDateAfter(LocalDate date1, LocalDate date2);

    // Trova per nome (ignorando maiuscole/minuscole)
    List<MicroSeason> findByNameContainingIgnoreCase(String name);
}
