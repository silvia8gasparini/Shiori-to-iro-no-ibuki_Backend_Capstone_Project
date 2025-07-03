package it.epicode.finalproject.service;

import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.TeaRoomZone;
import it.epicode.finalproject.repository.TeaRoomZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeaRoomZoneService {
    @Autowired
    private TeaRoomZoneRepository tearoomZoneRepository;

    public TeaRoomZone saveZone(TeaRoomZone zone) {
        return tearoomZoneRepository.save(zone);
    }

    public List<TeaRoomZone> getAllZones() {
        return tearoomZoneRepository.findAll();
    }

    public TeaRoomZone getZoneById(int id) throws NotFoundException {
        return tearoomZoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Zona non trovata con id: " + id));
    }

    public void deleteZone(int id) throws NotFoundException {
        TeaRoomZone zone = getZoneById(id);
        tearoomZoneRepository.delete(zone);
    }
}
