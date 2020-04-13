package transport_app.transport.objects;

public class Order {
	
	public String orderId;
	public String destination;
	public Flight flight;

	public Order(String orderId, String destination){
		this.orderId = orderId;
		this.destination = destination;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
