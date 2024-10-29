package HW6;
/* CellTest.java skeleton written by Hyunyoung Lee 
   CSCE 314 [Sections 598, 599] Fall 2024  
   Assignment 6 Problem 3 Test class 
   class CellTest

   Student Name: 
   UIN:
   Acknowledgements: 
*/

public class CellTest {
    // Sum integers in a linked list
    public static int int_sum(Cell<Integer> list) {
        if (list == null) return 0;

        int sum = 0;
        for (Integer num : list) {
            sum += num;
        }
        return sum;
    }

    // Sum any Number type (Double, Integer, etc.) using bounded wildcard
    public static double num_sum(Cell<? extends Number> list) {
        if (list == null) return 0.0;

        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    // Print any type of list
    public static <T> void print(Cell<T> list) {
        if (list == null) {
            System.out.println("Empty list");
            return;
        }

        for (T item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create an Integer list: 1 -> 22 -> 21 -> 12 -> 24 -> 17
        Cell<Integer> intlist = new Cell<>(1,
                new Cell<>(22,
                        new Cell<>(21,
                                new Cell<>(12,
                                        new Cell<>(24,
                                                new Cell<>(17, null))))));

        // Create a Double list: 1.0 -> 16.0 -> 13.72 -> 5.0 -> 22.0 -> 7.1
        Cell<Double> doublelist = new Cell<>(1.0,
                new Cell<>(16.0,
                        new Cell<>(13.72,
                                new Cell<>(5.0,
                                        new Cell<>(22.0,
                                                new Cell<>(7.1, null))))));

        System.out.println("===");
        print(intlist);
        System.out.println("sum of intlist is " + int_sum(intlist));
        System.out.println("sum of null list is " + int_sum(null));
        System.out.println("===");

        System.out.println("===");
        print(doublelist);
        System.out.println("sum ints = " + num_sum(intlist));
        System.out.println("sum doubles = " + num_sum(doublelist));
        System.out.println("===");
    }
}
