package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.listaSE.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();
       /* kids.add(new Kid("1111","Pablo",(byte)18,'M',"169");
        kids.add(new Kid("3","Daniel",(byte)5));
        kids.add(new Kid("96767","Tomas",(byte)8));

        kids.add(new Kid("967","Estefania",(byte)6));
        kids.add(new Kid("96767","Luis",(byte)8));
        kids.add(new Kid("1","Tarlos",(byte)4));
*/

    }

}
