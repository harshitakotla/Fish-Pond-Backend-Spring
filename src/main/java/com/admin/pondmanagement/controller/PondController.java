package com.admin.pondmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.admin.pondmanagement.model.Pond;
import com.admin.pondmanagement.model.Sensor;
import com.admin.pondmanagement.repository.PondRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/ponds")
@CrossOrigin(origins = "http://localhost:4200")
public class PondController {

    @Autowired
    private PondRepository pondRepository;

    @GetMapping
    public List<Pond> getAllPonds() {
        return pondRepository.findAll();
    }

    @PostMapping
    public Pond addPond(@RequestParam("name") String name,
                        @RequestParam("location") String location,
                        @RequestParam("size") String size,
                        @RequestParam("image") MultipartFile image) throws IOException {
        Pond pond = new Pond();
        pond.setName(name);
        pond.setLocation(location);
        pond.setSize(size);
        pond.setImage(image.getBytes()); // Convert image file to byte array
        return pondRepository.save(pond);
    }

    @GetMapping("/{pondId}/sensors")
    public List<Sensor> getSensorsByPond(@PathVariable String pondId) {
        Pond pond = pondRepository.findById(pondId)
                .orElseThrow(() -> new RuntimeException("Pond not found with ID: " + pondId));
        return pond.getSensors();
    }

    @PostMapping("/{pondId}/sensors")
    public Pond addSensorToPond(@PathVariable String pondId, @RequestBody Sensor sensor) {
        Pond pond = pondRepository.findById(pondId)
                .orElseThrow(() -> new RuntimeException("Pond not found with ID: " + pondId));
        pond.addSensor(sensor);
        return pondRepository.save(pond);
    }

    @GetMapping("/{pondId}")
    public Optional<Pond> getPondById(@PathVariable String pondId) {
        return pondRepository.findById(pondId);
    }

    @PutMapping("/{pondId}")
    public Pond updatePond(@PathVariable String pondId, @RequestBody Pond updatedPond) {
        return pondRepository.findById(pondId)
                .map(pond -> {
                    pond.setName(updatedPond.getName());
                    pond.setLocation(updatedPond.getLocation());
                    pond.setSize(updatedPond.getSize());
                    pond.setSensors(updatedPond.getSensors());
                    pond.setImage(updatedPond.getImage()); // Update image if provided
                    return pondRepository.save(pond);
                })
                .orElseThrow(() -> new RuntimeException("Pond not found with ID: " + pondId));
    }

    @DeleteMapping("/{pondId}")
    public String deletePond(@PathVariable String pondId) {
        pondRepository.deleteById(pondId);
        return "Pond with ID " + pondId + " has been deleted.";
    }
}
