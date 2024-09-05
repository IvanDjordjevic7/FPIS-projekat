package com.example.FPIS.Art.Expo._4.controller;

import com.example.FPIS.Art.Expo._4.model.Manifestacija;
import com.example.FPIS.Art.Expo._4.service.ManifestacijaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/manifestacije")
@CrossOrigin("http://localhost:4200")
public class ManifestacijaController {

    @Autowired
    private ManifestacijaService manifestacijaService;

    // Endpoint za dobijanje svih manifestacija
    @GetMapping("/all")
    public List<Manifestacija> getAllManifestacije() {
        return manifestacijaService.getAllManifestacije();
    }

    // Endpoint za dobijanje manifestacije po ID-u
    @GetMapping("/{id}")
    public Optional<Manifestacija> getManifestacijaById(@PathVariable int id) {
        return manifestacijaService.getManifestacijaById(id);
    }


}
