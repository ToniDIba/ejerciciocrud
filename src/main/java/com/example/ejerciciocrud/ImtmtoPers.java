package com.example.ejerciciocrud;

import java.util.Optional;

public interface ImtmtoPers {

    // Añade una persona a la lista
    Persona addPersona(Optional<String> nombre,
                       Optional<Integer> edad,
                       Optional<String> poblacion);



/*---------------------------------------------------------------------------
    // Modifica los datos de una persona que ya exista en la lista
    Persona updPersona(int id, Optional<String> nombre,
                               Optional<Integer> edad,
                               Optional<String> poblacion); */


    // Modifica los datos de una persona que ya exista en la lista
    Persona updPersona(Persona unaPers);
/*--------------------------------------------------------------------------*/

    // Borra persona existente en la lista
    Persona delPersona(int id);


    // Consulta persona en la lista
    Persona consPersona(Optional<Integer> id, Optional<String> nombre);

    void getLista();
}
