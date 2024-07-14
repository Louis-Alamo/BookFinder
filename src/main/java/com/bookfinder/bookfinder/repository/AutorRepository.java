package com.bookfinder.bookfinder.repository;

import com.bookfinder.bookfinder.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento >= ?1 AND a.anioMuerte <= ?2")
    List<Autor> buscarAutoresEntreDosAnos(Integer anioInicio, Integer anioFin);


}
