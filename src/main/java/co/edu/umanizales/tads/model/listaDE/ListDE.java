package co.edu.umanizales.tads.model.listaDE;
import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class ListDE {
    private Node head;
    private int size;

    public void add(Pet pet)
    {
        if(this.head!=null)
        {
            Node temp = this.head;
            while(temp.getNext()!=null)
            {
                temp = temp.getNext();
            }
            Node newPet= new Node(pet);
            temp.setNext(newPet);
            newPet.setPrevious(temp);
        }
        else
        {

            this.head= new Node(pet);
        }
    }
    public boolean Checker(Pet newPet) {
        Node temp = this.head;
        boolean found = false;
        while (temp != null) {
            Pet currentPet = temp.getData();
            if (currentPet.getName().equals(newPet.getName()) &&
                    currentPet.getLocation().equals(newPet.getLocation())) {
                found = true;
                break;
            }
            temp = temp.getNext();
        }
        if (!found) {
            Node newNode = new Node(newPet);
            newNode.setNext(this.head);
            this.head = newNode;
        }
        return found;
    }
}
