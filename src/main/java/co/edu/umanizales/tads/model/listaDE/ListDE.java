package co.edu.umanizales.tads.model.listaDE;

import co.edu.umanizales.tads.model.Gender;

import co.edu.umanizales.tads.model.Pet;
import lombok.Data;

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
    public void addToEnd(Pet pet) {
        Node newNode = new Node(pet);
        if (head == null) {
            head = newNode;
        } else {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
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
    public String delete(String id) {
        ListDE deleterList = new ListDE();
        Node temp = this.head;

        while (temp != null) {
            if (!temp.getData().getIdentification().equals(id)) {
                deleterList.addToEnd(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = deleterList.getHead();
        return id;
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

    public void intercalateBySex(){
        ListDE tempMales = new ListDE();
        ListDE tempFemales = new ListDE();
        Node temp = head;
        while (temp != null){
            if(temp.getData().getSex().equals(Gender.M)){
                tempMales.addToEnd(temp.getData());
            }
            if(temp.getData().getSex().equals(Gender.F)){
                tempFemales.addToEnd(temp.getData());
            }
            temp = temp.getNext();
        }

        ListDE intercalateList = new ListDE();
        Node maleNode = tempMales.getHead();
        Node femaleNode = tempFemales.getHead();
        while (maleNode != null || femaleNode != null){
            if (maleNode != null){
                intercalateList.addToEnd(maleNode.getData());
                maleNode = maleNode.getNext();
            }
            if (femaleNode != null){
                intercalateList.addToEnd(femaleNode.getData());
                femaleNode = femaleNode.getNext();
            }
        }
        head = intercalateList.getHead();
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
      public void sendBottom(char firstChar) {
        ListDE sendBottomList = new ListDE();
        Node temp = this.head;


        while (temp != null) {
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(firstChar)) {
                sendBottomList.addToEnd(temp.getData());
            }
            temp = temp.getNext();
        }


        temp = this.head;
        while (temp != null) {
            if (temp.getData().getName().charAt(0) == Character.toUpperCase(firstChar)) {
                sendBottomList.addToEnd(temp.getData());
            }
            temp = temp.getNext();
        }

        this.head = sendBottomList.getHead();
    }

    public int checkerByChar(char firstChar) {
        int counter = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getName().charAt(0) == Character.toUpperCase(firstChar)) {
                    counter++;
                }
                temp = temp.getNext();
            }
        }
        return counter;
    }
    public void addInPosition(int position, Pet pet) {

        if (head!=null) {
            if (position == 1) {
                addToStart(pet);
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

                } else {
                    addToEnd(pet);
                }
            }
        }else{
            addToEnd(pet);
        }
    }

    public void advancePosition(String id, int move) {
        if (head != null) {
            Node temp = this.head;

            int count = 1;

            while (temp != null && !temp.getData().getIdentification().equals(id)) {

                temp = temp.getNext();
                count++;

            }

            if (temp != null) {
                int diferencia = count- move;
                Pet petcopy = temp.getData();
                delete(temp.getData().getIdentification());
                if (diferencia > 0) {
                    addInPosition(diferencia, petcopy);}
                else{
                    addToStart(petcopy);
                }
            }
        }
    }
    public void losePosition(String id, int move) {
        if (head != null) {
            Node temp = this.head;
            int count = 1;
            while (temp != null && !temp.getData().getIdentification().equals(id)) {
                temp = temp.getNext();
                count++;
            }
            if (temp != null) {
                int newPosition = count + move;
                Pet petCopy = temp.getData();
                delete(temp.getData().getIdentification());
                if (newPosition <= getSize()) {
                    addInPosition(newPosition, petCopy);
                } else {
                    addToEnd(petCopy);
                }
            }
        }
    }

    public int getRangeByAges(int min, int max) {
        Node temp = head;
        int counter = 0;
        while (temp !=  null) {
            if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                counter++;
            }
            temp= temp.getNext();
        }

        return counter;
    }




    public void Kamikaze(String id) {
        Node temp = this.head;

        while (temp != null) {
            if (temp.getData().getIdentification().equals(id)) {
                if(temp.getPrevious() != null) {
                    temp.getPrevious().setNext(temp.getNext());
                } else {
                    this.head = temp.getNext();
                }
                if (temp.getNext() != null){
                    temp.getNext().setPrevious(temp.getPrevious());
                }
                temp.setPrevious(null);
                temp.setNext(null);
                return;
            }
            temp = temp.getNext();
        }



    }









}