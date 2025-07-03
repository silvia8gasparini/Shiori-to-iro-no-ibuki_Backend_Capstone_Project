package it.epicode.finalproject.service;

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

}
