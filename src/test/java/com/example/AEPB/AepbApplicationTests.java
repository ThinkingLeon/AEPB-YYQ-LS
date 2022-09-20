package com.example.AEPB;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelloTest {

	@Test
	void should_no_null() {

		Hello hello = new Hello();
		assertNotNull(hello);
	}

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
		PackingLot packingLot = new PackingLot(10);
		Car car = new Car("陕A88888");
		List<Ticket> ticketList = packingLot.stopCar(List.of(car));
		assertEquals(1, ticketList.size());
		assertEquals("陕A88888", ticketList.get(0).getCarCode());
		assertEquals(9, packingLot.getRemainPackingNum());
	}

	@Test
	void should_get_two_ticket_when_parking_two_car_given_a_parking_lot_not_with_enough_space(){
		PackingLot packingLot = new PackingLot(10);
		List<Ticket> ticketList = packingLot.stopCar(initCarList());
		assertEquals(2, ticketList.size());
		assertEquals("陕A88888", ticketList.get(0).getCarCode());
		assertEquals("陕A66666", ticketList.get(1).getCarCode());
		assertEquals(8,packingLot.getRemainPackingNum());
		packingLot.getStoppedCars();
	}

	@Test
	void should_get_current_ticket_when_parking_given_a_parking_lot_not_with_enough_space(){
		PackingLot packingLot = new PackingLot(10);
		packingLot.setRemainPackingNum(1);
		List<Ticket> ticketList = packingLot.stopCar(initCarList());
		assertEquals(1, ticketList.size());
		assertEquals("陕A88888", ticketList.get(0).getCarCode());
		assertEquals(0, packingLot.getRemainPackingNum());
	}

	@Test
	void should_not_get_ticket_when_parking_given_a_parking_lot_with_no_space(){
		PackingLot packingLot = new PackingLot(10);
		packingLot.setRemainPackingNum(0);
		List<Ticket> ticketList = packingLot.stopCar(initCarList());
		assertEquals(0, ticketList.size());
	}

	@Test
	void should_get_my_car_when_get_car_given_a_current_ticket(){
		PackingLot packingLot = new PackingLot(10);
		Ticket ticket = new Ticket("陕A88888");
		packingLot.stopCar(initCarList());
		List<Car> myCars = packingLot.getCar(List.of(ticket));
		assertEquals(9, packingLot.getRemainPackingNum());
		assertEquals(1,myCars.size());
		assertEquals("陕A88888", myCars.get(0).getCarCode());
	}

	@Test
	void should_get_my_cars_when_get_car_given_two_current_ticket(){
		PackingLot packingLot = new PackingLot(10);
		Car car3 = new Car("陕A11111");
		packingLot.stopCar(initCarList());
		packingLot.stopCar(List.of(car3));
		List<Car> myCars = packingLot.getCar(initTicketList());
		assertEquals(9, packingLot.getRemainPackingNum());
		assertEquals(2,myCars.size());
		assertEquals("陕A88888", myCars.get(0).getCarCode());
		assertEquals("陕A66666", myCars.get(1).getCarCode());
	}

	@Test
	void should_not_get_my_cars_when_get_car_given_wrong_ticket(){
		PackingLot packingLot = new PackingLot(10);
		Ticket ticket = new Ticket("陕A22222");
		packingLot.stopCar(initCarList());
		List<Car> myCars = packingLot.getCar(List.of(ticket));
		assertEquals(8, packingLot.getRemainPackingNum());
		assertEquals(0,myCars.size());
	}

	@Test
	void should_not_get_my_cars_when_get_car_given_no_ticket(){
		PackingLot packingLot = new PackingLot(10);
		packingLot.stopCar(initCarList());
		List<Car> myCars = packingLot.getCar(new ArrayList<>());
		assertEquals(8, packingLot.getRemainPackingNum());
		assertEquals(0,myCars.size());
	}

}
