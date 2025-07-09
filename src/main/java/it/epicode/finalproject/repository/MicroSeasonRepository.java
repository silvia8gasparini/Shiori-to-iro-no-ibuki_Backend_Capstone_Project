package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.MicroSeason;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MicroSeasonRepository extends JpaRepository<MicroSeason, Integer> {
    List<MicroSeason> findAllByJapaneseName(String japaneseName);
    List<MicroSeason> findByStartDateBeforeAndEndDateAfter(LocalDate after, LocalDate before);
    List<MicroSeason> findByItalianNameContainingIgnoreCase(String name);
    boolean existsByJapaneseNameAndStartDate(String japaneseName, LocalDate startDate);

    @Query("SELECT m FROM MicroSeason m WHERE m.startDate > :now ORDER BY m.startDate ASC")
    List<MicroSeason> findNextSeasons(@Param("now") LocalDate now, Pageable pageable);
}
