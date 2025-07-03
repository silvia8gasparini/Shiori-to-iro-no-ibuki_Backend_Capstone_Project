package it.epicode.finalproject.controller;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.TeaRoomZone;
import it.epicode.finalproject.service.TeaRoomZoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tearoom/zones")
public class TeaRoomZoneController {

    @Autowired
    private TeaRoomZoneService teaRoomZoneService;

    @PostMapping
    public TeaRoomZone createZone(@RequestBody @Valid TeaRoomZone zone) {
        return teaRoomZoneService.saveZone(zone);
    }

    @GetMapping
    public List<TeaRoomZone> getAllZones() {
        return teaRoomZoneService.getAllZones();
    }

    @GetMapping("/{id}")
    public TeaRoomZone getZoneById(@PathVariable int id) throws NotFoundException {
        return teaRoomZoneService.getZoneById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteZone(@PathVariable int id) throws NotFoundException {
        teaRoomZoneService.deleteZone(id);
    }
}
