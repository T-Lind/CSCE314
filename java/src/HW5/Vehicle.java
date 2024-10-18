package HW5;
/* CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
   Homework Assignment 5 
   Skeleton for Problems 4-9
   Written by Hyunyoung Lee for CSCE 314 Students

   Student Name: Tiernan Lindauer
   Student UIN: 134003853
   Acknowledgements:
*/

import java.util.ArrayList;
import java.util.Collections;

class Vehicle {
    enum TurnDirection {
        TURN_LEFT, TURN_RIGHT;  // simple enum for turn direction
    }

    private int currentSpeed;
    private int currentDirection;
    private String ownerName;
    private final int vehicleID;  // instance variables useful for car objects
    private static int nextVehicleID = 1;  // static variable to keep track of the next id to use

    public static int getHighestIdUsed() {
        return nextVehicleID - 1;  // static method to get the highest id used
    }

    public Vehicle() {
        this.vehicleID = nextVehicleID++;  // default constructor for vehicle setting id
    }

    public Vehicle(String ownerName) {
        this();  // calls default constructor
        this.ownerName = ownerName;  // sets owner name
    }

    public int getCurrentSpeed() {
        return currentSpeed;  // getter for current speed
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;  // setter for current speed
    }

    public int getCurrentDirection() {
        return currentDirection;  // getter for current direction
    }

    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;  // setter for current direction
    }

    public String getOwnerName() {
        return ownerName;  // getter for owner name
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;  // setter for owner name
    }

    public int getVehicleID() {
        return vehicleID;  // getter for vehicle id
    }

    public void changeSpeed(int newSpeed) {
        this.currentSpeed = newSpeed;  // method to change speed
    }

    public void stop() {
        this.currentSpeed = 0;  // method to stop the vehicle
    }

    public void turn(int degrees) {
        this.currentDirection = (this.currentDirection + degrees) % 360;  // turns vehicle, also handles negative degrees
    }

    public void turn(TurnDirection direction) {  // turns vehicle left or right and handles negative degrees by looping
        if (direction == TurnDirection.TURN_LEFT) {
            this.currentDirection = (this.currentDirection - 90 + 360) % 360;
        } else if (direction == TurnDirection.TURN_RIGHT) {
            this.currentDirection = (this.currentDirection + 90) % 360;
        }
    }

    @Override
    public String toString() {  // toString method to print vehicle info
        return "Vehicle{" +
                "currentSpeed=" + currentSpeed +
                ", currentDirection=" + currentDirection +
                ", ownerName='" + ownerName + '\'' +
                ", vehicleID=" + vehicleID +
                '}';
    }
}

class VehicleTest {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[10];  // create array of vehicles

        for (int i = 0; i < 5; i++) {  // fill array with vehicles
            vehicles[i] = new Vehicle();
            vehicles[i].setCurrentSpeed(50 + i);  // set speed and direction
            vehicles[i].setCurrentDirection(10 * i);
            vehicles[i].setOwnerName("Owner" + i);  // set owner name
        }

        for (int i = 5; i < 10; i++) {  // fill array with vehicles
            vehicles[i] = new Vehicle("Owner" + i);
            vehicles[i].setCurrentSpeed(50 + i);
            vehicles[i].setCurrentDirection(10 * i);
        }

        for (Vehicle vehicle : vehicles) {  // print vehicle info using toString
            System.out.println(vehicle);
        }

        // test changeSpeed method
        System.out.println("\nTesting changeSpeed method:");
        vehicles[0].changeSpeed(100);
        System.out.println("Vehicle 0 new speed: " + vehicles[0].getCurrentSpeed());

        // test stop method
        System.out.println("\nTesting stop method:");
        vehicles[1].stop();
        System.out.println("Vehicle 1 new speed: " + vehicles[1].getCurrentSpeed());

        // test turn method with degrees
        System.out.println("\nTesting turn method with degrees:");
        vehicles[2].turn(45);
        System.out.println("Vehicle 2 new direction: " + vehicles[2].getCurrentDirection());

        // test turn method with direction
        System.out.println("\nTesting turn method with direction:");
        vehicles[3].turn(Vehicle.TurnDirection.TURN_LEFT);
        System.out.println("Vehicle 3 new direction: " + vehicles[3].getCurrentDirection());
        vehicles[4].turn(Vehicle.TurnDirection.TURN_RIGHT);
        System.out.println("Vehicle 4 new direction: " + vehicles[4].getCurrentDirection());
    }
}

class PassengerVehicle extends Vehicle implements Comparable<PassengerVehicle> {
    private int totalSeats;
    private int occupiedSeats;  // instance variables for passenger vehicles that add upon vehicle

    public PassengerVehicle() {
        super();  // calls default constructor for vehicle
    }

    public PassengerVehicle(String ownerName) {
        super(ownerName);  // calls constructor for vehicle with owner name
    }

    public PassengerVehicle(String ownerName, int totalSeats) {
        super(ownerName);  // calls constructor for vehicle with owner name
        this.totalSeats = totalSeats;  // sets total seats
    }

    public int getTotalSeats() {
        return totalSeats;  // getter for total seats
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;  // setter for total seats
    }

    public int getOccupiedSeats() {
        return occupiedSeats;  // getter for occupied seats
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;  // setter for occupied seats
    }

    public int getAvailableSeats() {
        return totalSeats - occupiedSeats;  // method to get available seats
    }

    @Override
    public String toString() {  // toString method to print passenger vehicle info
        return "PassengerVehicle{" +
                "totalSeats=" + totalSeats +
                ", occupiedSeats=" + occupiedSeats +
                ", availableSeats=" + getAvailableSeats() +
                ", ownerName='" + getOwnerName() + '\'' +
                '}';
    }

    @Override
    public int compareTo(PassengerVehicle other) {  // compareTo method to compare passenger vehicles by total seats
        return Integer.compare(this.totalSeats, other.totalSeats);
    }

    public static void main(String[] args) {
        ArrayList<PassengerVehicle> passengerVehicleArrayList = new ArrayList<>();  // create array list of passenger vehicles
        passengerVehicleArrayList.add(new PassengerVehicle("Owner1", 7));
        passengerVehicleArrayList.add(new PassengerVehicle("Owner2", 5));
        passengerVehicleArrayList.add(new PassengerVehicle("Owner3", 9));
        passengerVehicleArrayList.add(new PassengerVehicle("Owner4", 3));
        passengerVehicleArrayList.add(new PassengerVehicle("Owner5", 6));

        Collections.sort(passengerVehicleArrayList);  // sort passenger vehicles by total seats, which is how compareTo was defined

        System.out.println("sorted in ascending order:");
        for (PassengerVehicle passengerVehicle : passengerVehicleArrayList) {
            System.out.println(passengerVehicle);  // print passenger vehicles in ascending order
        }

        System.out.println("sorted in descending order:");
        for (int i = passengerVehicleArrayList.size() - 1; i >= 0; i--) {
            System.out.println(passengerVehicleArrayList.get(i));  // print passenger vehicles in descending order
        }
    }
}
