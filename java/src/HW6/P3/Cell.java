package HW6.P3;
/* Cell.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 3 

   Student Name: Tiernan Lindauer
   UIN: 134003853
   Acknowledgements:
*/

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

// class Cell: 10 points
// give correct class header - given in the problem statement
public final class Cell<E> implements Iterable<E> {
    // private fields
    private E elem; // stores a value of type E
    private Cell<E> next; // link to the next Cell

    // constructor
    public Cell(E elem, Cell<E> next) {
        this.elem = elem;
        this.next = next;
    }

    // iterator() returns a CellIterator object for this object
    @Override
    public CellIterator<E> iterator() {
        return new CellIterator<>(this);
    }

    // getter and setter methods for the private fields
    public E getVal() {
        return elem;
    }

    public void setVal(E v) {
        this.elem = v;
    }

    public Cell<E> getNext() {
        return next;
    }

    public void setNext(Cell<E> node) {
        this.next = node;
    }

    // CellIterator: 20 points
    // Having CellIterator as an inner class of Cell makes sense...
    // (2 points) correct class header - given in the problem statement
    class CellIterator<T> implements Iterator<T> {
        private Cell<T> p;  // given

        // (3 points) constructor
        public CellIterator(Cell<T> n) {
            this.p = n;
        }

        // (5+10=15 points) two methods to implement the Iterator interface
        // (5 points) hasNext()
        @Override
        public boolean hasNext() {
            return p != null;
        }

        // (10 points) next()
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T val = p.getVal();
            p = p.getNext();
            return val;
        }
    } // end of CellIterator
} // end of Cell
