package com.bookfinder.bookfinder.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGenerales (
        @JsonAlias("count") Integer cantidad,
        @JsonAlias("results") DatosLibro[] libros
){
}
