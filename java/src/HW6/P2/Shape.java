package HW6.P2;
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2
 
   Student Name: Tierna Lindauer
   UIN: 134003853
   Acknowledgements:
*/

abstract class Shape implements Comparable<Shape> {
    public Point position;
    public double area;

    // constructor that sets position as the Point passed as an argument
    // signature: Shape (Point)
    // implement the constructor

    // implement equals()
    public Shape(Point p) {
        this.position = p;
        this.area = area();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shape shape)) {
            return false;
        }
        return Double.compare(shape.area(), area()) == 0 && position.equals(shape.position);
    }

    // area() should be abstract
    public abstract double area();

    // implement compareTo()
    @Override
    public int compareTo(Shape s) {
        return Double.compare(this.area(), s.area());
    }
} // end of class Shape