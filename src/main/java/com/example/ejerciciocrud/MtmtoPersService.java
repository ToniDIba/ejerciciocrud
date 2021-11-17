package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


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


        mtmtoPers.getLista();

        Iterator<Persona> it = listaPersonas.iterator();
        boolean existe = false;
        Persona persLista = null;
        int indLista=0;

        while (it.hasNext()) {
            persLista = it.next();
            if (persLista.id == idBuscado) {
                indLista = listaPersonas.indexOf(persLista);
                existe = true;
                break;
            }
        }

        if(existe == true) {

            //Sólo se modificarán los campos que vengan informados, si algún valor no está presente, se deja como estaba.
            if (nuevoNombre.isPresent()) {
                listaPersonas.get(indLista).setNombre(nuevoNombre.get());
            }
            if (nuevaEdad.isPresent()) {
                listaPersonas.get(indLista).setEdad(nuevaEdad.get());
            }
            if (nuevaPobla.isPresent()) {
                listaPersonas.get(indLista).setPobla(nuevaPobla.get());
            }

        }
        else
        {
            persLista = null;
        }

        return  persLista;

    }


    /* **
     * Borrar persona
     * */

    @Override
    public Persona delPersona(int idBuscado) {

        Iterator<Persona> it = listaPersonas.iterator();
        boolean existe = false;
        Persona persLista = null;

        while (it.hasNext()) {
            persLista = it.next();
            if (persLista.id == idBuscado) {
                existe = true;
                break;
            }
        }

        //No se ha encontrado persona para borrar
        if(existe == false) {
            persLista = null;
        }
        else {
            listaPersonas.remove(idBuscado);
            mtmtoPers.getLista();  //Escribe contenido de la lista en consola
        }

        return persLista;

    }



    /* **
     * Consultar persona
     * */

    @Override
    public Persona consPersona(Optional<Integer> idBuscado,
                               Optional<String> nombre) {

        Persona persLista = null;
        boolean existe = false;

        if ((nombre.isPresent()) || (idBuscado.isPresent())) {

            Iterator<Persona> it = listaPersonas.iterator();

            while (it.hasNext())
            {
                persLista = it.next();

                if (idBuscado.isPresent() && persLista.id == idBuscado.get()) {
                    existe = true;
                    break;
                }

                if (nombre.isPresent() && persLista.nombre.equals(nombre.get())) {
                    existe = true;
                    break;

                }
            }

            if (existe == false) {
                System.out.println("Persona no hallada");
                persLista = null;
            }

            //Escribe contenido de la lista en consola
            mtmtoPers.getLista();


        } else {
            persLista = null;
        }

        //System.out.println("Persona consultada: " + persLista.id + " " + persLista.getNombre() + " " + persLista.edad + " " + persLista.poblacion);
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


