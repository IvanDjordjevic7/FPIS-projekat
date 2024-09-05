package com.example.FPIS.Art.Expo._4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prijava")
public class Prijava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "profesija")
    private String profesija;

    @Column(name = "adresa1")
    private String adresa1;

    @Column(name = "adresa2")
    private String adresa2;

    @Column(name = "postanski_broj")
    private String postanskiBroj;

    @Column(name = "mesto")
    private String mesto;

    @Column(name = "drzava")
    private String drzava;

    @Column(name = "email")
    private String email;

    @Column(name = "potvrda_email")
    private String potvrdaEmail;

    @Column(name = "token")
    private String token;

    @Column(name = "promo_kod_unos")
    private String promoKodUnos;

    @Column(name = "broj_osoba")
    private int brojOsoba;

    @Temporal(TemporalType.DATE)
    @Column(name = "datum_prijave")
    private Date datumPrijave;

    @ManyToOne
    @JoinColumn(name = "manifestacija_id")
    @JsonBackReference
    private Manifestacija manifestacija;

    @Column(name = "otkazana")
    private boolean otkazana;

    @ManyToMany
    @JoinTable(
            name = "prijava_izlozba",
            joinColumns = @JoinColumn(name = "prijava_id"),
            inverseJoinColumns = @JoinColumn(name = "izlozba_id")
    )
    @JsonBackReference
    private Set<Izlozba> izlozbe = new HashSet<>();

    @Column(name = "tip_prijave")
    private String tipPrijave;

    @Column(name = "konacna_cena")
    private double konacnaCena;

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getProfesija() {
        return profesija;
    }

    public void setProfesija(String profesija) {
        this.profesija = profesija;
    }

    public String getAdresa1() {
        return adresa1;
    }

    public void setAdresa1(String adresa1) {
        this.adresa1 = adresa1;
    }

    public String getAdresa2() {
        return adresa2;
    }

    public void setAdresa2(String adresa2) {
        this.adresa2 = adresa2;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPotvrdaEmail() {
        return potvrdaEmail;
    }

    public void setPotvrdaEmail(String potvrdaEmail) {
        this.potvrdaEmail = potvrdaEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPromoKodUnos() {
        return promoKodUnos;
    }

    public void setPromoKodUnos(String promoKodUnos) {
        this.promoKodUnos = promoKodUnos;
    }

    public int getBrojOsoba() {
        return brojOsoba;
    }

    public void setBrojOsoba(int brojOsoba) {
        this.brojOsoba = brojOsoba;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public Manifestacija getManifestacija() {
        return manifestacija;
    }

    public void setManifestacija(Manifestacija manifestacija) {
        this.manifestacija = manifestacija;
    }

    public boolean isOtkazana() {
        return otkazana;
    }

    public void setOtkazana(boolean otkazana) {
        this.otkazana = otkazana;
    }

    public Set<Izlozba> getIzlozbe() {
        return izlozbe;
    }

    public void setIzlozbe(Set<Izlozba> izlozbe) {
        this.izlozbe = izlozbe;
    }

    public String getTipPrijave() {
        return tipPrijave;
    }

    public void setTipPrijave(String tipPrijave) {
        this.tipPrijave = tipPrijave;
    }

    public double getKonacnaCena() {
        return konacnaCena;
    }

    public void setKonacnaCena(double konacnaCena) {
        this.konacnaCena = konacnaCena;
    }

    public Prijava() {
    }

    public Prijava(String ime,
                   String prezime,
                   String profesija,
                   String adresa1,
                   String adresa2,
                   String postanskiBroj,
                   String mesto,
                   String drzava,
                   String email,
                   String potvrdaEmail,
                   String token,
                   String promoKodUnos,
                   int brojOsoba,
                   Date datumPrijave,
                   Manifestacija manifestacija,
                   boolean otkazana,
                   Set<Izlozba> izlozbe,
                   String tipPrijave,
                   double konacnaCena) {
        this.ime = ime;
        this.prezime = prezime;
        this.profesija = profesija;
        this.adresa1 = adresa1;
        this.adresa2 = adresa2;
        this.postanskiBroj = postanskiBroj;
        this.mesto = mesto;
        this.drzava = drzava;
        this.email = email;
        this.potvrdaEmail = potvrdaEmail;
        this.token = token;
        this.promoKodUnos = promoKodUnos;
        this.brojOsoba = brojOsoba;
        this.datumPrijave = datumPrijave;
        this.manifestacija = manifestacija;
        this.otkazana = otkazana;
        this.izlozbe = izlozbe;
        this.tipPrijave = tipPrijave;
        this.konacnaCena = konacnaCena;
    }
}
