package it.epicode.finalproject.service;

import it.epicode.finalproject.dto.SeasonalColorDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.SeasonalColor;
import it.epicode.finalproject.repository.SeasonalColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeasonalColorService {
    @Autowired
    private SeasonalColorRepository seasonalColorRepository;

    @Autowired
    private MicroSeasonService microSeasonService;

    public SeasonalColor create(SeasonalColorDto dto) throws NotFoundException {
        SeasonalColor color = new SeasonalColor();
        color.setJapaneseName(dto.getJapaneseName());
        color.setItalianName(dto.getItalianName());
        color.setRgb(dto.getRgb());
        color.setDescription(dto.getDescription());
        color.setDetails(dto.getDetails());
        color.setImageUrl(dto.getImageUrl());
        color.setTheme(dto.getTheme());
        color.setMicroSeason(microSeasonService.getById(dto.getMicroSeasonId())); // Associazione

        return seasonalColorRepository.save(color);
    }

    public SeasonalColor getById(int id) throws NotFoundException {
        return seasonalColorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Colore stagionale non trovato con id: " + id));
    }

    public Page<SeasonalColor> getAll(Pageable pageable) {
        return seasonalColorRepository.findAll(pageable);
    }

    public List<SeasonalColor> searchByJapaneseName(String name) {
        return seasonalColorRepository.findByJapaneseNameContainingIgnoreCase(name);
    }

    public List<SeasonalColor> searchByItalianName(String name) {
        return seasonalColorRepository.findByItalianNameContainingIgnoreCase(name);
    }

    public List<SeasonalColor> getDemoColors() {
        return seasonalColorRepository.findAll(PageRequest.of(0, 3)).getContent();
    }

    public SeasonalColor update(int id, SeasonalColor updated) throws NotFoundException {
        SeasonalColor existing = getById(id);
        existing.setJapaneseName(updated.getJapaneseName());
        existing.setItalianName(updated.getItalianName());
        existing.setRgb(updated.getRgb());
        existing.setImageUrl(updated.getImageUrl());
        existing.setTheme(updated.getTheme());
        return seasonalColorRepository.save(existing);
    }

    public void delete(int id) throws NotFoundException {
        SeasonalColor toDelete = getById(id);
        seasonalColorRepository.delete(toDelete);
    }

    public SeasonalColor getByMicroSeasonId(int microSeasonId) throws NotFoundException {
        return seasonalColorRepository.findByMicroSeasonId(microSeasonId)
                .orElseThrow(() -> new NotFoundException("Colore stagionale non trovato per la micro-stagione " + microSeasonId));
    }

}
