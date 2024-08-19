package designpatterns.creational.prototypedesign;

import java.util.Objects;

public abstract class Vehicle {
    private String model;
    private String brand;
    private String color;

    public Vehicle(String model, String brand, String color) {
        this.model = model;
        this.brand = brand;
        this.color = color;
    }

    protected Vehicle(Vehicle vehicle) {
        this.brand = vehicle.brand;
        this.model = vehicle.model;
        this.color = vehicle.color;
    }


    public abstract Vehicle clone();

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(model, vehicle.model) && Objects.equals(brand, vehicle.brand) && Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, brand, color);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
