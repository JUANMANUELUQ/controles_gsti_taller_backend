package com.controles.taller.model;

public enum Rol {

    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE");

    private final String descripcion;

    Rol(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}