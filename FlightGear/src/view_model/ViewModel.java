package view_model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.Model;


public class ViewModel   {
	public DoubleProperty throttle,rudder,aileron,elevator;
	Model model;
	
	public ViewModel(Model model) {
		this.model=model;


		throttle=new SimpleDoubleProperty();
		rudder=new SimpleDoubleProperty();
		aileron=new SimpleDoubleProperty();
		elevator=new SimpleDoubleProperty();
		
		// when these values change, change the model values as well.		
		throttle.addListener((o,old,nw)->model.setThrottle(nw.doubleValue()));
		rudder.addListener((o,old,nw)->model.setRudder(nw.doubleValue()));
		aileron.addListener((o,old,nw)->model.setAileron(nw.doubleValue()));
		elevator.addListener((o,old,nw)->model.setElevator(nw.doubleValue()));
		// when the model changes values it sends FlightGear the associated commands
	}


}
