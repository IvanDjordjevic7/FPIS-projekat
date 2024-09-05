package com.example.FPIS.Art.Expo._4.controller;

import com.example.FPIS.Art.Expo._4.model.Umetnik;
import com.example.FPIS.Art.Expo._4.service.UmetnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/umetnici")
@CrossOrigin("http://localhost:4200")
public class UmetnikController {

    @Autowired
    private UmetnikService umetnikService;

    // Endpoint za dobijanje svih umetnika koji učestvuju na određenoj izložbi
    @GetMapping("/izlozba/{id}")
    public List<Umetnik> getUmetniciByIzlozbaId(@PathVariable int id) {
        return umetnikService.getUmetniciByIzlozbaId(id);
    }

}
