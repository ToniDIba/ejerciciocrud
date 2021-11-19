package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class MtmtoPersService implements ImtmtoPers {

    public List<Persona> listaPersonas = new ArrayList<Persona>();

    @Autowired
    private ImtmtoPers mtmtoPers;


    /* *
     * Al dar de ALTA una persona, deben llegar todas las variables informadas ("required")
     * Se han declarado "Optional" en la interfase porque en método "updPersona" sí que pueden
     * venir una o más variables sin contenido.
     * */
    @Override
    public Persona addPersona(Optional<String> nombre,
                              Optional<Integer> edad,
                              Optional<String> poblacion) {

        int id = listaPersonas.size();

        Persona pers = new Persona(id, nombre.get(), edad.get(), poblacion.get());
        listaPersonas.add(pers);

        //Escribe contenido de la lista en consola
        mtmtoPers.getLista();

        return pers;

    }




    /* **
     * Modificar persona
     * */

    @Override
    public Persona updPersona(int idBuscado, Optional<String> nuevoNombre,
                              Optional<Integer> nuevaEdad,
                              Optional<String> nuevaPobla) {


        //Vuelca en consola el contenido de la lista
        mtmtoPers.getLista();

        Persona persLista = null;
        Persona personaBuscada = null;
        int indLista = 0;

        try {
            personaBuscada = listaPersonas.stream().filter(t -> t.getId() == idBuscado).findFirst().get();
        } catch (NoSuchElementException exception) {
            return null;
        }

        indLista = listaPersonas.indexOf(personaBuscada);
        persLista = listaPersonas.get(indLista);


        //Sólo se modificarán los campos que vengan informados, si algún valor no está presente, se deja como estaba.
        if (nuevoNombre.isPresent()) { listaPersonas.get(indLista).setNombre(nuevoNombre.get()); }
        if (nuevaEdad.isPresent())   { listaPersonas.get(indLista).setEdad(nuevaEdad.get()); }
        if (nuevaPobla.isPresent())  { listaPersonas.get(indLista).setPobla(nuevaPobla.get()); }

        return persLista;

    }


    /* **
     * Borrar persona
     * */

    @Override
    public Persona delPersona(int idBuscado) {


        //Vuelca en consola el contenido de la lista
        mtmtoPers.getLista();

        Persona persLista = null;
        Persona personaBuscada = null;
        int indLista = 0;

        try {
            personaBuscada = listaPersonas.stream().filter(t -> t.getId() == idBuscado).findFirst().get();
        } catch (NoSuchElementException exception) {
            return null;
        }

        indLista  = listaPersonas.indexOf(personaBuscada);
        persLista = listaPersonas.get(indLista);

        listaPersonas.remove(indLista);
        mtmtoPers.getLista();  //Escribe contenido de la lista en consola

        return persLista;

    }



    /* **
     * Consultar persona
     * */

    @Override
    public Persona consPersona(Optional<Integer> idBuscado,
                               Optional<String> nombre) {

        Persona persLista;
        boolean existe = false;
        Persona personaBuscada = null;
        int indLista = 0;

        mtmtoPers.getLista();


        //Campos vacíos = nada que consultar...
        if ( (nombre.isEmpty()) && (idBuscado.isEmpty()) ) return null;

        try {
            if(idBuscado.isPresent()) { personaBuscada = listaPersonas.stream().filter(t -> t.getId() == idBuscado.get()).findFirst().get(); }
            if(nombre.isPresent())    { personaBuscada = listaPersonas.stream().filter(t -> t.getNombre().equals(nombre.get())).findFirst().get(); }
        } catch (NoSuchElementException exception) {
            return null;
        }

        indLista = listaPersonas.indexOf(personaBuscada);
        persLista = listaPersonas.get(indLista);

        return persLista;

    }


    /* **
     * Volcar contenido de la lista en consola
     * */
    @Override
    public void getLista() {
        System.out.println("Leo contenido lista...");
        Iterator<Persona> it = listaPersonas.iterator();
        while (it.hasNext()) {
            Persona pers;
            pers = it.next();
            System.out.println(" GetLista Leido: " + pers.id + " " + pers.nombre + " " + pers.edad + " " + pers.poblacion);
        }
    }

}


