package HW6;
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2
 
   Student Name:
   UIN:
   Acknowledgements:
*/

class TotalAreaCalculator {
    public static double calculate(Shape[] shapes) {
        double totalArea = 0.0;
        for (Shape shape : shapes) {
            totalArea += shape.area();
        }
        return totalArea;
    }
}