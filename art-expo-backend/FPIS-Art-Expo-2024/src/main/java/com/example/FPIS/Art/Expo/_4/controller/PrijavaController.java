package com.example.FPIS.Art.Expo._4.controller;

import com.example.FPIS.Art.Expo._4.dto.IzmenaPrijaveDTO;
import com.example.FPIS.Art.Expo._4.dto.OtkazivanjePrijaveDTO;
import com.example.FPIS.Art.Expo._4.dto.PrijavaDTO;
import com.example.FPIS.Art.Expo._4.model.Manifestacija;
import com.example.FPIS.Art.Expo._4.model.Prijava;
import com.example.FPIS.Art.Expo._4.service.ManifestacijaService;
import com.example.FPIS.Art.Expo._4.service.PrijavaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prijave")
@CrossOrigin("http://localhost:4200")
public class PrijavaController {


    @Autowired
    private PrijavaService prijavaService;

    @Autowired
    private ManifestacijaService manifestacijaService;

    @GetMapping("/{email}&{token}")
    public Optional<Prijava> getPrijavaByEmailAndToken(@PathVariable String email, @PathVariable String token){
        return prijavaService.getPrijavaByEmailAndToken(email, token);
    }

    @PostMapping("/kreiraj")
    public ResponseEntity<Prijava> createPrijava(@RequestBody @Valid PrijavaDTO prijavaDTO) {
        try {
            Optional<Manifestacija> manifestacijaOpt = manifestacijaService.getManifestacijaById(prijavaDTO.getManifestacijaId());
            Manifestacija manifestacija = manifestacijaOpt.get();
            if (manifestacija == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Prijava prijava = prijavaService.createPrijava(
                    prijavaDTO.getIme(),
                    prijavaDTO.getPrezime(),
                    prijavaDTO.getProfesija(),
                    prijavaDTO.getAdresa1(),
                    prijavaDTO.getAdresa2(),
                    prijavaDTO.getPostanskiBroj(),
                    prijavaDTO.getMesto(),
                    prijavaDTO.getDrzava(),
                    prijavaDTO.getEmail(),
                    prijavaDTO.getPotvrdaEmail(),
                    prijavaDTO.getBrojOsoba(),
                    manifestacija,
                    prijavaDTO.getTipPrijave(),
                    prijavaDTO.getPromoKodUnos()
            );
            return new ResponseEntity<>(prijava, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/izmena")
    public ResponseEntity<Prijava> updatePrijava(
            @RequestBody @Valid IzmenaPrijaveDTO izmenaPrijaveDTO
    ) {
        try {
            Prijava updatedPrijava = prijavaService.updatePrijava(izmenaPrijaveDTO.getEmail(), izmenaPrijaveDTO.getToken(),
                    izmenaPrijaveDTO.getNovaTipPrijave(), izmenaPrijaveDTO.getNoviBrojOsoba());
            return ResponseEntity.ok(updatedPrijava);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/otkazivanje")
    public ResponseEntity<Prijava> otkaziPrijavu(
            @RequestBody @Valid OtkazivanjePrijaveDTO otkazivanjePrijaveDTO
    ) {
        try {
            Prijava updatedPrijava = prijavaService.otkaziPrijavu(otkazivanjePrijaveDTO.getEmail(), otkazivanjePrijaveDTO.getToken());
            return ResponseEntity.ok(updatedPrijava);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
