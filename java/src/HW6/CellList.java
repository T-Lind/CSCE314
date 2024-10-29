package HW6;
/*   
   CellList.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 4

   Student Name:
   UIN:
   Acknowledgements:
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

// Total 40 points for the CellList class

public class CellList<E> implements Iterable<E>, Cloneable, Comparable<CellList<E>> {
    private Cell<E> n;
    private int length;

    @Override
    public Iterator<E> iterator() {
        return n.iterator();
    }

    // Task 1: Clone implementation
    @Override
    public CellList<E> clone() {
        CellList<E> cloned = new CellList<>();
        for (E item : this) {
            cloned.push(item);
        }
        // Reverse the list since push adds to front
        CellList<E> result = new CellList<>();
        for (E item : cloned) {
            result.push(item);
        }
        return result;
    }

    @Override
    public int compareTo(CellList<E> list) {
        if (this.length < list.length) return -1;
        if (this.length == list.length) return 0;
        return 1;
    }

    // Task 2: Equals implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CellList<?>)) return false;
        CellList<?> other = (CellList<?>) o;

        if (this.length != other.length) return false;

        // Convert both lists to arrays for comparison
        ArrayList<Object> thisList = new ArrayList<>();
        ArrayList<Object> otherList = new ArrayList<>();

        for (E item : this) thisList.add(item);
        for (Object item : other) otherList.add(item);

        Object[] thisArray = thisList.toArray();
        Object[] otherArray = otherList.toArray();

        Arrays.sort(thisArray);
        Arrays.sort(otherArray);

        return Arrays.equals(thisArray, otherArray);
    }

    @Override
    public int hashCode() {
        return length;
    }

    // No-arg constructor
    public CellList() {
        n = null;
        length = 0;
    }

    // Task 3: One-arg constructor
    public CellList(Iterable<E> iterable) {
        this();
        ArrayList<E> temp = new ArrayList<>();
        for (E item : iterable) {
            temp.add(item);
        }
        for (int i = temp.size() - 1; i >= 0; i--) {
            push(temp.get(i));
        }
    }

    // Task 4: toString implementation
    public String toString() {
        if (n == null) return "[(head: )]";

        StringBuilder sb = new StringBuilder("[(head: ");
        Cell<E> current = n;
        sb.append(current.getVal());
        current = current.getNext();

        while (current != null) {
            sb.append(") -> (");
            sb.append(current.getVal());
            current = current.getNext();
        }
        sb.append(")]");
        return sb.toString();
    }

    // Task 4: push implementation
    public void push(E item) {
        n = new Cell<>(item, n);
        length++;
    }

    // Task 4: pop implementation
    public E pop() {
        if (n == null) return null;
        E val = n.getVal();
        n = n.getNext();
        length--;
        return val;
    }

    // Given methods
    public E peek() {
        return n.getVal();
    }

    public int getLength() {
        return length;
    }
}
