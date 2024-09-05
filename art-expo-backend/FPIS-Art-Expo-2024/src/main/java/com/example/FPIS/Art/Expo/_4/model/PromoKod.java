package com.example.FPIS.Art.Expo._4.model;

import jakarta.persistence.*;



@Entity
@Table(name = "promo_kod")
public class PromoKod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "kod")
    private String kod;

    @Column(name = "koriscen")
    private boolean koriscen;

    @OneToOne
    @JoinColumn(name = "prijava_id")
    private Prijava prijava;

    public int getId() {
        return id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public boolean isKoriscen() {
        return koriscen;
    }

    public void setKoriscen(boolean koriscen) {
        this.koriscen = koriscen;
    }

    public Prijava getPrijava() {
        return prijava;
    }

    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }

    public PromoKod() {
    }

    public PromoKod(String kod, boolean koriscen, Prijava prijava) {
        this.kod = kod;
        this.koriscen = koriscen;
        this.prijava = prijava;
    }
}
