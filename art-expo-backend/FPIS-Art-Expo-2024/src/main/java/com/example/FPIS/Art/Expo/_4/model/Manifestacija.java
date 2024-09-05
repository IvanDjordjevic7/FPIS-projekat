package com.example.FPIS.Art.Expo._4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "manifestacija")
public class Manifestacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "grad")
    private String grad;

    @Column(name = "lokacija")
    private String lokacija;

    @Temporal(TemporalType.DATE)
    @Column(name = "datum_pocetka")
    private Date datumPocetka;

    @Temporal(TemporalType.DATE)
    @Column(name = "datum_zavrsetka")
    private Date datumZavrsetka;

    @Column(name = "cena_slikarstvo")
    private double cenaSlikarstvo;

    @Column(name = "cena_fotografija")
    private double cenaFotografija;

    @Temporal(TemporalType.DATE)
    @Column(name = "datum_umanjenja")
    private Date datumUmanjenja;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manifestacija")
    @JsonManagedReference
    private Set<Prijava> prijave;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manifestacija")
    @JsonBackReference
    private Set<Izlozba> izlozbe;

    public int getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public double getCenaSlikarstvo() {
        return cenaSlikarstvo;
    }

    public void setCenaSlikarstvo(double cenaSlikarstvo) {
        this.cenaSlikarstvo = cenaSlikarstvo;
    }

    public double getCenaFotografija() {
        return cenaFotografija;
    }

    public void setCenaFotografija(double cenaFotografija) {
        this.cenaFotografija = cenaFotografija;
    }

    public Date getDatumUmanjenja() {
        return datumUmanjenja;
    }

    public void setDatumUmanjenja(Date datumUmanjenja) {
        this.datumUmanjenja = datumUmanjenja;
    }
}
