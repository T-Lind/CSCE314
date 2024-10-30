Title: Homework 6 README
Name: Tiernan Lindauer
UIN: 134003853

----------
Problem 2: Shapes
----------
Compilation:
cd P2
javac *.java

Execution:
cd P2
java Main S input.txt

Expected Output:
general overview:
- read shapes from input.txt
- display the original list of shapes
- display shapes sorted by area in ascending order
- display the total area of all shapes
- shape will be displayed in format as the following:
  Circle: center=(x,y), radius=r
  Square: upper-left=(x,y), side=s

Ok here's hte actual output:
===
s ( 14.5, 1.0 ) 12.5
c ( 23, 37.5 ) 19
s ( 6.5, 11.1 ) 25
c ( 30, 50 ) 3
c ( 51, 15 ) 4.1
s ( 30, 60 ) 12
c ( 16.5, 10.5 ) 52
s ( 73.5, 35 ) 100
s ( 16.5, 10.5 ) 52
c ( 6.5, 11.1 ) 20
s ( 26.5, 41.1 ) 5
The input file contains 11 shapes.
11 shapes have been created

Inital shapes:
Square at (14.5, 1.0) with side 12.5
Circle at (23.0, 37.5) with radius 19.0
Square at (6.5, 11.1) with side 25.0
Circle at (30.0, 50.0) with radius 3.0
Circle at (51.0, 15.0) with radius 4.1
Square at (30.0, 60.0) with side 12.0
Circle at (16.5, 10.5) with radius 52.0
Square at (73.5, 35.0) with side 100.0
Square at (16.5, 10.5) with side 52.0
Circle at (6.5, 11.1) with radius 20.0
Square at (26.5, 41.1) with side 5.0

Shapes sorted (area):
Square at (26.5, 41.1) with side 5.0 (area: 25.00)
Circle at (30.0, 50.0) with radius 3.0 (area: 28.27)
Circle at (51.0, 15.0) with radius 4.1 (area: 52.81)
Square at (30.0, 60.0) with side 12.0 (area: 144.00)
Square at (14.5, 1.0) with side 12.5 (area: 156.25)
Square at (6.5, 11.1) with side 25.0 (area: 625.00)
Circle at (23.0, 37.5) with radius 19.0 (area: 1134.11)
Circle at (6.5, 11.1) with radius 20.0 (area: 1256.64)
Square at (16.5, 10.5) with side 52.0 (area: 2704.00)
Circle at (16.5, 10.5) with radius 52.0 (area: 8494.87)
Square at (73.5, 35.0) with side 100.0 (area: 10000.00)

Total area of all shapes: 24620.95
===


----------
Problem 3: Cell Iterator
----------
Compilation:
javac P3/*.java

Execution:
java P3.CellTest

Expected Output:
===
1 22 21 12 24 17 
sum of intlist is 97
sum of null list is 0
===
===
1.0 16.0 13.72 5.0 22.0 7.1 
sum ints = 97.0
sum doubles = 64.82
===


----------
Problem 4: CellList
----------
Compilation (from root):
javac P3/*.java

Execution:
java P3.CellListTest

Expected Output:
Overview:
- program demonstrate CellList operations including:
  - list creation and toString output
  - push/pop operations
  - list equality comparisons
  - list cloning
  - list comparisons by length
- sample output format for lists: [(head: 1) -> (2) -> (3)]

===
stringlist = [(head: A) -> (the) -> (the) -> (dove)]
stringlist2 = [(head: A) -> (dove) -> (the) -> (the)]
stringlist3 = [(head: A) -> (dove) -> (dove) -> (the)]
stringlist equals to stringlist2 ? true
stringlist equals to stringlist3 ? false
CellList<Integer> equals to CellList<String> ? false
list  = [(head: 1) -> (2) -> (3) -> (4)]
list1 = [(head: 2) -> (4) -> (3) -> (1)]
list == list1 is false
list.equals(list1) = true
list3 = [(head: 1) -> (2) -> (3) -> (1)]
list4 = [(head: 1) -> (2) -> (3) -> (1) -> (4)]
list1.equals(list3) = false
list1.equals(list4) = false
list.compareTo(list1) = 0
list.compareTo(list4) = -1
[(head: )]
[(head: 1) -> (2) -> (3) -> (4)]
1
[(head: 22) -> (21) -> (2) -> (3) -> (4)]
22
[(head: 22) -> (21) -> (2) -> (3) -> (4)]
22 22
21 21
2 2
3 3
4 4
[(head: )]
list1 = [(head: 2) -> (4) -> (3) -> (1)]
list2 = [(head: 4) -> (3) -> (2) -> (21) -> (22) -> (1) -> (2) -> (3) -> (4)]
list2.compareTo(list1) = 1
=== end of test



Notes:
- all code compiles with Java 11 or higher
- IMPORTANT! WHEN RUNNING P2, SPECIFY THE
PROPER FILEPATH TO input.txt.
    Or, you'll need to be in the P2 directory.