//http://localhost:8080/persona/alta/?nombre=manuel&edad=44&poblacion=Valencia (POST)
package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


/* *
* Alta de una persona
* */

@RestController
@RequestMapping(value = "/persona")

public class ContPost {

        @Autowired
        private ImtmtoPers mtmtoPers;

        @RequestMapping(value = "/alta/")

        Persona altaPersona(@RequestParam(value = "nombre",    required = true) Optional < String > nombre,
                            @RequestParam(value = "edad",      required = true) Optional < Integer > edad,
                            @RequestParam(value = "poblacion", required = true) Optional < String > poblacion )
        {
            return mtmtoPers.addPersona(nombre, edad, poblacion);
        }

    }




