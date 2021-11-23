//http://localhost:8080/persona/modificacion/0(PUT)
package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpEQ;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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

    public Persona getData(@PathVariable Optional<Integer> id,
                           @PathVariable(required = false) Optional<String> nombre,
                           @PathVariable(required = false) Optional<Integer> edad,
                           @PathVariable(required = false) Optional<String> poblacion)

       {

        System.out.println("Id: " + id.get());
        System.out.println("Nombre: " + nombre.get());
        System.out.println("Edad: " + edad.get());
        System.out.println("Pobla: " + poblacion.get());

        return mtmtoPers.updPersona(new Persona( id.get(), nombre.get(), edad.get(), poblacion.get()) );
    }


}