package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;

import co.edu.umanizales.tads.model.*;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.model.Rango;
import co.edu.umanizales.tads.service.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getPets().getHead(), null), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ResponseDTO> addPet(@RequestBody PetDTO petDTO) {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());

        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no existe",
                    null), HttpStatus.OK);
        }

        Pet newPet = new Pet(petDTO.getIdentification(), petDTO.getName(),
                petDTO.getAge(), petDTO.getGender(), location);

        boolean found = listDEService.getPets().Checker(newPet);

        if (found) {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Mascota ya existe",
                    null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se ha adicionado la mascota",
                    null), HttpStatus.OK);
        }
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
        listDEService.getPets().deleteByAge(age1);
        return new ResponseEntity<>(new ResponseDTO(200,
                "Borrado satisfactoriamente", null), HttpStatus.OK);
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


    }

    @GetMapping(path = "/advanceposition/{code}/{move}")
    public ResponseEntity<ResponseDTO> advancePosition(@PathVariable String code, @PathVariable int move) {
        listDEService.getPets().advancePosition(code, move);
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se movio la mascota ", null),
                HttpStatus.OK);
    }
    @GetMapping(path = "/lose/{code}/{move}")
    public ResponseEntity<ResponseDTO> losePosition(@PathVariable String code, @PathVariable int move) {
        listDEService.getPets().losePosition(code, move);
        return new ResponseEntity<>(
                new ResponseDTO(200, "Se retrocedio la mascota " , null),
                HttpStatus.OK);
    }

    @GetMapping(path = "/rangeagepets")
    public ResponseEntity<ResponseDTO> getRangeByPets() {
        List<RangeAgeDTO> kidsRangeDTOList = new ArrayList<>();

        for (Rango rango : rangesService.getRanges()) {
            int quantity = listDEService.getPets().getRangeByPets(rango.getFrom(), rango.getTo());
            kidsRangeDTOList.add(new RangeAgeDTO(rango, quantity));


        }
        return new ResponseEntity<>(new ResponseDTO(200, kidsRangeDTOList, null),
                HttpStatus.OK);


    }

}



