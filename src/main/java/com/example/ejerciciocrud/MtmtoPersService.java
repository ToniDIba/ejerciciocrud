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

        int id = 0;

        /**
         *  Busco id de la última persona añadida a la lista
         */
        if(listaPersonas.size() > 0) {
            Persona ultimaPersona = listaPersonas.get(listaPersonas.size()-1);
            id = ultimaPersona.getId() + 1;
        }

        Persona pers = new Persona(id, nombre, edad, poblacion);
        listaPersonas.add(pers);

        //Escribe contenido de la lista en consola
        mtmtoPers.getLista();

        return pers;

    }




    /* **
     * Modificar persona
     * */

    @Override
    public Persona updPersona(Persona unaPers) {


        //Vuelca en consola el contenido de la lista
        mtmtoPers.getLista();

        int idBuscado = unaPers.getId();

        Persona persLista = null;
        Persona personaBuscada = null;
        int indLista = 0;

        Optional<String> nuevoNombre = unaPers.getNombre();
        Optional<Integer> nuevaEdad = unaPers.getEdad();
        Optional<String> nuevaPobla = unaPers.getPobla();

        try {
            personaBuscada = listaPersonas.stream().filter(t -> t.getId() == idBuscado).findFirst().get();
        } catch (NoSuchElementException exception) {
            return null;
        }

        indLista = listaPersonas.indexOf(personaBuscada);
        persLista = listaPersonas.get(indLista);


        System.out.println("Cambiados: " + nuevoNombre + " " + nuevaEdad + " " + nuevaPobla);
        //Sólo se modificarán los campos que vengan informados, si algún valor no está presente, se deja como estaba.
        if (nuevoNombre.isPresent()) { listaPersonas.get(indLista).setNombre(nuevoNombre); }
        if (nuevaEdad.isPresent())   { listaPersonas.get(indLista).setEdad(nuevaEdad); }
        if (nuevaPobla.isPresent())  { listaPersonas.get(indLista).setPobla(nuevaPobla); }

        mtmtoPers.getLista();
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
        Persona personaBuscada = null;
        int indLista = 0;

        mtmtoPers.getLista(); //Vuelca el contenido de la lista en consola

        try {
            if(idBuscado.isPresent()) { personaBuscada = listaPersonas.stream().filter(t -> t.getId() == idBuscado.get()).findFirst().get(); }
            if(nombre.isPresent())    { personaBuscada = listaPersonas.stream().filter(t -> t.getNombre().get().equals(nombre.get())).findFirst().get(); }
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


