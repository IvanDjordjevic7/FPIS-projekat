package com.example.FPIS.Art.Expo._4.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "izlozba")
public class Izlozba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "datum")
    private Date datum;

    @Column(name = "vreme_otvaranja")
    private LocalTime vremeOtvaranja;

    @Column(name = "vreme_zatvaranja")
    private LocalTime vremeZatvaranja;

    @Column(name = "tip")
    private String tip;

    @ManyToOne
    @JoinColumn(name = "manifestacija_id")
    @JsonManagedReference
    private Manifestacija manifestacija;

    @Column(name = "maks_broj_prisutnih")
    private int maksBrojPrisutnih;

    @ManyToMany(mappedBy = "izlozbe")
    @JsonManagedReference
    private Set<Prijava> prijave = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "izlozba_lista_umetnika",
            joinColumns =
                    @JoinColumn(name = "izlozba_id"),
            inverseJoinColumns =
                    @JoinColumn(name = "umetnik_id")
    )
    @JsonManagedReference
    private Set<Umetnik> umetnici = new HashSet<>();

    public int getId() {
        return id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public LocalTime getVremeOtvaranja() {
        return vremeOtvaranja;
    }

    public void setVremeOtvaranja(LocalTime vremeOtvaranja) {
        this.vremeOtvaranja = vremeOtvaranja;
    }

    public LocalTime getVremeZatvaranja() {
        return vremeZatvaranja;
    }

    public void setVremeZatvaranja(LocalTime vremeZatvaranja) {
        this.vremeZatvaranja = vremeZatvaranja;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Manifestacija getManifestacija() {
        return manifestacija;
    }

    public void setManifestacija(Manifestacija manifestacija) {
        this.manifestacija = manifestacija;
    }

    public int getMaksBrojPrisutnih() {
        return maksBrojPrisutnih;
    }

    public void setMaksBrojPrisutnih(int maksBrojPrisutnih) {
        this.maksBrojPrisutnih = maksBrojPrisutnih;
    }

    public Set<Prijava> getPrijave() {
        return prijave;
    }

    public void setPrijave(Set<Prijava> prijave) {
        this.prijave = prijave;
    }

    public Set<Umetnik> getUmetnici() {
        return umetnici;
    }

    public void setUmetnici(Set<Umetnik> umetnici) {
        this.umetnici = umetnici;
    }
}


