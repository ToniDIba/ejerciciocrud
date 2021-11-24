package com.example.ejerciciocrud;

import java.util.Optional;

public class Persona {


    //public Persona(int id, String nombre, int edad, String poblacion)
    public Persona(int id, Optional<String> nombre,
                           Optional<Integer> edad,
                           Optional <String> poblacion)
    {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.poblacion = poblacion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Optional<String> getNombre() { return nombre; }

    public void setNombre(Optional<String> nombre) {
        this.nombre = nombre;
    }

    public Optional<Integer> getEdad() {
        return edad;
    }

    public void setEdad(Optional<Integer> edad) {
        this.edad = edad;
    }

    public Optional<String> getPobla() {
        return poblacion;
    }

    public void setPobla(Optional<String> pobla) {
        this.poblacion = pobla;
    }



    public int id;
    public Optional<String> nombre;
    public Optional<Integer> edad;
    public Optional<String> poblacion;



}
