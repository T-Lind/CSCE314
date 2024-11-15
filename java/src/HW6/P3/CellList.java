package HW6.P3;
/*   
   CellList.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 4

   Student Name: Tiernan Lindauer
   UIN: 134003853
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

    // Task 1: override clone() (5 points)
    @Override
    public CellList<E> clone() {
        CellList<E> cloned = new CellList<>();
        for (E item : this) {
            cloned.push(item);
        }
        CellList<E> result = new CellList<>();
        for (E item : cloned) {   // reverse the order
            result.push(item);
        }
        return result;
    }

    @Override
    public int compareTo(CellList<E> list) {
        return Integer.compare(this.length, list.length);
    }

    // Task 2: override equals() (10 points)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CellList<?> other)) {  // other is a pattern variable that is automatically cast to CellList<?> if type check passes
            return false;
        }

        if (this.length != other.length) {
            return false;
        }

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

    // no-arg constructor - given
    public CellList() {
        n = null;
        length = 0;
    }

    // Task 3: One-arg constructor (5 ponts)
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

    // Task 4: total 20 points for toString(), push() and pop()
    // 8 points
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

    // 5 points
    public void push(E item) {
        n = new Cell<>(item, n);
        length++;
    }

    // 7 points
    public E pop() {
        if (n == null) return null;
        E val = n.getVal();
        n = n.getNext();
        length--;
        return val;
    }

    // given
    public E peek() {
        return n.getVal();
    }

    // given
    public int getLength() {
        return length;
    }
}
