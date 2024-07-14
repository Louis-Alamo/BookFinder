package com.bookfinder.bookfinder.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autor_id;

    @Column(unique = true)
    private  String nombre;

    private  Integer anioNacimiento;
    private  Integer anioMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros;

    public Autor() {

    }

    public Autor(DatosAutor autor) {

        this.nombre = autor.nombre();
        this.anioNacimiento = autor.anioNacimiento();
        this.anioMuerte = autor.anioMuerte();
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    @Override
    public String toString() {
        return "{\n"
                + "  \"nombre\": \"" + nombre + "\",\n"
                + "  \"anioNacimiento\": " + anioNacimiento + ",\n"
                + "  \"anioMuerte\": " + anioMuerte + "\n"
                + "}";
    }

}

