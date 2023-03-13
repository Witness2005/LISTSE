package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@Getter
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();

        kids.add(new Kid("2","Mariana",(byte)3));
        kids.add(new Kid("3","Daniel",(byte)5));
        kids.add(new Kid("96767","Tomas",(byte)8));

        kids.add(new Kid("967","Estefania",(byte)6));
        kids.add(new Kid("96767","Luis",(byte)8));
        kids.add(new Kid("1","Tarlos",(byte)4));


    }





}

















