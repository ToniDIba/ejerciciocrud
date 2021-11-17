//http://localhost:8080/persona/delete/?id=0 (DELETE)
package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persona")


/* *
 * Borrado de una persona
 * */

public class ContDelete {

    @Autowired
    private ImtmtoPers mtmtoPers;
    boolean resultado;

    @RequestMapping(value = "/delete/")
    String deletePersona(@RequestParam(value = "id", required = true) int id) {

        Persona persReturn = mtmtoPers.delPersona(id);

        if (Objects.isNull(persReturn)) {
            return "No existe persona con id = " + id + " para borrar";
        } else {
            mtmtoPers.getLista(); //Muestra en consola contenido de la lista tras el borrado
            return "Persona borrada. Id: " + id +
                    ", nombre: " + persReturn.getNombre() +
                    ", edad: " + persReturn.getEdad() +
                    ", poblacion: " + persReturn.getPobla();
        }


    }

}
