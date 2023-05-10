package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.listaDE.CircularListDE;
import co.edu.umanizales.tads.model.listaDE.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class CircularListDEService {
    private CircularListDE cpets;
    public CircularListDEService() {
        cpets = new CircularListDE();




    }





}
