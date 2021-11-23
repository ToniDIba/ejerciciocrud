//http://localhost:8085/persona/consulta/nombreOrId(POST)
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

    /*@RequestMapping(value = "/consulta/")
    String consultaPersona(@RequestParam(value = "id", required = false) Optional<Integer> id,
                           @RequestParam(value = "nombre", required = false) Optional<String> nombre) {*/


    @GetMapping("consulta/{idOrName}")
    public Persona consultaPorNombreOrId(@PathVariable String idOrName) {

        Optional<String> nombreParam = Optional.empty();
        Optional<Integer> idParam = Optional.empty();

        idOrName = idOrName.trim();


        //FastFail
        if (idOrName.length() == 0) return new Persona(999, "Informe 'Id' o 'Nombre' para poder hacer la consulta", 0, "null");

        /* *
         * Extrae de param "idOrName" un 'int' correspondiente al 'id', o un String correspondiente al nombre buscado
         * */
        try {
            idParam = Optional.ofNullable(Integer.parseInt(idOrName));
        } catch (NumberFormatException e) {
            nombreParam = Optional.ofNullable(idOrName);
        }


        Persona persReturn = mtmtoPers.consPersona(idParam, nombreParam);


        if (Objects.isNull(persReturn)) {

            persReturn = ( idParam.isPresent() ?
                    new Persona(999, "No se ha encontrado persona con id: " + "'" + idParam.get() + "'" + " en la lista", 0, "null") :
                    new Persona(999, "No se ha encontrado persona con nombre: " + "'" + nombreParam.get() + "'" + " en la lista", 0, "null") );
        }

        return persReturn;

    }


}