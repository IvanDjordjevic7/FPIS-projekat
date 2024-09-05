package com.example.FPIS.Art.Expo._4.service;

import com.example.FPIS.Art.Expo._4.model.Umetnik;
import com.example.FPIS.Art.Expo._4.repository.UmetnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmetnikService {

    @Autowired
    private UmetnikRepository umetnikRepository;

    // Dobijanje svih umetnika za određenu izložbu
    public List<Umetnik> getUmetniciByIzlozbaId(int id) {
        return umetnikRepository.findUmetniciByIzlozbaId(id);
    }

}
