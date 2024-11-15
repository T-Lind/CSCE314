package HW7;
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 7 Problem 2

   Student Name: Tiernan Lindauer
   UIN: 134003853
   Acknowledgements:
*/

import java.util.*;

public class Market<T> {
    List<T> stock; // stock of the market

    public Market() {
        stock = new java.util.LinkedList<T>(); // init stock
    }

    void sell(T item) {
        stock.add(item); // add item to stock
    }

    public T buy() {
        return stock.removeFirst(); // remove 1st item from stock
    }

    void sell(Collection<? extends T> items) {
        stock.addAll(items); // add all items to stock
    }

    void buy(int n, Collection<? super T> items) {
        for (int i = 0; i < n && !stock.isEmpty(); i++) {
            items.add(stock.removeFirst()); // add n items to items
        }
    }
} // end of class Market

// Study class Main. You don't need to modify class Main
class Main {
    // three static nested classes expressing example subclass hierarchy
    // Gala <: Apple <: Fruit
    static class Fruit {
        public String toString() {
            return "Fruit"; // return "Fruit"
        }
    }

    static class Apple extends Fruit {
        public String toString() {
            return "Apple"; // return "Apple"
        }
    }

    static class Gala extends Apple {
        public String toString() {
            return "Gala"; // return "Gala"
        }
    }

    public static void main(String args[]) {
        Market<Fruit> farmersmarket = new Market<Fruit>(); // create market

        Deque<Fruit> fruits = new ArrayDeque<Fruit>(); // create deque
        fruits.addFirst(new Gala()); // add Gala
        fruits.addFirst(new Apple()); // add Apple

        Vector<Apple> apples = new Vector<Apple>(); // create vector
        apples.addElement(new Apple()); // add Apple
        apples.addElement(new Apple()); // add Apple
        apples.addElement(new Gala()); // add Gala

        farmersmarket.sell(fruits); // sell fruits
        farmersmarket.sell(apples); // sell apples
        farmersmarket.sell(new Fruit()); // sell Fruit
        farmersmarket.sell(new Gala()); // sell Gala

        ArrayList<Fruit> mybasket = new ArrayList<Fruit>(); // create basket

        farmersmarket.buy(6, mybasket); // buy 6 items

        // print out what you bought
        System.out.println("Here's what I bought");
        for (Fruit e : mybasket) System.out.println(e); // print each item
        System.out.println("Enjoy!"); // print Enjoy!
    } // end of main
} // end of class Main