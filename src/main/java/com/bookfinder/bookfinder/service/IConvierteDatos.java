package com.bookfinder.bookfinder.service;

public interface IConvierteDatos {


    <T> T obtenerDatos(String json, Class<T> clase);

}
