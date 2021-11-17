//http://localhost:8080/persona/modificacion/?id=0 (PUT)
package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persona")



/* *
 * Modificación de una persona
 * */

public class ContPut {

    @Autowired
    private ImtmtoPers mtmtoPers;

    @RequestMapping(value = "/modificacion/")


    String modificacionPersona(@RequestParam(value = "id",        required = true)  Optional< Integer > id,
                                @RequestParam(value = "nombre",    required = false) Optional< String > nombre,
                                @RequestParam(value = "edad",      required = false) Optional < Integer > edad,
                                @RequestParam(value = "poblacion", required = false) Optional < String > poblacion )
    {

        if(nombre.isEmpty() && edad.isEmpty() && poblacion.isEmpty() ) {
            return " No se ha informado nada para modificar";
        }

        Persona persReturn = mtmtoPers.updPersona(id.get() ,nombre ,edad ,poblacion);

        if (Objects.isNull(persReturn)) {
            return "No existe persona con id = " + id.get() + " para modificar";
        } else {
            mtmtoPers.getLista(); //Muestra en consola contenido de la lista tras la modificación
            return "Persona modificada. Id: " + id.get() +
                                 ", nombre: " + persReturn.getNombre() +
                                   ", edad: " + persReturn.getEdad() +
                              ", poblacion: " + persReturn.getPobla();
        }


        // Retorna la persona que acabamos de modificar
        // return mtmtoPers.updPersona(id.get() ,nombre ,edad ,poblacion);

    }

}
