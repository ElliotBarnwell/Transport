package transport_app.transport.objects;

import java.util.ArrayList;

public class Flight {
	
	public String flightId;
	public String departure;
	public String arrival;
	public int day;
	public int capacity;
	public boolean full;
	public ArrayList<Order> orders; 
	
	public Flight(String flightId, String departure, String arrival, int day){
		this.flightId = flightId;
		this.departure = departure;
		this.arrival = arrival;
		this.day = day;
		this.capacity = 20;
		this.full = false;
	}
	
	public void addOrder(Order order){
		if (orders.size()<capacity){
			orders.add(order);
		}
		if (orders.size()==capacity) {
			this.full = true;
		}

	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}
}
