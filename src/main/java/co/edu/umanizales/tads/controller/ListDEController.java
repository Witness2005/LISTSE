package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;

import co.edu.umanizales.tads.model.*;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.model.Rango;
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
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

    @Autowired
    private RangeService rangesService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        List<Pet> pets = listDEService.getPets().toList();
        return new ResponseEntity<>(new ResponseDTO(
                200, pets, null), HttpStatus.OK);
    }

    @PostMapping (path = "/add")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no encontrada",
                    null), HttpStatus.OK);}

            listDEService.getPets().add(
                    new Pet(petDTO.getIdentification(),
                            petDTO.getName(), petDTO.getAge(),
                            petDTO.getGender(), location));

        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado La mascotica",
                null), HttpStatus.OK);

}



    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invertList() {

        listDEService.getPets().invert();
        return new ResponseEntity<>(
                new ResponseDTO(200, "Lista Invertida Satisfactoriamente", null),
                HttpStatus.OK);


    }


    @GetMapping(path = "/maletostart")
    public ResponseEntity<ResponseDTO> maletostart() {

        listDEService.getPets().orderMalesToStart();
        return new ResponseEntity<>(
                new ResponseDTO(200, "Los machos han sido enviado al principio", null),
                HttpStatus.OK);


    }


    @GetMapping(path = "/intercalatebysex")
    public ResponseEntity<ResponseDTO> intercalatebysex() {

        listDEService.getPets().intercalateBySex();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se intercalo la lista por genero",
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/delete/{age1}")
    public ResponseEntity<ResponseDTO> getTotalSalesByStore(@PathVariable byte age1) {
        try {
            if (age1 < 1 || age1 > 127) {
                throw new IllegalArgumentException("Este espacio solo acepta edades (1-127)");
            }
            listDEService.getPets().deleteByAge(age1);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "Borrado satisfactoriamente", null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseDTO(400, e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/kamikaze/{id}")
    public ResponseEntity<ResponseDTO> kamikaze(@PathVariable String id) {
        listDEService.getPets().Kamikaze(id);
        return new ResponseEntity<>(new ResponseDTO(200, "Kamikaze successful", null), HttpStatus.OK);
    }


    @GetMapping(path = "/averageage")
    public ResponseEntity<ResponseDTO> averageAge() {

        listDEService.getPets().averageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getPets().averageAge(),
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/petsbycity")
    public ResponseEntity<ResponseDTO> getpetsBycity() {
        List<KidsByLocationDTO> kidsByLocationDTOList1 = new ArrayList<>();
        for (Location loc : locationService.getLocations()) {
            int count = listDEService.getPets().getCountKidsByLocationCodeLimited(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList1.add(new KidsByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200, kidsByLocationDTOList1,
                null), HttpStatus.OK);
    }


    @GetMapping(path = "/sendbottom/{firstChar}")
    public ResponseEntity<ResponseDTO> sendbottom(@PathVariable char firstChar) {
        try {
            if (listDEService.getPets().checkerByChar(Character.toUpperCase(firstChar)) == 0) {
                return new ResponseEntity<>(
                        new ResponseDTO(404, "no hay ninguno que empiece por esa letra", null),
                        HttpStatus.OK);
            } else {
                listDEService.getPets().sendBottom(Character.toUpperCase(firstChar));
                return new ResponseEntity<>(
                        new ResponseDTO(200, "Cambio Realizado", null),
                        HttpStatus.OK);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(
                    new ResponseDTO(500, "Se ha producido un error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/advanceposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> advancePosition(@PathVariable String code, @PathVariable int move) {
        try {
            if (move < 0) {
                return new ResponseEntity<>(
                        new ResponseDTO(400, "El numero de objetos a avanzar no puede ser negativo", null),
                        HttpStatus.BAD_REQUEST);
            }
            listDEService.getPets().advancePosition(code, move);
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Se movió la mascota", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseDTO(500, "Se ha producido un error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/lose/{code}/{move}")
    public ResponseEntity<ResponseDTO> losePosition(@PathVariable String code, @PathVariable int move) {
        try {
            if (move < 0) {
                return new ResponseEntity<>(
                        new ResponseDTO(400, "El valor para atrasar no puede ser negativo", null),
                        HttpStatus.BAD_REQUEST);
            }
            listDEService.getPets().losePosition(code, move);
            return new ResponseEntity<>(
                    new ResponseDTO(200, "Se retrocedió la mascota", null),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseDTO(500, "Se ha producido un error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/rangeagepets")
    public ResponseEntity<ResponseDTO> getRangeByPets() {
        List<RangeAgeDTO> kidsRangeDTOList = new ArrayList<>();

        for (Rango rango : rangesService.getRanges()) {
            int quantity = listDEService.getPets().getRangeByAges(rango.getFrom(), rango.getTo());
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



