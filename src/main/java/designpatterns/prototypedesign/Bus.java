package designpatterns.prototypedesign;

import java.util.Objects;

public class Bus extends Vehicle{
    private int numberOfWheels;

    public Bus(String model, String brand, String color, int numberOfWheels){
        super(model, brand, color);
        this.numberOfWheels = numberOfWheels;
    }

    private Bus(Bus bus){
        super(bus);
        this.numberOfWheels = bus.numberOfWheels;
    }

    @Override
    public Vehicle clone() {
        return new Bus(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;
        if (!super.equals(o)) return false;
        Bus bus = (Bus) o;
        return numberOfWheels == bus.numberOfWheels;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfWheels);
    }

    @Override
    public String toString() {
        return "{" + super.toString() + ", Bus{" +
                "numberOfWheels=" + numberOfWheels +
                "}}";
    }
}
