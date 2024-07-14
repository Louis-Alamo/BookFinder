package com.bookfinder.bookfinder.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos implements IConvierteDatos {

    private final ObjectMapper mapper = new ObjectMapper();


    public <T> T obtenerDatos(String json, Class<T> clase) {

        try {
            return mapper.readValue(json, clase);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
