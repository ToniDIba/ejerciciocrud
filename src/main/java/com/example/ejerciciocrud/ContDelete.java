//------------ usando @RequestParam -----------------
//http://localhost:8080/persona/delete/?id=0
//       @RequestMapping(value = "delete")
//    public String deletePersona(@RequestParam(value = "id", required = true) int id) {

//------------- usando @PathVariable--------------
//http://localhost:8080/persona/delete/0
//       @DeleteMapping("delete/{id}")
//       @ResponseBody
//       public String deletePersonaPorId(@PathVariable int id) {

//---------------------------------------------------------------------------------------------------


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

    // @RequestMapping(value = "/delete/")
    // public String deletePersona(@RequestParam(value = "id", required = true) int id) {


    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deletePersonaPorId(@PathVariable("id") int id) {

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
