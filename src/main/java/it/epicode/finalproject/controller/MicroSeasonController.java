package it.epicode.finalproject.controller;

import it.epicode.finalproject.dto.MicroSeasonDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.MicroSeason;
import it.epicode.finalproject.service.MicroSeasonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/microseasons")
public class MicroSeasonController {

    @Autowired
    private MicroSeasonService microSeasonService;

    @PostMapping
    public MicroSeason create(@RequestBody @Valid MicroSeason microSeason) {
        return microSeasonService.create(microSeason);
    }

    @GetMapping("/{id}")
    public MicroSeason getById(@PathVariable int id) throws NotFoundException {
        return microSeasonService.getById(id);
    }

    @GetMapping
    public Page<MicroSeason> getAll(Pageable pageable) {
        return microSeasonService.getAll(pageable);
    }

    @GetMapping("/dto")
    public List<MicroSeasonDto> getAllAsDto() {
        return microSeasonService.getAllDto();
    }

    @GetMapping("/search")
    public List<MicroSeason> searchByName(@RequestParam String name) {
        return microSeasonService.searchByName(name);
    }

    @GetMapping("/demo")
    public List<MicroSeason> getDemoSeasons() {
        return microSeasonService.getDemoSeasons();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        microSeasonService.delete(id);
    }

}
