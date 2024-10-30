package HW6.P2;
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2

   Student Name: Tiernan Lindauer
   UIN: 134003853
   Acknowledgements:
*/

public final class Point {
    public double x;
    public double y;

    // constructor that sets the values of x and y
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // implement equals, hashCode(), toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point point)) {
            return false;
        }
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) * 31 + Double.hashCode(y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
} // end of class Point