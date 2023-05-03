package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;

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

}