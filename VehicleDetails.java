package DAY_2;

class Vehicle {
    String brand;
    String model;
    int mileage;

    void setVehicleInfo(String brand, String model, int mileage) {
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
    }

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Mileage: " + mileage + " km/l");
    }
}

class Car extends Vehicle {
    int numberOfSeats;

    void setCarInfo(String brand, String model, int mileage, int seats) {
        setVehicleInfo(brand, model, mileage);
        this.numberOfSeats = seats;
    }

    void displayCarInfo() {
        displayInfo();
        System.out.println("Number of Seats: " + numberOfSeats);
    }
}

class Bus extends Vehicle {
    int capacity;

    void setBusInfo(String brand, String model, int mileage, int capacity) {
        setVehicleInfo(brand, model, mileage);
        this.capacity = capacity;
    }

    void displayBusInfo() {
        displayInfo();
        System.out.println("Passenger Capacity: " + capacity);
    }
}

public class VehicleDetails {
    public static void main(String[] args) {
        Car car = new Car();
        car.setCarInfo("Toyota", "Camry", 15, 5);
        System.out.println("Car Information:");
        car.displayCarInfo();

        System.out.println();

        Bus bus = new Bus();
        bus.setBusInfo("Volvo", "9400", 7, 50);
        System.out.println("Bus Information:");
        bus.displayBusInfo();
    }
}
// Output:
// Car Information:
// Brand: Toyota
// Model: Camry
// Mileage: 15 km/l
// Number of Seats: 5
//Bus Information:
//Brand: Volvo
//Model: 9400
//Mileage: 7 km/l
//Passenger Capacity: 50