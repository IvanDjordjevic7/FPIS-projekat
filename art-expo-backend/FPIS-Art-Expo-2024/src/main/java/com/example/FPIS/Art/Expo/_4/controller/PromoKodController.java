package com.example.FPIS.Art.Expo._4.controller;

import com.example.FPIS.Art.Expo._4.model.PromoKod;
import com.example.FPIS.Art.Expo._4.service.PromoKodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/kodovi")
@CrossOrigin("http://localhost:4200")
public class PromoKodController {

    @Autowired
    private PromoKodService promoKodService;

    @GetMapping("/{id}")
    public Optional<PromoKod> getPromoKodByPrijavaId(@PathVariable int id) {
        return promoKodService.getPromoKodByPrijavaId(id);
    }
}
