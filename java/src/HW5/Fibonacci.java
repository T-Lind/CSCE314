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
            System.out.println("program needs two arguments");  // if not enough arguments, print error message
            return;
        }

        int be = Integer.parseInt(args[0]);  // get the arguments as ints
        int en = Integer.parseInt(args[1]);

        if (be < 0) {
            System.out.println("converting neg start value to pos");  // if start value is negative, convert to positive
            be = Math.abs(be);
        }
        if (en < 0) {
            System.out.println("converting neg end value to pos");  // if end value is negative, convert to positive
            en = Math.abs(en);
        }
        if (be > en) {
            System.out.println("start value is greater than end value, swapping");  // swap if start > end
            int temp = be;
            be = en;
            en = temp;
        }

        int[] fibonacciSequence = new int[en + 1];  // create array to store fibonacci numbers
        for (int i = 0; i <= en; i++) {  // fill array with fibonacci numbers
            fibonacciSequence[i] = fibonacci(i);  // call fibonacci function to get the number
        }

        for (int i = be; i <= en; i++) {
            System.out.println(i + ": " + fibonacciSequence[i]);  // print the fibonacci numbers in the range
        }
    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;  // base case
        }
        int a = 0;
        int b = 1;  // initial values
        for (int i = 2; i <= n; i++) {  // loop to calculate the fibonacci number iteratively
            int temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}

class FibNum {  // Helper object to store in arrays
    int value;
    boolean isEven;

    FibNum(int value) {
        this.value = value;
        this.isEven = (value % 2 == 0);  // if even num % 2 == 0
    }
}

class ImprovedFibonacci {
    public static void main(String[] args) {
        final int MAX_INDEX = 9;
        FibNum[] fibSeq = new FibNum[MAX_INDEX];  // create array of FibNum objects

        int low = 1;
        int high = 1;  // initial values
        fibSeq[0] = new FibNum(low);  // set the first value to 1

        for (int i = 1; i < MAX_INDEX; i++) {  // loop to calculate the fibonacci numbers and store in FibNums
            fibSeq[i] = new FibNum(high);
            high = low + high;
            low = high - low;
        }

        for (int i = 0; i < MAX_INDEX; i++) {  // print the fibonacci numbers
            String mark = fibSeq[i].isEven ? " *" : "";
            System.out.println((i + 1) + ": " + fibSeq[i].value + mark);
        }
    }
}
