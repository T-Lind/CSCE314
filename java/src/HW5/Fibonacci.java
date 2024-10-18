package HW5;
/* CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
   Homework Assignment 5 
   Skeleton for Problems 2 & 3
   Written by Hyunyoung Lee for CSCE 314 Students

   Student Name: Tiernan Lindauer
   Student UIN: 134003853
   Acknowledgements:
*/

class SubsetOutputFib {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please provide exactly two integer arguments.");
            return;
        }

        int be = Integer.parseInt(args[0]);
        int en = Integer.parseInt(args[1]);

        if (be < 0) {
            System.out.println("Negative start value detected. Converting to positive.");
            be = Math.abs(be);
        }
        if (en < 0) {
            System.out.println("Negative end value detected. Converting to positive.");
            en = Math.abs(en);
        }
        if (be > en) {
            System.out.println("Start value greater than end value. Swapping values.");
            int temp = be;
            be = en;
            en = temp;
        }

        int[] fibonacciSequence = new int[en + 1];
        for (int i = 0; i <= en; i++) {
            fibonacciSequence[i] = fibonacci(i);
        }

        for (int i = be; i <= en; i++) {
            System.out.println(i + ": " + fibonacciSequence[i]);
        }
    }

    private static int fibonacci(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}

class FibonacciNum {
    int value;
    boolean isEven;

    FibonacciNum(int value) {
        this.value = value;
        this.isEven = (value % 2 == 0);
    }
}

class ImprovedFibonacci {
    public static void main(String[] args) {
        final int MAX_INDEX = 9;
        FibonacciNum[] sequence = new FibonacciNum[MAX_INDEX];

        int lo = 1;
        int hi = 1;
        sequence[0] = new FibonacciNum(lo);

        for (int i = 1; i < MAX_INDEX; i++) {
            sequence[i] = new FibonacciNum(hi);
            hi = lo + hi;
            lo = hi - lo;
        }

        for (int i = 0; i < MAX_INDEX; i++) {
            String mark = sequence[i].isEven ? " *" : "";
            System.out.println((i + 1) + ": " + sequence[i].value + mark);
        }
    }
}
