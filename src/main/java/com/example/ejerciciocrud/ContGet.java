// http://localhost:8080/persona/consulta/nombreOrId   (POST)

//Con @RequestParam:
//@RequestMapping(value = "/consulta/")
//    String consultaPersona(@RequestParam(value = "id", required = false) Optional<Integer> id,
//                           @RequestParam(value = "nombre", required = false) Optional<String> nombre) {*/


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

    /**
     * @param idOrName
     * Persona consultable de dos formas (por nombre y por id)
     * Acepta: .../persona/consulta/manuel
     * Acepta: .../persona/consulta/3
     */

    @GetMapping("consulta/{idOrName}")
    public Persona consultaPorNombreOrId(@PathVariable String idOrName) {

        Optional<Integer> idParam = Optional.empty();
        Optional<String> nombreParam = Optional.empty();

        idOrName = idOrName.trim();


        //FastFail
        if (idOrName.length() == 0)
            return new Persona(999, Optional.ofNullable("Informe 'Id' o 'Nombre' para poder hacer la consulta"), Optional.empty(), Optional.empty() );

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
            persReturn = ( idParam.isPresent() ? new Persona(idParam.get(),
                                                             Optional.ofNullable("No se ha encontrado id: " + "'" + idParam.get() + "'"),
                                                             Optional.empty(),
                                                             Optional.empty() ) :

                                                 new Persona(idParam.get(),
                                                             Optional.ofNullable("No se ha encontrado nombre: " + "'" + nombreParam.get() + "'"),
                                                             Optional.empty(),
                                                             Optional.empty()));
        }

        return persReturn;

    }


}