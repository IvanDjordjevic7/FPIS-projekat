package com.example.FPIS.Art.Expo._4.repository;

import com.example.FPIS.Art.Expo._4.model.Manifestacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManifestacijaRepository extends JpaRepository<Manifestacija, Integer>{

    List<Manifestacija> findAll();

    Optional<Manifestacija> findById(int id);

}
