package com.example.AEPB;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackingLot {
    private int totalNum;
    private int remainPackingNum;

    private List<Car> stoppedCars = new ArrayList<>();

    public PackingLot(int totalNum) {
        this.totalNum = totalNum;
        this.remainPackingNum = totalNum;
    }

    public void setRemainPackingNum(int remainPackingNum) {
        this.remainPackingNum = remainPackingNum;
    }

    public int getRemainPackingNum() {
        return remainPackingNum;
    }

    public List<Car> getStoppedCars() {
        return stoppedCars;
    }

    public List<Ticket> stopCar(List<Car> cars) {
        if (remainPackingNum >= cars.size()){
            remainPackingNum -= cars.size();
            stoppedCars.addAll(cars);
            return cars.stream().map(car -> new Ticket(car.getCarCode()))
                    .collect(Collectors.toList());
        }
        cars = cars.subList(0,remainPackingNum);
        remainPackingNum = 0;
        stoppedCars.addAll(cars);
        return cars.stream().map(car -> new Ticket(car.getCarCode()))
                .collect(Collectors.toList());
    }

    public List<Car> getCar(List<Ticket> tickets) {
        List<Car> myCars = new ArrayList<>();
        List<String> stoppedCarCodes = stoppedCars.stream().map(stoppedCar -> stoppedCar.getCarCode()).collect(Collectors.toList());
        for (Ticket ticket : tickets){
            String carCode = ticket.getCarCode();
            if (stoppedCarCodes.contains(carCode)){
                Car car = new Car(carCode);
                myCars.add(car);
                stoppedCars.remove(car);
                remainPackingNum++;
            }
        }
        return myCars;
    }
}
