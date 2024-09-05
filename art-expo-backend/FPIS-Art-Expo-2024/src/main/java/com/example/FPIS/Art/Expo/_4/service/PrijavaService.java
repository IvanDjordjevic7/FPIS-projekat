package com.example.FPIS.Art.Expo._4.service;

import com.example.FPIS.Art.Expo._4.model.Izlozba;
import com.example.FPIS.Art.Expo._4.model.Prijava;
import com.example.FPIS.Art.Expo._4.model.PromoKod;
import com.example.FPIS.Art.Expo._4.model.Manifestacija;
import com.example.FPIS.Art.Expo._4.repository.PrijavaRepository;
import com.example.FPIS.Art.Expo._4.repository.PromoKodRepository;
import com.example.FPIS.Art.Expo._4.repository.ManifestacijaRepository;
import com.example.FPIS.Art.Expo._4.repository.IzlozbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PrijavaService {

    @Autowired
    private PrijavaRepository prijavaRepository;

    @Autowired
    private ManifestacijaRepository manifestacijaRepository;

    @Autowired
    private PromoKodService promoKodService;

    @Autowired
    private IzlozbaRepository izlozbaRepository;

    @Autowired
    private PromoKodRepository promoKodRepository;

    public Optional<Prijava> getPrijavaByEmailAndToken(String email, String token){
        return prijavaRepository.findByEmailAndToken(email,token);
    }
    @Transactional
    public Prijava createPrijava(
            String ime,
            String prezime,
            String profesija,
            String adresa1,
            String adresa2,
            String postanskiBroj,
            String mesto,
            String drzava,
            String email,
            String potvrdaEmail,
            int brojOsoba,
            Manifestacija manifestacija,
            String tipPrijave,
            String promoKodUnos
    )
    {
        if(brojOsoba <= 0){
            throw new RuntimeException("Broj osoba mora biti veci od 0");
        }
        int brojSlobodnihMestaSlikarstvo = 0;
        int brojSlobodnihMestaFotografija = 0;

        Optional<Integer> brojSlobodnihMestaSlikarstvoOpt = izlozbaRepository.findBrojSlobodnihMestaSlikarstvo(manifestacija.getId());
        if(!brojSlobodnihMestaSlikarstvoOpt.isPresent()){
            List<Izlozba> izlozbe = izlozbaRepository.findByManifestacijaId(manifestacija.getId());
            brojSlobodnihMestaSlikarstvo = izlozbe.get(0).getMaksBrojPrisutnih();
        }else{
            brojSlobodnihMestaSlikarstvo = brojSlobodnihMestaSlikarstvoOpt.get();
        }
        Optional<Integer> brojSlobodnihMestaFotografijaOpt = izlozbaRepository.findBrojSlobodnihMestaFotografija(manifestacija.getId());
        if(!brojSlobodnihMestaFotografijaOpt.isPresent()){
            List<Izlozba> izlozbe = izlozbaRepository.findByManifestacijaId(manifestacija.getId());
            brojSlobodnihMestaFotografija = izlozbe.get(0).getMaksBrojPrisutnih();
        }else{
            brojSlobodnihMestaFotografija = brojSlobodnihMestaSlikarstvoOpt.get();
        }

        if ((tipPrijave.equals("Slikarstvo") && brojSlobodnihMestaSlikarstvo > brojOsoba) ||
                (tipPrijave.equals("Fotografija") && brojSlobodnihMestaFotografija > brojOsoba) ||
                (tipPrijave.equals("Oba dana") && brojSlobodnihMestaSlikarstvo > brojOsoba && brojSlobodnihMestaFotografija > brojOsoba))
        {

            // Postavi osnovnu cenu prema tipu prijave
            double osnovnaCena;
            if ("Slikarstvo".equalsIgnoreCase(tipPrijave)) {
                osnovnaCena = manifestacija.getCenaSlikarstvo();
            } else if ("Fotografija".equalsIgnoreCase(tipPrijave)) {
                osnovnaCena = manifestacija.getCenaFotografija();
            } else if ("Oba dana".equalsIgnoreCase(tipPrijave)) {
                osnovnaCena = (manifestacija.getCenaSlikarstvo() + manifestacija.getCenaFotografija()) * 0.9;
            } else {
                throw new IllegalArgumentException("Nepoznat tip prijave.");
            }

            double konacnaCena = osnovnaCena * brojOsoba;

            // Proveri da li je u periodu umanjenja cene

            Date now = new Date();
            if (now.before(manifestacija.getDatumUmanjenja())) {
                konacnaCena = konacnaCena - konacnaCena * 0.1; // 10% popusta pre odredjenog datuma
            }

            // Proveri promo kod ako postoji
            if (promoKodUnos != null && !promoKodUnos.isEmpty()) {
                Optional<PromoKod> promoKodOpt = promoKodService.findPromoKodByKod(promoKodUnos);
                if (promoKodOpt.isPresent() && !promoKodOpt.get().isKoriscen()) {
                    konacnaCena = konacnaCena - konacnaCena * 0.05; // Dodatnih 5% popusta
                    promoKodService.markAsUsed(promoKodOpt.get().getId());
                } else {
                    throw new IllegalArgumentException("Promo kod je nevažeći ili već korišćen.");
                }
            }

            // Popust za grupne prijave
            if (brojOsoba >= 3) {
                konacnaCena *= (1 - 0.01 * Math.min(brojOsoba, 5)); // 3% popusta za 3 osobe, 5% popusta za 5 i više osoba
            }

            // Kreiraj novi token za prijavu
            String token = UUID.randomUUID().toString();

            // Kreiraj novu prijavu
            Prijava prijava = new Prijava();
            prijava.setIme(ime);
            prijava.setPrezime(prezime);
            prijava.setProfesija(profesija);
            prijava.setAdresa1(adresa1);
            prijava.setAdresa2(adresa2);
            prijava.setPostanskiBroj(postanskiBroj);
            prijava.setMesto(mesto);
            prijava.setDrzava(drzava);
            prijava.setEmail(email);
            prijava.setPotvrdaEmail(potvrdaEmail);
            prijava.setBrojOsoba(brojOsoba);
            prijava.setDatumPrijave(new Date());
            prijava.setManifestacija(manifestacija);
            prijava.setOtkazana(false);
            prijava.setToken(token);
            prijava.setTipPrijave(tipPrijave);
            prijava.setKonacnaCena(konacnaCena);
            prijava.setPromoKodUnos(promoKodUnos);

            // Dodaj izložbe u prijavu na osnovu tipa prijave
            Set<Izlozba> izlozbe = new HashSet<>();
            if ("Slikarstvo".equalsIgnoreCase(tipPrijave)) {
                izlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Slikarstvo"));
            } else if ("Fotografija".equalsIgnoreCase(tipPrijave)) {
                izlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Fotografija"));
            } else if ("Oba dana".equals(tipPrijave)) {
                izlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Slikarstvo"));
                izlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Fotografija"));
            }

            prijava.setIzlozbe(izlozbe);


            // Sačuvaj prijavu
            Prijava savedPrijava = prijavaRepository.save(prijava);

            // Generiši novi promo kod za prijavu
            promoKodService.createPromoKod(savedPrijava);

            // Ažuriraj vezu između prijave i izložbi
            for (Izlozba izlozba : izlozbe) {
                izlozba.getPrijave().add(savedPrijava);
            }

            return savedPrijava;

        }
        else {
        throw new RuntimeException("Nema dovoljno mesta na izlozbi");

        }
    }
    @Transactional
    public Prijava updatePrijava(
            String email,
            String token,
            String novaTipPrijave,
            int noviBrojOsoba
    ) {
        Optional<Prijava> prijavaOpt = prijavaRepository.findByEmailAndToken(email, token);
        if (!prijavaOpt.isPresent()) {
            throw new RuntimeException("Prijava nije pronađena.");
        }
        Prijava prijava = prijavaOpt.get();
        if (prijava.isOtkazana()){
            throw new RuntimeException("Prijava je otkazana.");
        }

        // Pronađi sve izložbe povezane sa ovom prijavom
        List<Integer> izlozbeId = izlozbaRepository.findIzlozbaIdsByPrijavaId(prijava.getId());

        // Proveri slobodna mesta za svaku izložbu povezanu sa prijavom
        for (int izlozbaId : izlozbeId) {
            Optional<Integer> slobodnaMestaOpt = izlozbaRepository.findBrojSlobodnihMesta(izlozbaId);
            int slobodnaMesta = 0;
            if(slobodnaMestaOpt.isPresent()){
                slobodnaMesta = slobodnaMestaOpt.get();
            }else{
                Optional<Izlozba> izlozbaOpt = izlozbaRepository.findById(izlozbaId);
                Izlozba izlozba = izlozbaOpt.get();
                slobodnaMesta = izlozba.getMaksBrojPrisutnih();
            }

            if (slobodnaMesta < noviBrojOsoba) {
                throw new RuntimeException("Nema dovoljno slobodnih mesta za izložbu: " + izlozbaId);

            }
        }

        // Ažuriraj tip prijave i broj osoba
        prijava.setTipPrijave(novaTipPrijave);
        prijava.setBrojOsoba(noviBrojOsoba);

        // Pronađi manifestaciju po ID-u
        Optional<Manifestacija> manifestacijaOpt = manifestacijaRepository.findById(prijava.getManifestacija().getId());
        if (!manifestacijaOpt.isPresent()) {
            throw new RuntimeException("Manifestacija nije pronađena.");
        }
        Manifestacija manifestacija = manifestacijaOpt.get();

        // Ažuriraj cenu
        double osnovnaCena;
        if ("Slikarstvo".equalsIgnoreCase(prijava.getTipPrijave())) {
            osnovnaCena = manifestacija.getCenaSlikarstvo();
        } else if ("Fotografija".equalsIgnoreCase(prijava.getTipPrijave())) {
            osnovnaCena = manifestacija.getCenaFotografija();
        } else if ("Oba dana".equalsIgnoreCase(prijava.getTipPrijave())) {
            osnovnaCena = (manifestacija.getCenaSlikarstvo() + manifestacija.getCenaFotografija()) * 0.9;
        } else {
            throw new IllegalArgumentException("Nepoznat tip prijave.");
        }

        // Proveri da li je u periodu umanjenja cene
        double konacnaCena = osnovnaCena * noviBrojOsoba;
        if (prijava.getDatumPrijave().before(manifestacija.getDatumUmanjenja())) {
            konacnaCena = konacnaCena - konacnaCena * 0.1; // 10% popusta pre odredjenog datuma
        }

        // Proveri promo kod ako postoji
        if (prijava.getPromoKodUnos() != null && !prijava.getPromoKodUnos().isEmpty()) {
            Optional<PromoKod> promoKodOpt = promoKodService.findPromoKodByKod(prijava.getPromoKodUnos());
            if (promoKodOpt.isPresent() && promoKodOpt.get().isKoriscen()) {
                konacnaCena = konacnaCena - konacnaCena * 0.05; // Dodatnih 5% popusta

            } else {
                throw new IllegalArgumentException("Promo kod nije iskoriscen");
            }
        }

        // Popust za grupne prijave
        if (noviBrojOsoba >= 3) {
            konacnaCena *= (1 - 0.01 * Math.min(noviBrojOsoba, 5)); // 3% popusta za 3 osobe, 5% popusta za 5 i više osoba
        }

        prijava.setKonacnaCena(konacnaCena);

        // Dodaj izložbe u prijavu na osnovu novog tipa prijave
        Set<Izlozba> noveIzlozbe = new HashSet<>();
        if ("Slikarstvo".equalsIgnoreCase(novaTipPrijave)) {
            noveIzlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Slikarstvo"));
        } else if ("Fotografija".equalsIgnoreCase(novaTipPrijave)) {
            noveIzlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Fotografija"));
        } else if ("Oba dana".equals(novaTipPrijave)) {
            noveIzlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Slikarstvo"));
            noveIzlozbe.addAll(izlozbaRepository.findByManifestacijaIdAndTip(manifestacija.getId(), "Fotografija"));
        }
        prijava.setIzlozbe(noveIzlozbe);

        // Ukloni stare izložbe
        Set<Izlozba> stareIzlozbe = prijava.getIzlozbe();
        for (Izlozba izlozba : stareIzlozbe) {
            izlozba.getPrijave().remove(prijava);
            izlozbaRepository.save(izlozba); // Sačuvaj promene
        }

        // Dodaj nove izložbe
        prijava.setIzlozbe(noveIzlozbe);
        for (Izlozba izlozba : noveIzlozbe) {
            izlozba.getPrijave().add(prijava);
            izlozbaRepository.save(izlozba); // Sačuvaj promene
        }

        return prijavaRepository.save(prijava);

    }
    @Transactional
    public Prijava otkaziPrijavu(String email, String token) {
        Optional<Prijava> prijavaOpt = prijavaRepository.findByEmailAndToken(email, token);
        if (!prijavaOpt.isPresent()) {
            throw new RuntimeException("Prijava nije pronađena.");
        }
        Prijava prijava = prijavaOpt.get();

        // Onemogući token prijave
        prijava.setToken(null);

        // Promo kod postaje nevažeći
        Optional<PromoKod> promoKodOpt = promoKodRepository.findByPrijavaId(prijava.getId());
        PromoKod promoKod = promoKodOpt.get();
        if (promoKod != null && !promoKod.isKoriscen()) {
            promoKod.setKoriscen(true);
            promoKodRepository.save(promoKod);
        }

        prijava.setOtkazana(true);
        return prijavaRepository.save(prijava);

    }

}
