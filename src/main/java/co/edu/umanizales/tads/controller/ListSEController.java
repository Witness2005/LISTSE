package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;

import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Rango;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @Autowired
    private RangeService rangesService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listSEService.getKids().getHead(), null), HttpStatus.OK);
    }


    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(
                200, "SE han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid KidDTO kidDTO) {
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());

        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe",
                    null), HttpStatus.OK);
        }

        Kid newKid = new Kid(kidDTO.getIdentification(), kidDTO.getName(),
                kidDTO.getAge(), kidDTO.getGender(), location);

        boolean found = listSEService.getKids().Checker(newKid);

        if (found) {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Niño ya existe, no agregado",
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado el petacón",
                    null), HttpStatus.OK);
        }
    }

    /*
        @GetMapping(path = "/kidsbylocations")
        public ResponseEntity<ResponseDTO> getKidsByLocation(){
            List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
            for(Location loc: locationService.getLocations()){
                int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
                if(count>0){
                    kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200,kidsByLocationDTOList,
                    null), HttpStatus.OK);
        }
    */
    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invertList() {
        if (listSEService.getKids().count() == 0) {
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Lista vacia, no se puede invertir", null),
                    HttpStatus.OK);
        } else {
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


    @GetMapping(path = "/sendbottom/{firstChar}")
    public ResponseEntity<ResponseDTO> sendbottom(@PathVariable char firstChar) {


        if (listSEService.getKids().checkerByChar(Character.toUpperCase(firstChar)) == 0) {
            return new ResponseEntity<>(
                    new ResponseDTO(404, "no hay ninguno que empiece por esa letra", null),
                    HttpStatus.OK);
        } else {
            listSEService.getKids().sendBottom(Character.toUpperCase(firstChar));
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Cambio Realizado", null),
                    HttpStatus.OK);


        }


    }

    @GetMapping(path = "/filter/{firstChar}")
    public ResponseEntity<ResponseDTO> filterByFirstChar(@PathVariable char firstChar) {


        if (listSEService.getKids().checkerByChar(Character.toUpperCase(firstChar)) == 0) {
            return new ResponseEntity<>(
                    new ResponseDTO(200, "no hay ninguno que empiece por esa letra", null),
                    HttpStatus.OK);
        } else {
            listSEService.getKids().filterByFirstChar(Character.toUpperCase(firstChar));
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Cambio Realizado", null),
                    HttpStatus.OK);
        }


    }

    @GetMapping(path = "/excludingFilter/{firstChar}")
    public ResponseEntity<ResponseDTO> excludeByFirstChar(@PathVariable char firstChar) {

        if (listSEService.getKids().checkerByChar(Character.toUpperCase(firstChar)) == 0) {
            return new ResponseEntity<>(
                    new ResponseDTO(200, "no hay ninguno que empiece por esa letra", null),
                    HttpStatus.OK);
        } else {

            listSEService.getKids().filterExcludingFirstChar(Character.toUpperCase(firstChar));
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Cambio Realizado", null),
                    HttpStatus.OK);


        }


    }
/*
    @GetMapping(path="/sendbottom2")
    public ResponseEntity<ResponseDTO> sendbottom2(){
        listSEService.getKids().sendBottom('M');
        return new ResponseEntity<>(
                new ResponseDTO(200, "Cambio Realizado", null),
                HttpStatus.OK);



    }
    */

    @GetMapping(path = "/delete/{id}")
    public ResponseEntity<ResponseDTO> getTotalSalesByStore(@PathVariable String id) {
        listSEService.getKids().delete(id);
        return new ResponseEntity<>(new ResponseDTO(200,
                "Borrado satisfactoriamente", null), HttpStatus.OK);
    }


        @GetMapping(path = "/kidsbydepartment")
        public ResponseEntity<ResponseDTO> getKidsByDepa() {
            List<KidsByLocationDTO> kidsByLocationDTOList1 = new ArrayList<>();
            for (Location loc : locationService.getLocations()) {
                int count = listSEService.getKids().getCountKidsByLocationCodeLimited(loc.getCode());
                if (count > 0) {
                    kidsByLocationDTOList1.add(new KidsByLocationDTO(loc, count));
                }
            }
            return new ResponseEntity<>(new ResponseDTO(
                    200, kidsByLocationDTOList1,
                    null), HttpStatus.OK);
        }
    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        List<KidsByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidsByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
}





    @GetMapping(path = "/kidsbycityandgender/{limitador}")
    public ResponseEntity<ResponseDTO> getKidsByLocationAndGender(@PathVariable byte limitador) {
        List<KidsByLocationAndGenderDTO> kidsByLocationAndGenderDTOList = new ArrayList<>();


        for (Location loc : locationService.getLocations()) {
            List<KidsByGenderDTO> kidsByGenderList = new ArrayList<>();

            int countMales = listSEService.getKids().getCountKidsByLocationCodeAndGender(loc.getCode(), Gender.M, limitador);
            int countFemales = listSEService.getKids().getCountKidsByLocationCodeAndGender(loc.getCode(), Gender.F, limitador);

            int total = countMales + countFemales;
            if (total > 0) {
                kidsByGenderList.add(new KidsByGenderDTO(Gender.M, countMales));
                kidsByGenderList.add(new KidsByGenderDTO(Gender.F, countFemales));


                kidsByLocationAndGenderDTOList.add(new KidsByLocationAndGenderDTO(loc.getName(),kidsByGenderList,total));




            }
        }

        ResponseDTO responseDTO = new ResponseDTO(200, kidsByLocationAndGenderDTOList, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping(path = "/delete/{age1}")
    public ResponseEntity<ResponseDTO> getTotalSalesByStore(@PathVariable byte age1) {
        listSEService.getKids().deleteByAge(age1);
        return new ResponseEntity<>(new ResponseDTO(200,
                "Borrado satisfactoriamente", null), HttpStatus.OK);
    }


    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge(){

        listSEService.getKids().averageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids().averageAge(),
                null), HttpStatus.OK);
    }


    @GetMapping(path="/intercalatebygender")
    public ResponseEntity<ResponseDTO> intercalatebygender(){

        listSEService.getKids().intercalateByGender();
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se intercalo la lista por genero",
                null), HttpStatus.OK);
    }



    @GetMapping(path = "/rangeagepets")
    public ResponseEntity<ResponseDTO> getRangeByPets() {
        List<RangeAgeDTO> kidsRangeDTOList = new ArrayList<>();

        for (Rango rango : rangesService.getRanges()) {
            int quantity = listSEService.getKids().getRangeByPets(rango.getFrom(), rango.getTo());
            kidsRangeDTOList.add(new RangeAgeDTO(rango, quantity));


        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsRangeDTOList, null),
                HttpStatus.OK);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ErrorDTO> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errors.add(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), null, errors), HttpStatus.BAD_REQUEST);
    }







}

