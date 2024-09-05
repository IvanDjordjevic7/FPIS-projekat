package com.example.FPIS.Art.Expo._4.service;


import com.example.FPIS.Art.Expo._4.model.Manifestacija;
import com.example.FPIS.Art.Expo._4.repository.ManifestacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ManifestacijaService {

    @Autowired
    private ManifestacijaRepository manifestacijaRepository;

    // Dobijanje svih manifestacija
    public List<Manifestacija> getAllManifestacije() {
        return manifestacijaRepository.findAll();
    }

    // Dobijanje manifestacije po ID-u
    public Optional<Manifestacija> getManifestacijaById(int id) {
        return manifestacijaRepository.findById(id);
    }

}
