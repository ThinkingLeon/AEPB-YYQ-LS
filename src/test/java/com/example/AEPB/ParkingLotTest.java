package com.example.AEPB;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {

    private List<Car> initCarList(){
        Car car1 = new Car("陕A88888");
        Car car2 = new Car("陕A66666");
        return List.of(car1,car2);
    }

    private List<Ticket> initTicketList(){
        Ticket ticket1 = new Ticket("陕A88888");
        Ticket ticket2 = new Ticket("陕A66666");
        return List.of(ticket1,ticket2);
    }

    @Test
    void should_get_one_ticket_when_parking_one_car_given_a_parking_lot_with_enough_space(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car("陕A88888");
        List<Ticket> ticketList = parkingLot.stopCar(List.of(car));
        assertEquals(1, ticketList.size());
        assertEquals("陕A88888", ticketList.get(0).getCarCode());
        assertEquals(9, parkingLot.getRemainPackingNum());
    }

    @Test
    void should_get_two_ticket_when_parking_two_car_given_a_parking_lot_not_with_enough_space(){
        ParkingLot parkingLot = new ParkingLot(10);
        List<Ticket> ticketList = parkingLot.stopCar(initCarList());
        assertEquals(2, ticketList.size());
        assertEquals("陕A88888", ticketList.get(0).getCarCode());
        assertEquals("陕A66666", ticketList.get(1).getCarCode());
        assertEquals(8, parkingLot.getRemainPackingNum());
        parkingLot.getStoppedCars();
    }

    @Test
    void should_get_current_ticket_when_parking_given_a_parking_lot_not_with_enough_space(){
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.setRemainPackingNum(1);
        List<Ticket> ticketList = parkingLot.stopCar(initCarList());
        assertEquals(1, ticketList.size());
        assertEquals("陕A88888", ticketList.get(0).getCarCode());
        assertEquals(0, parkingLot.getRemainPackingNum());
    }

    @Test
    void should_not_get_ticket_when_parking_given_a_parking_lot_with_no_space(){
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.setRemainPackingNum(0);
        List<Ticket> ticketList = parkingLot.stopCar(initCarList());
        assertEquals(0, ticketList.size());
    }

    @Test
    void should_get_my_car_when_get_car_given_a_current_ticket(){
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = new Ticket("陕A88888");
        parkingLot.stopCar(initCarList());
        List<Car> myCars = parkingLot.getCar(List.of(ticket));
        assertEquals(9, parkingLot.getRemainPackingNum());
        assertEquals(1,myCars.size());
        assertEquals("陕A88888", myCars.get(0).getCarCode());
    }

    @Test
    void should_get_my_cars_when_get_car_given_two_current_ticket(){
        ParkingLot parkingLot = new ParkingLot(10);
        Car car3 = new Car("陕A11111");
        parkingLot.stopCar(initCarList());
        parkingLot.stopCar(List.of(car3));
        List<Car> myCars = parkingLot.getCar(initTicketList());
        assertEquals(9, parkingLot.getRemainPackingNum());
        assertEquals(2,myCars.size());
        assertEquals("陕A88888", myCars.get(0).getCarCode());
        assertEquals("陕A66666", myCars.get(1).getCarCode());
    }

    @Test
    void should_not_get_my_cars_when_get_car_given_wrong_ticket(){
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = new Ticket("陕A22222");
        parkingLot.stopCar(initCarList());
        List<Car> myCars = parkingLot.getCar(List.of(ticket));
        assertEquals(8, parkingLot.getRemainPackingNum());
        assertEquals(0,myCars.size());
    }

    @Test
    void should_not_get_my_cars_when_get_car_given_no_ticket(){
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.stopCar(initCarList());
        List<Car> myCars = parkingLot.getCar(new ArrayList<>());
        assertEquals(8, parkingLot.getRemainPackingNum());
        assertEquals(0,myCars.size());
    }
    
}
