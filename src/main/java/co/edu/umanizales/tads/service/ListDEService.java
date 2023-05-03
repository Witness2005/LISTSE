package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.model.listaDE.ListDE;
import co.edu.umanizales.tads.service.LocationService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDEService {
    private ListDE pets;
    public ListDEService() {
        pets = new ListDE();




    }

}
