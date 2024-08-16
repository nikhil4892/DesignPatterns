package designpatterns.prototypedesign;

import java.util.Objects;

public class Car extends Vehicle{
    private int topSpeed;

    public Car(String model, String brand, String color, int topSpeed){
        super(model, brand, color);
        this.topSpeed = topSpeed;
    }

    private Car(Car car){
        super(car);
        this.topSpeed = car.topSpeed;;
    }

    @Override
    public Vehicle clone() {
        return new Car(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        if (!super.equals(o)) return false;
        Car car = (Car) o;
        return topSpeed == car.topSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), topSpeed);
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ", Car{" +
                "topSpeed=" + topSpeed +
                "}}";
    }
}
