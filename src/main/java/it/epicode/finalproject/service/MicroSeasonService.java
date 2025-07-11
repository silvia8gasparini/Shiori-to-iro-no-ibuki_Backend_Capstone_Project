package it.epicode.finalproject.service;

import it.epicode.finalproject.dto.MicroSeasonDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.MicroSeason;
import it.epicode.finalproject.repository.MicroSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MicroSeasonService {
    @Autowired
    private MicroSeasonRepository microSeasonRepository;

    public MicroSeason create(MicroSeason microSeason) {
        return microSeasonRepository.save(microSeason);
    }

    public MicroSeason getById(int id) throws NotFoundException {
        return microSeasonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Micro-stagione non trovata con id: " + id));
    }

    public Page<MicroSeason> getAll(Pageable pageable) {
        return microSeasonRepository.findAll(pageable);
    }

    public List<MicroSeason> searchByName(String name) {
        return microSeasonRepository.findByItalianNameContainingIgnoreCase(name);
    }

    public List<MicroSeason> getDemoSeasons() {
        return microSeasonRepository.findAll(PageRequest.of(0, 3)).getContent();
    }

    public void delete(int id) throws NotFoundException {
        MicroSeason toDelete = getById(id);
        microSeasonRepository.delete(toDelete);
    }

    public List<MicroSeasonDto> getAllDto() {
        return microSeasonRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    private MicroSeasonDto convertToDto(MicroSeason microSeason) {
        MicroSeasonDto microSeasonDto = new MicroSeasonDto();
        microSeasonDto.setJapaneseName(microSeason.getJapaneseName());
        microSeasonDto.setItalianName(microSeason.getItalianName());
        microSeasonDto.setDisplayPeriod(microSeason.getDisplayPeriod());
        return microSeasonDto;
    }

    public List<MicroSeason> getByDate(LocalDate date) {
        return microSeasonRepository.findByStartDateBeforeAndEndDateAfter(date, date);
    }

    public MicroSeason getCurrentMicroSeason() throws NotFoundException {
        LocalDate today = LocalDate.now();
        List<MicroSeason> results = microSeasonRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqual(today, today);

        if (results.isEmpty()) {
            throw new NotFoundException("Nessuna micro-stagione attuale trovata");
        }

        return results.get(0);
    }

    public List<MicroSeason> getNextSeasons(int count) {
        Pageable pageable = PageRequest.of(0, count);
        return microSeasonRepository.findNextSeasons(LocalDate.now(), pageable);
    }

}
