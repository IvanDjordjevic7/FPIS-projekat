package com.example.FPIS.Art.Expo._4.controller;

import com.example.FPIS.Art.Expo._4.model.Izlozba;
import com.example.FPIS.Art.Expo._4.model.Umetnik;
import com.example.FPIS.Art.Expo._4.service.IzlozbaService;

import com.example.FPIS.Art.Expo._4.service.UmetnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/izlozbe")
@CrossOrigin("http://localhost:4200")
public class IzlozbaController {

    @Autowired
    private IzlozbaService izlozbaService;

    @Autowired
    private UmetnikService umetnikService;

    // Endpoint za dobijanje svih izložbi vezanih za odabranu manifestaciju
    @GetMapping("/manifestacija/{id}")
    public List<Izlozba> getIzlozbeByManifestacijaId(@PathVariable int id) {
        return izlozbaService.getIzlozbeByManifestacijaId(id);
    }

    // Endpoint za dobijanje izložbe po ID-u
    @GetMapping("/{id}")
    public Optional<Izlozba> getIzlozbaById(@PathVariable int id) {
        return izlozbaService.getIzlozbaById(id);
    }



    // Endpoint za dobijanje svih umetnika koji učestvuju na određenoj izložbi
    @GetMapping("/izlozba/{id}")
    public List<Umetnik> getUmetniciByIzlozbaId(@PathVariable int id) {
        return umetnikService.getUmetniciByIzlozbaId(id);
    }

    @GetMapping("/{id}/slobodna-mesta")
    public ResponseEntity<?> getBrojSlobodnihMesta(@PathVariable int id) {
        int slobodnaMesta = izlozbaService.getBrojSlobodnihMesta(id);
        return ResponseEntity.ok(slobodnaMesta);
    }



}
