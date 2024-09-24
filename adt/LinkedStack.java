package adt;

import java.util.EmptyStackException;

/**
 *
 * @author Tongkitming
 */

public class LinkedStack<T> implements StackInterface<T> {

    private Node topNode;
    private int numOfEntry;

    public LinkedStack() {
        topNode = null;
    }

    @Override
    public void push(T newEntry) { //enchanced
        // Check if the newEntry is null (optional)
        if (newEntry == null) {
            throw new IllegalArgumentException("Cannot push a null entry onto the stack.");
        }

        // Create a new node with the newEntry and set its next reference to the current topNode
        Node newNode = new Node(newEntry, topNode);

        // Update the topNode to the new node, effectively adding the new entry to the top of the stack
        topNode = newNode;

        numOfEntry++;
    }

    @Override
    public T peek() {
        T top = topNode.data;
        return top;
    }

    @Override
    public T pop() {//enchanced
        if (isEmpty()) {
            throw new EmptyStackException(); // Handle empty stack condition
        }

        T top = topNode.data; // Get the data from the top node
        topNode = topNode.next; // Move the topNode to the next node (removing the current top)
        numOfEntry--; // Decrement the count of entries
        return top; // Return the removed element

    }
    


    @Override
    public void clear() {
        topNode = null; // Set the topNode to null to clear the stack
    }

    @Override
    public int getNumOfEntry() {
        return this.numOfEntry;
    }

    @Override
    public boolean isEmpty() {
        return topNode == null;
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
