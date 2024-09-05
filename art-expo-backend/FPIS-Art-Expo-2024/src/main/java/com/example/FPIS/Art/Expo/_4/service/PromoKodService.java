package com.example.FPIS.Art.Expo._4.service;

import com.example.FPIS.Art.Expo._4.model.PromoKod;
import com.example.FPIS.Art.Expo._4.model.Prijava;
import com.example.FPIS.Art.Expo._4.repository.PromoKodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class PromoKodService {

    @Autowired
    private PromoKodRepository promoKodRepository;

    /**
     * Kreira i čuva novi promo kod za datu prijavu.
     *
     * @param prijava - prijava za koju se generiše promo kod
     * @return generisani promo kod
     */
    public PromoKod createPromoKod(Prijava prijava) {
        PromoKod promoKod = new PromoKod();
        promoKod.setKod(generatePromoKod());
        promoKod.setKoriscen(false);
        promoKod.setPrijava(prijava);
        return promoKodRepository.save(promoKod);
    }

    /**
     * Validira i primenjuje promo kod ako je ispravan.
     *
     * @param kod - string promo koda koji treba validirati
     * @return true ako je promo kod validan i primenjen, inače false
     */
    public boolean validateAndApplyPromoKod(String kod) {
        Optional<PromoKod> promoKodOpt = promoKodRepository.findByKod(kod);
        if (promoKodOpt.isPresent() && !promoKodOpt.get().isKoriscen()) {
            PromoKod promoKod = promoKodOpt.get();
            promoKod.setKoriscen(true);
            promoKodRepository.save(promoKod);
            return true;
        }
        return false;
    }

    /**
     * Generiše nasumični promo kod.
     *
     * @return generisani promo kod kao string
     */
    private String generatePromoKod() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


    public Optional<PromoKod> findPromoKodByKod(String promoKodUnos) { return promoKodRepository.findByKod(promoKodUnos);
    }

    public Optional<PromoKod> getPromoKodByPrijavaId(int id){return promoKodRepository.findByPrijavaId(id);}

    public void markAsUsed(int id) {
        Optional<PromoKod> promoKodOpt = promoKodRepository.findById(id);
        if (promoKodOpt.isPresent()) {
            PromoKod promoKod = promoKodOpt.get();
            promoKod.setKoriscen(true); // Oznaci kod kao korišćen
            promoKodRepository.save(promoKod); // Sačuvaj promene u bazi
        } else {
            throw new RuntimeException("Promo kod nije pronađen.");
        }
    }
}
