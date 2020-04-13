package transport_app.transport;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import transport_app.transport.objects.Flight;
import transport_app.transport.objects.Order;


/**
 * Hello world!
 *
 */
public class App 
{
	public static Scanner input;
	public static String ordersFileName = "coding-assigment-orders.json";
	public static ArrayList<Flight> flights;
	public static ArrayList<Order> orders;
    public static void main(String[] args)
    {
    	flights = new ArrayList<Flight>();
    	orders = new ArrayList<Order>();
    	//load orders from json file
    	getOrders();
        input = new Scanner(System.in);

        String strInput;
        do {
        	//print menu options
            System.out.println("Choose from the following menu options\n"
            		+ "Load Flights\n"
            		+ "List Flights\n"
            		+ "Schedule Orders\n");
            strInput = input.nextLine().toLowerCase();
        	//Switch case for user option
            switch (strInput){
	            case "load flights":
	                loadFlights();
	            	break;
	            case "list flights":
	            	listFlights();
	            	break;
	            case "schedule orders":
	            	scheduleOrders();
	            	break;
	            default:
	            	System.out.println("Invalid menu option");
            }
        } while(strInput!="exit");         
    }
    
    //allow the user to input flights
    public static void loadFlights(){
    	String userInput;
    	do {
    	System.out.println("Please input the flight id");
    	String flightId = input.nextLine();
    	
    	System.out.println("Please input the departure");
    	String departure = input.nextLine();

    	System.out.println("Please input the arrival");
    	String arrival = input.nextLine();
    	
    	System.out.println("Please input the day (must be an integer)");
    	int day = Integer.parseInt(input.nextLine());
    	
    	Flight flight = new Flight(flightId, departure, arrival, day);
    	
    	flights.add(flight);
    	
    	System.out.println("Would you like to enter another flight? yes/no");
    	userInput = input.nextLine();
    	
    	} while (userInput.equals("yes"));

    }
    
    //output the list of flights to the user
    public static void listFlights(){
    	for(Flight flight: flights){
        	System.out.println(String.format("Flight: %s, departure: %s, arrival: %s, day: %d", flight.getFlightId(), flight.getDeparture(), flight.getArrival(), flight.getDay()));
    	}
    }
    
    //Schedule a batch of orders to flights and output the orders to the user
    public static void scheduleOrders(){
    	for (Order order: orders){
    		//confirm that order has not been assigned and that flights exist
    		if(!flights.isEmpty() && order.getFlight()==null){
    			for (Flight flight: flights){
    				//if the arrival of the flight is the same as the order destination add the order to flight
    				if (flight.getArrival().equals(order.getDestination()) && !flight.isFull()){
    					//set the flight of the order
    					flight.addOrder(order);
    					order.setFlight(flight);
    					break;
    				}
    			}
    			if (order.getFlight()==null){
    				//order.getFlight().setFlightId("not scheduled");
    			}
    		}	
    	System.out.print(String.format("order: %s, flightNumber: %s", order.getOrderId(), (order.getFlight()==null)? "not scheduled": order.getFlight().getFlightId()));
    	if (order.getFlight()!=null){
        	System.out.print(String.format(", departure: %s, arrival: %s, day, %d", order.getFlight().getDeparture(), order.getFlight().getArrival(), order.getFlight().getDay()));
    	}
    	System.out.print("\n");
    	}
    	
    }
    
    //load orders from json file
    public static void getOrders(){
    	JSONParser parser = new JSONParser();
    	String dir = System.getProperty("user.dir");
    	try {
    		Reader reader = new FileReader(dir + "/" + ordersFileName);
    		JSONObject rawOrders = (JSONObject) parser.parse(reader);
    		Set<String> keys = rawOrders.keySet();
    		List<String> orderedKeys = new ArrayList<String>(keys);
    		Collections.sort(orderedKeys);
    		for (String key : orderedKeys){
    			JSONObject rawOrder = (JSONObject) rawOrders.get(key);
    			orders.add(new Order(key, rawOrder.get("destination").toString()));
    		}
    	}catch (IOException e){
    		e.printStackTrace();
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
