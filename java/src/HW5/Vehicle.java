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
        TURN_LEFT, TURN_RIGHT
    }

    private int currentSpeed;
    private int currentDirection;
    private String ownerName;
    private final int vehicleID;
    private static int nextVehicleID = 1;

    public static int getHighestIdUsed() {
        return nextVehicleID - 1;
    }

    public Vehicle() {
        this.vehicleID = nextVehicleID++;
    }

    public Vehicle(String ownerName) {
        this();
        this.ownerName = ownerName;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void changeSpeed(int newSpeed) {
        this.currentSpeed = newSpeed;
    }

    public void stop() {
        this.currentSpeed = 0;
    }

    public void turn(int degrees) {
        this.currentDirection = (this.currentDirection + degrees) % 360;
    }

    public void turn(TurnDirection direction) {
        if (direction == TurnDirection.TURN_LEFT) {
            this.currentDirection = (this.currentDirection - 90 + 360) % 360;
        } else if (direction == TurnDirection.TURN_RIGHT) {
            this.currentDirection = (this.currentDirection + 90) % 360;
        }
    }

    @Override
    public String toString() {
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
        Vehicle[] vehicles = new Vehicle[10];

        for (int i = 0; i < 5; i++) {
            vehicles[i] = new Vehicle();
            vehicles[i].setCurrentSpeed(50 + i);
            vehicles[i].setCurrentDirection(10 * i);
            vehicles[i].setOwnerName("Owner" + i);
        }

        for (int i = 5; i < 10; i++) {
            vehicles[i] = new Vehicle("Owner" + i);
            vehicles[i].setCurrentSpeed(50 + i);
            vehicles[i].setCurrentDirection(10 * i);
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        // Test changeSpeed method
        System.out.println("\nTesting changeSpeed method:");
        vehicles[0].changeSpeed(100);
        System.out.println("Vehicle 0 new speed: " + vehicles[0].getCurrentSpeed());

        // Test stop method
        System.out.println("\nTesting stop method:");
        vehicles[1].stop();
        System.out.println("Vehicle 1 new speed: " + vehicles[1].getCurrentSpeed());

        // Test turn method with degrees
        System.out.println("\nTesting turn method with degrees:");
        vehicles[2].turn(45);
        System.out.println("Vehicle 2 new direction: " + vehicles[2].getCurrentDirection());

        // Test turn method with direction
        System.out.println("\nTesting turn method with direction:");
        vehicles[3].turn(Vehicle.TurnDirection.TURN_LEFT);
        System.out.println("Vehicle 3 new direction: " + vehicles[3].getCurrentDirection());
        vehicles[4].turn(Vehicle.TurnDirection.TURN_RIGHT);
        System.out.println("Vehicle 4 new direction: " + vehicles[4].getCurrentDirection());
    }
}
class PassengerVehicle extends Vehicle implements Comparable<PassengerVehicle> {
    private int totalSeats;
    private int occupiedSeats;

    public PassengerVehicle() {
        super();
    }

    public PassengerVehicle(String ownerName) {
        super(ownerName);
    }

    public PassengerVehicle(String ownerName, int totalSeats) {
        super(ownerName);
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(int occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - occupiedSeats;
    }

    @Override
    public String toString() {
        return "PassengerVehicle{" +
                "totalSeats=" + totalSeats +
                ", occupiedSeats=" + occupiedSeats +
                ", availableSeats=" + getAvailableSeats() +
                ", ownerName='" + getOwnerName() + '\'' +
                '}';
    }

    @Override
    public int compareTo(PassengerVehicle other) {
        return Integer.compare(this.totalSeats, other.totalSeats);
    }

    public static void main(String[] args) {
        ArrayList<PassengerVehicle> pVs = new ArrayList<>();
        pVs.add(new PassengerVehicle("Owner1", 7));
        pVs.add(new PassengerVehicle("Owner2", 5));
        pVs.add(new PassengerVehicle("Owner3", 9));
        pVs.add(new PassengerVehicle("Owner4", 3));
        pVs.add(new PassengerVehicle("Owner5", 6));

        Collections.sort(pVs);

        System.out.println("sorted in ascending order:");
        for (PassengerVehicle pv : pVs) {
            System.out.println(pv);
        }

        System.out.println("sorted in descending order:");
        for (int i = pVs.size() - 1; i >= 0; i--) {
            System.out.println(pVs.get(i));
        }
    }
}
