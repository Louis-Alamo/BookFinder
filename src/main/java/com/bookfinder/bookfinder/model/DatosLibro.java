package com.bookfinder.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(

        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") DatosAutor[] autor,
        @JsonAlias("languages") String[] idiomas,
        @JsonAlias("download_count") Integer descargas
    ){
}
