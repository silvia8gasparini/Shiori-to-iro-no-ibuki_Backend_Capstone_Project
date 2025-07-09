package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.SeasonalColor;
import it.epicode.finalproject.service.SeasonalColorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colors")
public class SeasonalColorController {

    @Autowired
    private SeasonalColorService seasonalColorService;

    @PostMapping
    public SeasonalColor create(@RequestBody @Valid SeasonalColor color) {
        return seasonalColorService.create(color);
    }

    @GetMapping("/{id}")
    public SeasonalColor getById(@PathVariable int id) throws NotFoundException {
        return seasonalColorService.getById(id);
    }

    @GetMapping
    public Page<SeasonalColor> getAll(Pageable pageable) {
        return seasonalColorService.getAll(pageable);
    }

    @GetMapping("/search/japanese")
    public List<SeasonalColor> searchByJapaneseName(@RequestParam String name) {
        return seasonalColorService.searchByJapaneseName(name);
    }

    @GetMapping("/search/italian")
    public List<SeasonalColor> searchByItalianName(@RequestParam String name) {
        return seasonalColorService.searchByItalianName(name);
    }

    @GetMapping("/demo")
    public List<SeasonalColor> getDemoColors() {
        return seasonalColorService.getDemoColors();
    }

    @PutMapping("/{id}")
    public SeasonalColor update(@PathVariable int id, @RequestBody @Valid SeasonalColor updated) throws NotFoundException {
        return seasonalColorService.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) throws NotFoundException {
        seasonalColorService.delete(id);
    }

    @GetMapping("/microseason/{microSeasonId}")
    public SeasonalColor getByMicroSeasonId(@PathVariable int microSeasonId) throws NotFoundException {
        return seasonalColorService.getByMicroSeasonId(microSeasonId);
    }


}
