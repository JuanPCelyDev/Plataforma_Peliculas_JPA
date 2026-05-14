package org.cursoPlatzi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Director_tbl")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDirector;
    private String nombre;
    private String apellidos;

    public Director() {
    }

    public Director(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


}
