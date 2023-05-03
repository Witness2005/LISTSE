package co.edu.umanizales.tads.model.listaDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;

@Data
public class Node {
    private Pet data;
    private Node next;
    private Node previous;

    public Node(Pet data) {
        this.data = data;
    }
}
