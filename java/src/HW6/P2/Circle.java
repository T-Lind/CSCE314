package HW6.P2;
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2

   Student Name: Tiernan Lindauer
   UIN: 134003853
   Acknowledgements:
*/

import java.lang.Math;

class Circle extends Shape {
    private double radius;

    // constructor that accepts a Point (for position) and a double
    // (for the radius).
    public Circle(Point p0, double r) {
        super(p0);
        this.radius = r;
    }

    // implement equals(), hashCode(), area(), and toString()
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Circle circle)) {
            return false;
        }
        return Double.compare(circle.radius, radius) == 0 && position.equals(circle.position);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius) * 31 + position.hashCode();
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle at " + position + " with radius " + radius;
    }
} // end of class Circle