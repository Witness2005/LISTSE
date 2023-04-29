package co.edu.umanizales.tads.model.listaSE;

import co.edu.umanizales.tads.model.Kid;
import lombok.Data;

@Data
public class Node {
    private Kid data;
    private Node next;

    public Node(Kid data) {
        this.data = data;
    }
}
