package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {

        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invertList() {
        if(listSEService.getKids().count()== 0)
        {
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Lista vacia, no se puede invertir", null),
                    HttpStatus.OK);
        }else
        {
            listSEService.getKids().invert();
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Lista Invertida Satisfactoriamente", null),
                    HttpStatus.OK);
        }



    }


    @GetMapping(path = "/count")
    public ResponseEntity<ResponseDTO> count() {

        listSEService.getKids().count();
        return new ResponseEntity<>(
                new ResponseDTO(200, listSEService.getKids().count(), null),
                HttpStatus.OK);
    }
    @GetMapping(path = "/checkerByChar/{firstChar}")
    public ResponseEntity<ResponseDTO> checkerByChar(@PathVariable char firstChar) {

        return new ResponseEntity<>(
                new ResponseDTO(200, listSEService.getKids().checkerByChar(Character.toUpperCase(firstChar)), null),
                HttpStatus.OK);
    }



    @GetMapping(path="/sendbottom/{firstChar}")
    public ResponseEntity<ResponseDTO> sendbottom(@PathVariable char firstChar){




        if (listSEService.getKids().checkerByChar(Character.toUpperCase(firstChar)) == 0 ){
            return new ResponseEntity<>(
                    new ResponseDTO(404, "no hay ninguno que empiece por esa letra", null),
                    HttpStatus.OK);
        }else {
            listSEService.getKids().sendBottom(Character.toUpperCase(firstChar));
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Cambio Realizado", null),
                    HttpStatus.OK);


        }






    }
    @GetMapping(path="/filter/{firstChar}")
    public ResponseEntity<ResponseDTO> filterByFirstChar (@PathVariable char firstChar  ){


        if (listSEService.getKids().checkerByChar(Character.toUpperCase(firstChar)) == 0 ){
            return new ResponseEntity<>(
                    new ResponseDTO(200, "no hay ninguno que empiece por esa letra", null),
                    HttpStatus.OK);
        }else {
            listSEService.getKids().filterByFirstChar(Character.toUpperCase(firstChar));
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Cambio Realizado", null),
                    HttpStatus.OK);
        }










    }

    @GetMapping(path="/excludingFilter/{firstChar}")
    public ResponseEntity<ResponseDTO> excludeByFirstChar (@PathVariable char firstChar ){

        if (listSEService.getKids().checkerByChar(Character.toUpperCase(firstChar)) == 0 ){
            return new ResponseEntity<>(
                    new ResponseDTO(200, "no hay ninguno que empiece por esa letra", null),
                    HttpStatus.OK);
        }else {

            listSEService.getKids().filterExcludingFirstChar(Character.toUpperCase(firstChar));
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Cambio Realizado", null),
                    HttpStatus.OK);


        }




}

    @GetMapping(path="/sendbottom2")
    public ResponseEntity<ResponseDTO> sendbottom2(){
        listSEService.getKids().sendBottom('M');
        return new ResponseEntity<>(
                new ResponseDTO(200, "Cambio Realizado", null),
                HttpStatus.OK);



    }
    @GetMapping(path="/delete/{id}")
    public ResponseEntity<ResponseDTO> getTotalSalesByStore(@PathVariable String id) {
        listSEService.getKids().delete(id);
        return new ResponseEntity<>(new ResponseDTO(200,
                "Borrado satisfactoriamente" , null), HttpStatus.OK);
    }
}