package com.bookfinder.bookfinder.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "libros")
public class Libro {

    @Id
    private Long id;


    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idiomas idioma;

    private Integer descargas;

    //Constructores

    public Libro(){

    }

    public Libro(DatosLibro libro){

        this.id = libro.id();
        this.titulo = libro.titulo();
        this.idioma = Idiomas.fromSiglas(libro.idiomas()[0]);
        this.descargas = libro.descargas();
    }

    public Libro(DatosLibro libro, Autor autor){
        this(libro);
        this.autor = autor;
    }


    //Metodos Getters

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }


    //Metodos Setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "{\n"
                + "  \"id\": \"" + id + "\",\n"
                + "  \"titulo\": \"" + titulo + "\",\n"
                + "  \"autor\": " + autor.toString() + ",\n"
                + "  \"idioma\": \"" + idioma + "\",\n"
                + "  \"descargas\": " + descargas + "\n"
                + "}";
    }

}
