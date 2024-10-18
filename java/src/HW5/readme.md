Title: Homework 5 README

## First, write your name, UIN, and acknowledge any help received

## in doing this assignment

Student Name: Tiernan Lindauer
UIN: 134003853
Acknowledgements:

## Also, state which version of Java on which machine you used to

## compile and run your programs

java 20.0.1 2023-04-18
Java(TM) SE Runtime Environment (build 20.0.1+9-29)
Java HotSpot(TM) 64-Bit Server VM (build 20.0.1+9-29, mixed mode, sharing)
----------
Problem 2
----------

## Explain how to compile (using javac at the terminal) and execute your code

## (using java at the terminal), and what is the expected output of your code

## when tested (for each task/functionality).

Compile: navigate to java/src directory, run

```sh
javac HW5/Fibonacci.java
```

And to run, execute SubsetOutputFib with two integers as arguments,

```sh
java HW5.SubsetOutputFib 3 6
```

Expected output:

```
3: 2
4: 3
5: 5
6: 8
```

It will also take the absolute value of negative arguments and switch the values so that the start is <= end.

----------
Problem 3
----------

To compile and run ImprovedFibonacci, navigate to the java/src directory and run

```sh
javac HW5/Fibonacci.java
```
Then to run it, simply run
```sh
java HW5.ImprovedFibonacci
```
Expected output:
```
1: 1
2: 1
3: 2 *
4: 3
5: 5
6: 8 *
7: 13
8: 21
9: 34 *
```

Note that a * is placed next to even numbers.

----------
Problem 4
----------
To compile and run Vehicle we need to run VehicleTest, so
```sh
javac HW5/Vehicle.java
```
Then to run it, simply run
```sh
java HW5.VehicleTest
```
You should see this output:

```
Vehicle{currentSpeed=50, currentDirection=0, ownerName='Owner0', vehicleID=1}
Vehicle{currentSpeed=51, currentDirection=10, ownerName='Owner1', vehicleID=2}    
Vehicle{currentSpeed=52, currentDirection=20, ownerName='Owner2', vehicleID=3}    
Vehicle{currentSpeed=53, currentDirection=30, ownerName='Owner3', vehicleID=4}    
Vehicle{currentSpeed=54, currentDirection=40, ownerName='Owner4', vehicleID=5}    
Vehicle{currentSpeed=55, currentDirection=50, ownerName='Owner5', vehicleID=6}    
Vehicle{currentSpeed=56, currentDirection=60, ownerName='Owner6', vehicleID=7}    
Vehicle{currentSpeed=57, currentDirection=70, ownerName='Owner7', vehicleID=8}    
Vehicle{currentSpeed=58, currentDirection=80, ownerName='Owner8', vehicleID=9}    
Vehicle{currentSpeed=59, currentDirection=90, ownerName='Owner9', vehicleID=10}  
```

----------
Problem 9
----------
To test PassengerVehicle, run
```sh
javac HW5/Vehicle.java
```
Then to run it, simply run
```sh
java HW5.PassengerVehicle
```
And you should get:
```
sorted in ascending order:
PassengerVehicle{totalSeats=3, occupiedSeats=0, availableSeats=3, ownerName='Owner4'}
PassengerVehicle{totalSeats=5, occupiedSeats=0, availableSeats=5, ownerName='Owner2'}
PassengerVehicle{totalSeats=6, occupiedSeats=0, availableSeats=6, ownerName='Owner5'}
PassengerVehicle{totalSeats=7, occupiedSeats=0, availableSeats=7, ownerName='Owner1'}
PassengerVehicle{totalSeats=9, occupiedSeats=0, availableSeats=9, ownerName='Owner3'}
sorted in descending order:
PassengerVehicle{totalSeats=9, occupiedSeats=0, availableSeats=9, ownerName='Owner3'}
PassengerVehicle{totalSeats=7, occupiedSeats=0, availableSeats=7, ownerName='Owner1'}
PassengerVehicle{totalSeats=6, occupiedSeats=0, availableSeats=6, ownerName='Owner5'}
PassengerVehicle{totalSeats=5, occupiedSeats=0, availableSeats=5, ownerName='Owner2'}
PassengerVehicle{totalSeats=3, occupiedSeats=0, availableSeats=3, ownerName='Owner4'}
```

## End of README
