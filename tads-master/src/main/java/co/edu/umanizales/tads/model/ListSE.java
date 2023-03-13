package co.edu.umanizales.tads.model;

import lombok.Data;

@Data
public class ListSE {


    private Node head;


    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo

        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */
    public void add(Kid kid) {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */
    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
    }
    public void addToEnd(Kid kid) {
        Node newNode = new Node(kid);
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

    public void invert() {
        if (this.head != null) {
            ListSE listTemp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listTemp.addToStart(temp.getData());
                temp = temp.getNext();

            }
            this.head = listTemp.getHead();
        }

    }
    public int count() {
        int counter = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null)
            {
                counter++;
                temp = temp.getNext();
            }
        }
        return counter;
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
    public void filterByFirstChar(char firstChar) {
        ListSE filteredList = new ListSE();
        Node temp = this.head;

        while (temp != null) {
            if (temp.getData().getName().charAt(0) == Character.toUpperCase(firstChar)) {
                filteredList.addToEnd(temp.getData());
            }
            temp = temp.getNext();
        }
        this.head = filteredList.getHead();



    }
    public void filterExcludingFirstChar(char firstChar) {
        ListSE excluderFilteredList = new ListSE();
        Node temp = this.head;

        while (temp != null) {
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(firstChar)) {
                excluderFilteredList.addToEnd(temp.getData());
            }
            temp = temp.getNext();
        }
        this.head = excluderFilteredList.getHead();


    }



        public void sendBottom(char firstChar) {
            ListSE sendBottomList = new ListSE();
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

/*
Prueba usando los metodos
basicamente hace lo mismo que el sentbottom solo que este se aprovecha de los metodos anteriormente creados

        public void sendbottom2(char firstChar){
            ListSE sendBottomList2 = new ListSE();

            sendBottomList2.filterExcludingFirstChar(firstChar);


            sendBottomList2.filterByFirstChar(firstChar);

            this.head = sendBottomList2.getHead();








        }
        */


    public String delete(String id) {
        ListSE deleterList = new ListSE();
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

}
























