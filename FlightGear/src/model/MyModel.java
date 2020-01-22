package model;
import flightgear.FlightGearClient;

public class MyModel implements Model   {
	FlightGearClient client;
	
	public MyModel() {
		this.client = new FlightGearClient("127.0.0.1",5402);
		this.client.run_client();
	}
	@Override
	public void setThrottle(double value) { this.client.send_command("set /controls/engines/current-engine/throttle " + value); }
	@Override
	public void setRudder(double value) { this.client.send_command("set /controls/flight/rudder " + value); }
	@Override
	public void setAileron(double value) { this.client.send_command("set /controls/flight/aileron " + value); }
	@Override
	public void setElevator(double value) { this.client.send_command("set /controls/flight/elevator " + value); }
}
