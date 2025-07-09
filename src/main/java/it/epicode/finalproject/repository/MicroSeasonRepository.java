package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.MicroSeason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MicroSeasonRepository extends JpaRepository<MicroSeason, Integer> {
    List<MicroSeason> findAllByJapaneseName(String japaneseName);
    List<MicroSeason> findByStartDateBeforeAndEndDateAfter(LocalDate after, LocalDate before);
    List<MicroSeason> findByItalianNameContainingIgnoreCase(String name);
    boolean existsByJapaneseNameAndStartDate(String japaneseName, LocalDate startDate);
}
