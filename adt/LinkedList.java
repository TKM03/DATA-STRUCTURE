package adt;

import entity.Tutors;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 *
 * @author Tongkitming
 */

public class LinkedList<T> implements ListInterface<T> {

    private Node firstNode;
    private int numOfEntry;

    public LinkedList() {
        clear();
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);	

        if (isEmpty()) {
            firstNode = newNode;
        } else {                        
            Node currentNode = firstNode;	
            while (currentNode.next != null) { 
                currentNode = currentNode.next;
            }
            currentNode.next = newNode; 
        }

        numOfEntry++;
        return true;
    }
    
    
    @Override
    public boolean add(int newPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numOfEntry + 1)) {
            Node newNode = new Node(newEntry);

            if (isEmpty() || (newPosition == 1)) { 
                newNode.next = firstNode;
                firstNode = newNode;
            } else {							
                Node nodeBefore = firstNode;
                for (int i = 1; i < newPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;		
                }

                newNode.next = nodeBefore.next;	
                nodeBefore.next = newNode;		
            }

            numOfEntry++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }
    
    
    @Override
    public final void clear() {
        firstNode = null;      //nothing in node
        numOfEntry = 0;
    }
    
    
    @Override
    public boolean isEmpty() {
        boolean result;

        result = numOfEntry == 0;

        return result;
    }
    

    @Override
    public T remove(int givenPosition) {
        T result = null;               

        if ((givenPosition >= 1) && (givenPosition <= numOfEntry)) {
            if (givenPosition == 1) {      
                result = firstNode.data;    
                firstNode = firstNode.next;
            } else {                        
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    nodeBefore = nodeBefore.next;	
                }
                result = nodeBefore.next.data;  
                nodeBefore.next = nodeBefore.next.next;	
            } 														
            numOfEntry--;
        }

        return result; 
    }
    
     @Override
    public boolean replace(int givenPosition, T newEntry) {
        boolean isSuccessful = true;

        if ((givenPosition >= 1) && (givenPosition <= numOfEntry)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;		
            }
            currentNode.data = newEntry;	
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    
    @Override
    public T find(String tutorName) {
        Node current = firstNode;
        while (current != null) {
            if (((Tutors) current.data).getName().equals(tutorName)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    
    @Override
    public void listAll() {
        Node current = firstNode;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    
    @Override
    public T getEntry(int givenPosition) {
    if ((givenPosition >= 1) && (givenPosition <= numOfEntry)) {
        Node currentNode = firstNode;
        for (int i = 1; i < givenPosition; ++i) {
            currentNode = currentNode.next; 
        }
        return currentNode.data; 
    } else {
        throw new IndexOutOfBoundsException("Invalid position");
    }
}

    @Override
    public void filter(String criteria) {
        Node current = firstNode;
        while (current != null) {
            if (((Tutors) current.data).getSubject().equals(criteria)) {
                System.out.println(current.data);
            }
            current = current.next;
        }
    }
    
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return found;
    }
    
    @Override
    public int getNumberOfEntries() {
        return numOfEntry;
    }
    
    @Override
    public boolean isFull() {
        return false;
    }
    
    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    @Override
    public T find(Predicate<T> predicate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void filter(Predicate<T> predicate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T getEntry(T entry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator getIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getPosition(T entry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean cNumberofGroup() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void maxNumberofGroup(int entry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getMaxNumberofGroup() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void bubbleSort(int[] entry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}


