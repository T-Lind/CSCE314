package HW6;
/* Cell.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 3 

   Student Name:
   UIN:
   Acknowledgements:
*/

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

// class Cell: 10 points
// give correct class header - given in the problem statement
public final class Cell<E> implements Iterable<E> {
    private E elem;
    private Cell<E> next;

    public Cell(E elem, Cell<E> next) {
        this.elem = elem;
        this.next = next;
    }

    @Override
    public CellIterator<E> iterator() {
        return new CellIterator<>(this);
    }

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

    class CellIterator<T> implements Iterator<T> {
        private Cell<T> p;

        public CellIterator(Cell<T> n) {
            this.p = n;
        }

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T val = p.getVal();
            p = p.getNext();
            return val;
        }
    }
}
