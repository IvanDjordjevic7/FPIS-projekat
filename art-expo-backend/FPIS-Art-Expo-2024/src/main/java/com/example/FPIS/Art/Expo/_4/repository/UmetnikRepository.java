package com.example.FPIS.Art.Expo._4.repository;

import com.example.FPIS.Art.Expo._4.model.Umetnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface UmetnikRepository extends JpaRepository<Umetnik, Integer>{

    // Pronaći sve umetnike koji učestvuju na određenoj izložbi
    //@Query("SELECT u.id, u.ime, u.prezime FROM umetnik u JOIN izlozba_lista_umetnika ilu ON u.id = ilu.umetnik_id WHERE ilu.izlozba_id = :izlozba_id")
    @Query("SELECT u FROM Umetnik u JOIN u.izlozbe i WHERE i.id = :izlozba_id")
    List<Umetnik> findUmetniciByIzlozbaId(@Param("izlozba_id") int izlozba_id);

}
