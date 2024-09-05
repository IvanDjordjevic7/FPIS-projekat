package com.example.FPIS.Art.Expo._4.repository;

import com.example.FPIS.Art.Expo._4.model.Prijava;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrijavaRepository extends JpaRepository<Prijava, Integer>{

    Optional<Prijava> findByEmailAndToken(String email, String token);
}
