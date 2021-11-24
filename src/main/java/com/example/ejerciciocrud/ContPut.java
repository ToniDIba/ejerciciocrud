//http://localhost:8080/persona/modificacion/0(PUT)
package com.example.ejerciciocrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpEQ;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Parser;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

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
    public Persona getData(@PathVariable Map<String, String> pathVarsMap) {

        int id = 0;
        Optional<String> nombre = Optional.empty();
        Optional<Integer> edad = Optional.empty();
        Optional<String> poblacion = Optional.empty();


        for (Map.Entry<String, String> entry : pathVarsMap.entrySet()) {
            String key = entry.getKey();
            String tab = entry.getValue();


            switch(key)
            {
                case "id":
                    id = parseInt(tab);
                case "nombre":
                    if(tab.trim().length() > 0) nombre = Optional.ofNullable(tab); //Retorna un valor si viene informada y, sino, un Optional.empty
                    break;
                case "edad":
                    if(tab.trim().length() > 0) edad = Optional.ofNullable(Integer.valueOf(tab));
                    break;
                case "poblacion":
                    if(tab.trim().length() > 0) poblacion = Optional.ofNullable(tab);
                    break;
                default:
                    System.exit(1);
            }

        }

        return mtmtoPers.updPersona(new Persona(id, nombre, edad, poblacion));
    }

}
