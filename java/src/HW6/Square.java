package HW6;
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2

   Student Name:
   UIN:
   Acknowledgements:
*/

import java.lang.Math;

class Square extends Shape {
    private double side;

    public Square(Point p0, double side) {
        super(p0);
        this.side = side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
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
}