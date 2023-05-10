package co.edu.umanizales.tads.model.listaDE;

import co.edu.umanizales.tads.model.Gender;

import co.edu.umanizales.tads.model.Pet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class CircularListDE {
    private Node head;





    private int size;


    public List<Pet> toList() {
        List<Pet> pets = new ArrayList<>();
        if (head != null) {
            Node currentNode = head;
            do {
                pets.add(currentNode.getData());
                currentNode = currentNode.getNext();
            } while (currentNode != head);
        }
        return pets;
    }
    public void add(Pet pet) {
        Node newNode = new Node(pet);
        if (head == null) {
            head = newNode;
            head.setNext(head);
            head.setPrevious(head);
        } else {
            Node lastNode = head.getPrevious();
            newNode.setPrevious(lastNode);
            lastNode.setNext(newNode);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }
    /*
    Si la lista está vacía, coloco al nuevo nodo como el primer elemento de la lista y hago que el nodo apunte a sí mismo para ambos lados.
    Si la lista ya tiene mascotas, encuentro el último nodo de la lista y hago que apunte al nuevo.
    hago que el nuevo nodo apunte al primer nodo en la lista y que el primer nodo en la lista apunte al nuevo nodo.
    Ahora, hago que el nuevo nodo sea el nuevo primer nodo de la lista.



     */


    public void addToEnd(Pet pet) {
        Node newNode = new Node(pet);
        if (head == null) {
            head = newNode;
            head.setNext(head);
            head.setPrevious(head);
        } else {
            Node lastNode = head.getPrevious();
            newNode.setNext(head);
            head.setPrevious(newNode);
            newNode.setPrevious(lastNode);
            lastNode.setNext(newNode);
        }
        size++;
    }

/*
    creo un nuevo nodo en la lista para esa mascota.

    Si la lista está vacía,
    coloco al nuevo nodo como el primer elemento de la lista y hago que el nodo apunte a sí mismo en ambos sentidos.
    Si la lista ya tiene elementos, encuentro el último nodo de la lista y hago que apunte al nuevo nodo.
            Después, hago que el nuevo nodo apunte al primer nodo en la lista y que el primer nodo en la lista apunte al nuevo nodo.
    Finalmente, hago que el nuevo nodo sea el último nodo de la lista.

    Finalmente, incremento el tamaño de la lista para reflejar que agregué una mascota nueva.




 */

    /*
    verifico si la lista no está vacía
    Si la lista está vacía, creo un nuevo nodo con la mascota y lo establezco como cabeza de la lista.
    Si la lista no está vacía, verifico si la posición pedida es la primera (posición 1).
     Si es así, llamo al metodo  "add" que agrega la mascota al principio de la lista.
    Si la posición pedida no es la primera, recorro la lista hasta llegar al nodo anterior a la posición deseada.
    entonces, utilizo un bucle "while" que se ejecuta mientras el nodo temporal (temp) no sea nulo y el contador sea menor que la posición deseada menos uno.
       Una vez que he llegado al nodo anterior a la posición deseada, creo un nuevo nodo con la mascota y establezco los "next" y "previous"
       para que apunten al nodo siguiente y al nodo anterior.
        actualizo los next y previous de los nodos vecinos para que apunten al nuevo nodo.

        si la posición deseada es mayor que la longitud actual de la lista, llamo al metodo addToEnd









     */

    public void addInPosition(int position, Pet pet) {

        if (head!=null) {
            if (position == 1) {
                add(pet);
            } else {
                Node temp = head;
                int cont =1;
                while (temp != null && cont<position-1)
                {
                    temp = temp.getNext();
                    cont++;
                }
                if (temp != null) {
                    Node newNode = new Node(pet);
                    newNode.setNext(temp.getNext());
                    newNode.setPrevious(temp);
                    if (temp.getNext() != null) {
                        temp.getNext().setPrevious(newNode);
                    }
                    temp.setNext(newNode);
                    if (newNode.getNext() == null) {
                        newNode.setNext(head);
                        head.setPrevious(newNode);
                    }
                } else {
                    addToEnd(pet);
                }
            }
        }else{
            Node newNode = new Node(pet);
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
            head = newNode;
        }
    }



    public int bañarPerroAleatorio(String side) {

        if (head == null) {
            return -1;
        }

        int size = getSize();


        Random random = new Random();
        int randomPosition = random.nextInt(size) + 1;


        Node temp = head;
        int cont = 1;
        if (side.equals("l")) {
            temp = head.getPrevious();
        }
        while (cont < randomPosition) {
            if (side.equals("r")) {
                temp = temp.getNext();
            } else if (side.equals("l")) {
                temp = temp.getPrevious();
            }
            cont++;
        }
        if (side.equals("l")) {
            temp = temp.getNext();
        }


        Pet pet = temp.getData();


        if (!pet.isBañado()) {
            //
            pet.setBañado(true);
            return randomPosition;
        } else {

            return 0;
        }
    }

}
/*


Primero, se verifica si la lista no está vacía .
Si la lista está vacía, la función devuelve el mensaje de error.

Luego, se obtiene el tamaño de la lista y se genera un número aleatorio dentro del rango de la longitud de la lista.

Después, se recorre la lista hasta llegar al nodo correspondiente al número aleatorio generado y al lado especificado ("l" para izquierda, "r" para derecha). Si el lado es "l",
se empieza desde el nodo anterior a la cabeza de la lista.
Una vez que se ha llegado al nodo correspondiente, se verifica si el perro ya ha sido bañado. Si no ha sido bañado,
 se marca como bañado y se devuelve la posición aleatoria. Si ya ha sido bañado, se devuelve un mensaje de que ya fue bañado






 */



