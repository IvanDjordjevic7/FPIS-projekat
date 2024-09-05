package com.example.FPIS.Art.Expo._4.repository;

import com.example.FPIS.Art.Expo._4.model.Izlozba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


@Repository
public interface IzlozbaRepository extends JpaRepository<Izlozba, Integer>{

    // Pronaći sve izložbe koje pripadaju određenoj manifestaciji
    List<Izlozba> findByManifestacijaId(int id);

    // Metoda za pronalaženje izložbe prema ID-ju
    Optional<Izlozba> findById(int id);

    // Metoda koja pronalazi id izlozbe na osnovu id-a prijave
    @Query("SELECT i.id FROM Izlozba i JOIN i.prijave p WHERE p.id = :prijava_id")
    List<Integer> findIzlozbaIdsByPrijavaId(@Param("prijava_id") int prijava_id);



    // Metoda za prebrojavanje broja slobodnih mesta na izložbi

    /*@Query("SELECT i.maks_broj_prisutnih - COALESCE(SUM(p.broj_osoba), 0) " +
            "FROM izlozba i LEFT JOIN prijava_izlozba pi ON i.id = pi.izlozba_id " +
            "LEFT JOIN prijava p ON pi.prijava_id = p.id " +
            "WHERE p.otkazana = false AND i.id = :izlozba_id ")*/
    @Query("SELECT i.maksBrojPrisutnih - COALESCE(SUM(p.brojOsoba), 0) " +
            "FROM Izlozba i LEFT JOIN i.prijave p " +
            "WHERE p.otkazana = false AND i.id = :izlozba_id")
    Optional<Integer> findBrojSlobodnihMesta(@Param("izlozba_id") int izlozba_id);

    /*@Query("SELECT i.maks_broj_prisutnih - COALESCE(SUM(p.broj_osoba), 0) " +
            "FROM izlozba i " +
            "JOIN manifestacija m ON i.manifestacija_id = m.id " +
            "JOIN prijava p ON p.manifestacija_id = m.id " +
            "WHERE p.otkazana = false AND m.id = :manifestacija_id " +
            "AND p.tip_prijave = 'Slikarstvo' " +
            "AND i.tip = 'Slikarstvo' " +
            "GROUP BY i.maks_broj_prisutnih ")*/
    @Query("SELECT i.maksBrojPrisutnih - COALESCE(SUM(p.brojOsoba), 0) " +
            "FROM Izlozba i " +
            "JOIN i.manifestacija m " +
            "JOIN Prijava p ON p.manifestacija = m " +
            "WHERE p.otkazana = false AND m.id = :manifestacija_id " +
            "AND p.tipPrijave = 'Slikarstvo' " +
            "AND i.tip = 'Slikarstvo' " +
            "GROUP BY i.maksBrojPrisutnih")
    Optional<Integer> findBrojSlobodnihMestaSlikarstvo(@Param("manifestacija_id") int manifestacija_id);

    /*@Query("SELECT i.maks_broj_prisutnih - COALESCE(SUM(p.broj_osoba), 0) " +
            "FROM izlozba i " +
            "JOIN manifestacija m ON i.manifestacija_id = m.id " +
            "JOIN prijava p ON p.manifestacija_id = m.id " +
            "WHERE p.otkazana = false AND m.id = :manifestacija_id " +
            "AND p.tip_prijave = 'Fotografija' " +
            "AND i.tip = 'Fotografija' " +
            "GROUP BY i.maks_broj_prisutnih ")*/
    @Query("SELECT i.maksBrojPrisutnih - COALESCE(SUM(p.brojOsoba), 0) " +
            "FROM Izlozba i " +
            "JOIN i.manifestacija m " +
            "JOIN Prijava p ON p.manifestacija = m " +
            "WHERE p.otkazana = false AND m.id = :manifestacija_id " +
            "AND p.tipPrijave = 'Fotografija' " +
            "AND i.tip = 'Fotografija' " +
            "GROUP BY i.maksBrojPrisutnih")
    Optional<Integer> findBrojSlobodnihMestaFotografija(@Param("manifestacija_id") int manifestacija_id);

    // Pronalazi izložbe na osnovu manifestacije i tipa
    List<Izlozba> findByManifestacijaIdAndTip(int manifestacijaId, String tip);

}
