package it.epicode.finalproject.repository;

import it.epicode.finalproject.model.SeasonalColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonalColorRepository extends JpaRepository<SeasonalColor, Integer> {
    // Ricerca case-insensitive per nome giapponese o italiano
    List<SeasonalColor> findByJapaneseNameContainingIgnoreCase(String name);
    List<SeasonalColor> findByItalianNameContainingIgnoreCase(String name);
}
