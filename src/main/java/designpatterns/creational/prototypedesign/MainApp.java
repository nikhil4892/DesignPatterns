package designpatterns.creational.prototypedesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MainApp {

    public static List<Vehicle> getListOfVehicles(Vehicle... vehicles){
        List<Vehicle> listOfVehicles = new ArrayList<>();
        listOfVehicles.addAll(Arrays.asList(vehicles));
        return listOfVehicles;
    }

    public static void main(String[] args) {
        Car car1 = new Car("superb", "skoda", "blue", 210);
        Car car2 = new Car("astor", "morris garden", "black", 190);

        Bus bus1=  new Bus("viking", "ashok leyland", "red", 6);
        Bus bus2 = new Bus("9600", "volvo", "white", 6);

        //list of vehicles
        List<Vehicle> listOfVehicles = getListOfVehicles(car1, car2, bus1, bus2);
        //new list to have copies of vehicles
        List<Vehicle> copyList = new ArrayList<>();
        //copy all vehicles
        listOfVehicles.stream().forEach(vehicle -> copyList.add(vehicle.clone()));

        IntStream.range(0, listOfVehicles.size()-1).forEach(i -> {
            if(listOfVehicles.get(i).equals(copyList.get(i)))
                System.out.println("Value at " + i + "th index are same in both lists.");

            else
                System.out.println("Value at " + i + "th index are not same in both lists.");

            System.out.println(listOfVehicles.get(i).toString());
            System.out.println(copyList.get(i).toString());
        });
    }
}
