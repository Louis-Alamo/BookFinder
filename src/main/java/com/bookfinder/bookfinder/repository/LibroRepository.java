package com.bookfinder.bookfinder.repository;

import com.bookfinder.bookfinder.model.Idiomas;
import com.bookfinder.bookfinder.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository  extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> findByIdioma(Idiomas idioma);

}
