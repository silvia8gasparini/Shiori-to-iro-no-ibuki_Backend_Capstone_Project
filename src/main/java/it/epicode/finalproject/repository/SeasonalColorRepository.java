package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.SeasonalColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonalColorRepository extends JpaRepository<SeasonalColor, Integer> {
    List<SeasonalColor> findAllByJapaneseName(String japaneseName);
    List<SeasonalColor> findByJapaneseNameContainingIgnoreCase(String name);
    List<SeasonalColor> findByItalianNameContainingIgnoreCase(String name);
    boolean existsByJapaneseNameAndMicroSeason_JapaneseName(String japaneseName, String microSeasonJapaneseName);
}
