package com.example.FPIS.Art.Expo._4.repository;

import com.example.FPIS.Art.Expo._4.model.PromoKod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromoKodRepository extends JpaRepository<PromoKod, Integer>{

    Optional<PromoKod> findByKod(String kod);

    Optional<PromoKod>  findByPrijavaId(int prijavaId);
}
