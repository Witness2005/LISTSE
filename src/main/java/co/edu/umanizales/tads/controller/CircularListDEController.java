package co.edu.umanizales.tads.controller;


import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.CircularListDEService;
import co.edu.umanizales.tads.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clistde")
public class CircularListDEController {
    @Autowired
    private CircularListDEService circularListDEService;
    @Autowired
    private LocationService locationService;


    @GetMapping
    public ResponseEntity<ResponseDTO> getPets() {
        List<Pet> pets =circularListDEService.getCpets().toList();
        return new ResponseEntity<>(new ResponseDTO(
                200, pets, null), HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody PetDTO petDTO) {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no encontrada",
                    null), HttpStatus.OK);
        }

        circularListDEService.getCpets().add(
                new Pet(petDTO.getIdentification(),
                        petDTO.getName(), petDTO.getAge(),
                        petDTO.getGender(), location,petDTO.isBañado()));

        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado La mascotica",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addtoend")
    public ResponseEntity<ResponseDTO> addKidtoend(@RequestBody PetDTO petDTO) {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no encontrada",
                    null), HttpStatus.OK);
        }

        circularListDEService.getCpets().addToEnd(
                new Pet(petDTO.getIdentification(),
                        petDTO.getName(), petDTO.getAge(),
                        petDTO.getGender(), location,petDTO.isBañado()));

        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado La mascotica",
                null), HttpStatus.OK);

    }
    @PostMapping(path = "/add/{position}")
    public ResponseEntity<ResponseDTO> addKid(@PathVariable("position") int position, @RequestBody PetDTO petDTO) {
        Location location = locationService.getLocationByCode(petDTO.getCodeLocation());
        if (location == null) {
            return new ResponseEntity<>(new ResponseDTO(
                    404, "La ubicación no encontrada",
                    null), HttpStatus.OK);
        }

        if (position <= 0 || position > circularListDEService.getCpets().getSize() + 1) {
            return new ResponseEntity<>(new ResponseDTO(
                    400, "Posición inválida",
                    null), HttpStatus.OK);
        }

        circularListDEService.getCpets().addInPosition(position,
                new Pet(petDTO.getIdentification(), petDTO.getName(),
                        petDTO.getAge(), petDTO.getGender(), location,petDTO.isBañado()));

        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha adicionado La mascotica",
                null), HttpStatus.OK);

    }


    @GetMapping("/random/{side}")
    public ResponseEntity<ResponseDTO> random(@PathVariable String side) {
        List<Pet> pets = circularListDEService.getCpets().toList();

        if (pets.isEmpty()) {
            return new ResponseEntity<>(new ResponseDTO(
                    200, "No se encontraron mascotas",
                    null), HttpStatus.OK);
        } else {
            int randomPosition = circularListDEService.getCpets().bañarPerroAleatorio(side);
            if (randomPosition == -1) {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "No se pudo bañar ningún perro",
                        null), HttpStatus.OK);
            } else if (randomPosition == 0) {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "El perro ya fue bañado en esa dirección" + randomPosition,
                        null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDTO(
                        200, "Se ha bañado el perro número " + randomPosition + " en la dirección " + side,
                        null), HttpStatus.OK);
            }
        }
    }


}




