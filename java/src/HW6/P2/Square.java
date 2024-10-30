package HW6.P2;
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2

   Student Name: Tiernan Lindauer
   UIN: 134003853
   Acknowledgements:
*/

class Square extends Shape {
    private double side; // side is the side length

    // constructor that accepts a Point (for position) and a double
    // (for the side length).
    public Square(Point p0, double side) {
        super(p0);
        this.side = side;
    }

    // implement equals(), hashCode(), area(), and toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Square square)) {
            return false;
        }
        return Double.compare(square.side, side) == 0 && position.equals(square.position);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(side) * 31 + position.hashCode();
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public String toString() {
        return "Square at " + position + " with side " + side;
    }
} // end of class Square