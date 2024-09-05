package com.example.FPIS.Art.Expo._4.service;

import com.example.FPIS.Art.Expo._4.model.Izlozba;
import com.example.FPIS.Art.Expo._4.repository.IzlozbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IzlozbaService {

    @Autowired
    private IzlozbaRepository izlozbaRepository;

    // Dobijanje svih izložbi za određenu manifestaciju
    public List<Izlozba> getIzlozbeByManifestacijaId(int id) {
        return izlozbaRepository.findByManifestacijaId(id);
    }

    // Dobijanje izložbe po ID-u
    public Optional<Izlozba> getIzlozbaById(int id) {
        return izlozbaRepository.findById(id);
    }

    // Dobijanje broja slobodnih mesta
    public int getBrojSlobodnihMesta(int izlozbaId) {
        Optional<Integer> brojSlobodnihMestaOpt = izlozbaRepository.findBrojSlobodnihMesta(izlozbaId);
        if (!brojSlobodnihMestaOpt.isPresent()) {
            // Ako nema rezultata, možete vratiti 0 ili baciti izuzetak, zavisno od vaših zahteva
            Optional<Izlozba> izlozbaOpt = izlozbaRepository.findById(izlozbaId);
            Izlozba izlozba = izlozbaOpt.get();
            return izlozba.getMaksBrojPrisutnih();
        }
        int brojSlobodnihMesta = brojSlobodnihMestaOpt.get();
        return brojSlobodnihMesta;
    }

    ///////////////////////////////////////////////////////////

}
