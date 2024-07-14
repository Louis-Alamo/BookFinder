package com.bookfinder.bookfinder.model;

public enum Idiomas {

    INGLES("Inglés", "en"),
    ESPAÑOL("Español", "es"),
    FRANCES("Francés", "fr"),
    ITALIANO("Italiano", "it"),
    PORTUGUES("Portugués", "pt"),
    ALEMAN("Alemán", "de");

    private String nombreCompleto;
    private String siglas;

    Idiomas(String nombreCompleto, String siglas) {
        this.nombreCompleto = nombreCompleto;
        this.siglas = siglas;
    }

    public String getSiglas() {
        return siglas;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public static Idiomas fromNombreCompleto(String texto) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.nombreCompleto.equalsIgnoreCase(texto)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado: " + texto);
    }



    public static Idiomas fromSiglas(String texto) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.siglas.equalsIgnoreCase(texto)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado: " + texto);
    }
}