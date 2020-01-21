package view_model;
import model.Model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ViewModel   {
	public DoubleProperty throttle,rudder,aileron,elevator;
	Model model;
	
	public ViewModel(Model model) {
		this.model=model;

		elevator=new SimpleDoubleProperty();
		aileron=new SimpleDoubleProperty();
		throttle=new SimpleDoubleProperty();
		rudder=new SimpleDoubleProperty();

		// when these values change, change the model values as well.		
		elevator.addListener((o,old,nw)->model.setElevator(nw.doubleValue()));
		aileron.addListener((o,old,nw)->model.setAileron(nw.doubleValue()));
		throttle.addListener((o,old,nw)->model.setThrottle(nw.doubleValue()));
		rudder.addListener((o,old,nw)->model.setRudder(nw.doubleValue()));
		// when the model changes values it sends FlightGear the associated commands
	}


}
