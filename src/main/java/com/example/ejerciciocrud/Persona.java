package com.example.ejerciciocrud;

public class Persona {


    public Persona(int id, String nombre, int edad, String poblacion)
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

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getPobla() {
        return poblacion;
    }

    public void setPobla(String pobla) {
        this.poblacion = pobla;
    }

    public int id;
    public String nombre;
    public int edad;
    public String poblacion;


}
