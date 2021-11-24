
package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import static java.lang.Integer.parseInt;


@RestController
@RequestMapping(value = "/persona/")

/* *
 * Modificación de una persona
 * */


//http://localhost:8080/persona/modificacion/0/carlos/55/gerona

public class ContPut {

    @Autowired
    private ImtmtoPers mtmtoPers;

    @PutMapping("modificacion/{id}/{nombre}/{edad}/{poblacion}")
    public String getData(@PathVariable Map<String, String> pathVarsMap) {

        int id = 0;
        Optional<String> nombre = Optional.empty();
        Optional<Integer> edad = Optional.empty();
        Optional<String> poblacion = Optional.empty();


        for (Map.Entry<String, String> entry : pathVarsMap.entrySet()) {
            String key = entry.getKey();
            String valor = entry.getValue();


            switch (key) {
                case "id":
                    id = parseInt(valor);
                case "nombre":
                    if (valor.trim().length() > 0)
                        nombre = Optional.ofNullable(valor); //Retorna un valor si viene informada y, sino, un Optional.empty
                    break;
                case "edad":
                    if (valor.trim().length() > 0) edad = Optional.ofNullable(Integer.valueOf(valor));
                    break;
                case "poblacion":
                    if (valor.trim().length() > 0) poblacion = Optional.ofNullable(valor);
                    break;
                default:
                    System.exit(1);
            }

        }

        Persona personaRetornada = mtmtoPers.updPersona(new Persona(id, nombre, edad, poblacion));

        if (Objects.isNull(personaRetornada)) {
            return "No existe persona con id = " + id + " para modificar";
        } else {
            mtmtoPers.getLista(); //Muestra en consola contenido de la lista tras la modificación
            return "Persona modificada con 'Id' : " + id +
                    ", nombre: " + personaRetornada.getNombre() +
                    ", edad: " + personaRetornada.getEdad() +
                    ", poblacion: " + personaRetornada.getPobla();
        }

    }

}