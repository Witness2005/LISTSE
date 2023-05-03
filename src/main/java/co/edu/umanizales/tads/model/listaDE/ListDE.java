package co.edu.umanizales.tads.model.listaDE;
import co.edu.umanizales.tads.controller.dto.KidsByLocationDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Gender;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Data

public class ListDE {
    private Node head;
    private int size;





    public void add(Pet pet) {
        Node newPet = new Node(pet);
        if (head != null) {
            head.setPrevious(newPet);
        }
        newPet.setNext(head);
        head = newPet;
    }

    public void addToStart(Pet pet) {
        if (head != null) {
            Node newNode = new Node(pet);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(pet);
        }
        size++;
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

    public void invert() {
        if (this.head != null) {
            ListDE listTemp = new ListDE();
            Node temp = this.head;
            while (temp != null) {
                listTemp.addToStart(temp.getData());
                temp = temp.getNext();

            }
            this.head = listTemp.getHead();
        }

    }

    public void orderMalesToStart() {
        if (this.head != null) {
            ListDE listCp = new ListDE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getSex().equals(Gender.M)) {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
    }

    public void intercalateMaleAndFemale() {
        ListDE tempBoys = new ListDE();
        ListDE tempGirls = new ListDE();
        Node temp = head;
        while (temp != null) {
            if (temp.getData().getSex().equals(Gender.M)) {
                tempBoys.add(temp.getData());
            }
            if (temp.getData().getSex().equals(Gender.F)) {
                tempGirls.add(temp.getData());
            }
            temp = temp.getNext();
        }

        ListDE intercalateList = new ListDE();
        Node maleNode = tempBoys.getHead();
        Node femaleNode = tempGirls.getHead();
        while (maleNode != null || femaleNode != null) {
            if (maleNode != null) {
                intercalateList.add(maleNode.getData());
                maleNode = maleNode.getNext();
            }
            if (femaleNode != null) {
                intercalateList.add(femaleNode.getData());
                femaleNode = femaleNode.getNext();
            }
            size++; // Actualizar el tamaño de la lista
        }
        this.head = intercalateList.getHead();
    }


    public void deleteByAge(byte age) {
        ListDE deleterList = new ListDE();
        Node temp = this.head;

        while (temp != null) {
            if (temp.getData().getAge() != (age)) {
                deleterList.add(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = deleterList.getHead();

    }


    public float averageAge() {
        if (head != null) {
            Node temp = head;
            int counter = 0;
            int summatory = 0;
            while (temp != null) {
                counter++;
                summatory = summatory + temp.getData().getAge();
                temp = temp.getNext();
            }
            float average = 0;
            if (counter > 0) {
                average = summatory / (float) counter;
            }
            return average;
        } else {
            return 0;
        }
    }

    public int getCountKidsByLocationCodeLimited(String code) {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().substring(0, 8).equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }


}