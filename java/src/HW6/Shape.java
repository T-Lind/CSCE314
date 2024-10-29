package HW6;
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2
 
   Student Name:
   UIN:
   Acknowledgements:
*/

abstract class Shape implements Comparable<Shape> {
  public Point position;
  protected double area;  // Changed to protected

  public Shape(Point p) {
      this.position = p;
      this.area = area();
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Shape)) return false;
      Shape shape = (Shape) o;
      return Double.compare(shape.area(), area()) == 0 && position.equals(shape.position);
  }

  public abstract double area();

  @Override
  public int compareTo(Shape s) {
      return Double.compare(this.area(), s.area());
  }
}