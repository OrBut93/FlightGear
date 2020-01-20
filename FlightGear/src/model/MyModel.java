package model;

import interpeter.Interpeter;
import javafx.beans.InvalidationListener;
import view.FlightGearClient;

import java.util.Observable;

public class MyModel implements Model   {
	FlightGearClient client;
	
	public MyModel() {
		this.client = new FlightGearClient("127.0.0.1",5402);
		this.client.runClient();

	}

	@Override
	public void setThrottle(double value) {
		System.out.println("throttle "+value);
		this.client.writeClient("set /controls/flight/throttle " + value);
	}

	@Override
	public void setRudder(double value) {
		this.client.writeClient("set /controls/flight/rudder " + value);
		System.out.println("rudder "+ value);
	}

	@Override
	public void setAileron(double value) {
		this.client.writeClient("set /controls/flight/aileron " + value);
		System.out.println("aileron "+value);
	}

	@Override
	public void setElevator(double value) {
		this.client.writeClient("set /controls/flight/elevator " + value);
		System.out.println("elevator "+value);

	}
	
	public void reset() {
		this.client.writeClient("set /controls/flight/rudder " + 0);
	}


}
