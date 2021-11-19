//http://localhost:8080/persona/consulta/?id=1 (POST)
package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping(value = "/persona")


/* *
 * Consulta de una persona
 * */

public class ContGet {

    @Autowired
    private ImtmtoPers mtmtoPers;

    @RequestMapping(value = "/consulta/")
    String consultaPersona(@RequestParam(value = "id", required = false) Optional<Integer> id,
                           @RequestParam(value = "nombre", required = false) Optional<String> nombre) {


        String textoReturn="";

        if (id.isEmpty() && nombre.isEmpty()) {
            textoReturn = "Informe 'Id' o 'Nombre' para poder hacer la consulta";
        } else {

            Persona persReturn = mtmtoPers.consPersona(id, nombre);

            if (Objects.isNull(persReturn)) {
                if (id.isPresent()) {
                    textoReturn =  "No se ha encontrado persona con 'id: ' " + id.get() + " en la lista";
                }

                if (nombre.isPresent()) {
                    textoReturn = "No se ha encontrado persona con 'nombre: ' " + nombre.get() + " en la lista";
                }

            } else {
                textoReturn =  "Persona consultada. Id: " + persReturn.getId() +
                               ", nombre: " + persReturn.getNombre() +
                               ", edad: " + persReturn.getEdad() +
                               ", poblacion: " + persReturn.getPobla();
            }
        }

        return textoReturn;
        //return mtmtoPers.consPersona(id, nombre);
    }

}





