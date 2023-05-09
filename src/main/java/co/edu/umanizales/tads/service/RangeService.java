package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Rango;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RangeService {
    private List<Rango> ranges ;

    public RangeService() {
        //Conectar a una base de datos
        ranges= new ArrayList<>();
        ranges.add(new Rango(1,3));
        ranges.add(new Rango(4,6));
        ranges.add(new Rango(7,9));
        ranges.add(new Rango(10,12));
        ranges.add(new Rango(14,15));

  }
}



