package adt;

/**
 * @author Tongkitming/ Fong Wei Sheng/ Yu Yung Jun/ Ho Wei Young/ Low Su Yin
 * @version 2.0
 */

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Predicate;

public class ArrayList<T> implements ListInterface<T>, Serializable{

  private T[] array;
  private int numberOfEntries;
  private int numberofGroup;
  private static final int DEFAULT_CAPACITY = 5;
 

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    numberOfEntries = 0;
    array = (T[]) new Object[initialCapacity];
  }
  
  @Override
  public Iterator<T> getIterator(){
        Iterator<T> iterator = new Iterator<T>(){
            private int index = 0;
      
            public boolean hasNext(){
            return index < numberOfEntries;
            }
            
            public T next(){
                if(!hasNext()){
                    return null;
                }
                T entry = array[index];
                index++;
                return entry;
            }
      };
        return iterator;        
  }

  @Override
  public boolean add(T newEntry) {
    if (isArrayFull()) {
      doubleArray();
    }

    array[numberOfEntries] = newEntry;
    numberOfEntries++;
    return true;
  }

  @Override
  public boolean add(int newPosition, T newEntry) {
    boolean isSuccessful = true;

    if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
      if (isArrayFull()) {
        doubleArray();
      }
      makeRoom(newPosition);
      array[newPosition - 1] = newEntry;
      numberOfEntries++;
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }

  @Override
  public T remove(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition - 1];

      if (givenPosition < numberOfEntries) {
        removeGap(givenPosition);
      }

      numberOfEntries--;
    }

    return result;
  }

  @Override
  public void clear() {
    numberOfEntries = 0;
  }

  @Override
  public boolean replace(int givenPosition, T newEntry) {
    boolean isSuccessful = true;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      array[givenPosition - 1] = newEntry;
    } else {
      isSuccessful = false;
    }

    return isSuccessful;
  }
  
  
  @Override
  public T getEntry(T entry) {
    T result = null;
    for(int i=0;i<numberOfEntries;i++){
        if(array[i].equals(entry)){
            result = array[i];
            break;
        }
    }

    return result;
  }
  
 
  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition - 1];
    }

    return result;
  }

  @Override
  public boolean contains(T anEntry) {
    boolean found = false;
    for (int index = 0; !found && (index < numberOfEntries); index++) {
      if (anEntry.equals(array[index])) {
        found = true;
      }
    }
    return found;
  }

  @Override
  public int getNumberOfEntries() {
    return numberOfEntries;
  }

  @Override
  public boolean isEmpty() {
    return numberOfEntries == 0;
  }

  @Override
  public boolean isFull() {
    return false;
  }

  private void doubleArray() {
    T[] oldArray = array;
    array = (T[]) new Object[oldArray.length * 2];
    for (int i = 0; i < oldArray.length; i++) {
      array[i] = oldArray[i];
    }
  }

  private boolean isArrayFull() {
    return numberOfEntries == array.length;
  }

  @Override
  public String toString() {
    String outputStr = "";
    for (int index = 0; index < numberOfEntries; ++index) {
      outputStr += array[index] + "\n";
    }

    return outputStr;
  }

  /**
   * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
   * newPosition <= numberOfEntries + 1; numberOfEntries is array's
   * numberOfEntries before addition.
   */
  private void makeRoom(int newPosition) {
    int newIndex = newPosition - 1;
    int lastIndex = numberOfEntries - 1;

    // move each entry to next higher index, starting at end of
    // array and continuing until the entry at newIndex is moved
    for (int index = lastIndex; index >= newIndex; index--) {
      array[index + 1] = array[index];
    }
  }

  /**
   * Task: Shifts entries that are beyond the entry to be removed to the next
   * lower position. Precondition: array is not empty; 1 <= givenPosition <
   * numberOfEntries; numberOfEntries is array's numberOfEntries before removal.
   */
  private void removeGap(int givenPosition) {
    // move each entry to next lower position starting at entry after the
    // one removed and continuing until end of array
    int removedIndex = givenPosition - 1;
    int lastIndex = numberOfEntries - 1;

    for (int index = removedIndex; index < lastIndex; index++) {
      array[index] = array[index + 1];
    }
  }

    @Override
    public int getPosition(T entry) {
      int n = 0;
      for(int i=0;i<numberOfEntries;i++){
        if(array[i].equals(entry)){
            n = i+1;
            break;
        }
  }
      return n;
    }
    
    @Override
  public T get(int index) {
        if (index < 0 || index >= numberOfEntries) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

   

@Override
  public boolean cNumberofGroup(){
      return numberofGroup == 0;
  }
  @Override
  public void maxNumberofGroup(int entry){
          numberofGroup = entry;
  }
  @Override
  public int getMaxNumberofGroup(){
      return numberofGroup;
  }
    
    
       @Override
    public T find(Predicate<T> predicate) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (predicate.test(array[index])) {
                return array[index];
            }
        }
        return null;
    }
        @Override
    public void listAll() {
        for (int index = 0; index < numberOfEntries; index++) {
            System.out.println(array[index]); // `toString` method
        }
    }

    @Override
    public void filter(Predicate<T> predicate) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (predicate.test(array[index])) {
                System.out.println(array[index]);
            }
        }
    }

    @Override
    public T find(String tutorName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void filter(String criteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void bubbleSort(int[] entry) {
        int n = numberOfEntries;
        T temp = null;
        int temp1=0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (entry[j - 1] > entry[j]) {
                    //swap elements  
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    temp1 = entry[j - 1];
                    entry[j - 1] = entry[j];
                    entry[j] = temp1;
                }
            }
        }

    }
    
}
