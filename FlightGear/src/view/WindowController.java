package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import view_model.ViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable  {
	
	ViewModel vm;
	
	@FXML
	Slider throttle;
	@FXML
	Slider rudder;
	@FXML
	JoystickDisplayer joystick;
	@FXML
	Circle innerCircle;
	@FXML
	Circle outerCircle;

	public DoubleProperty aileron, elevator;


	public void setViewModel(ViewModel vm) {
		this.vm=vm;
		vm.rudder.bind(rudder.valueProperty());
		vm.throttle.bind(throttle.valueProperty());
		vm.aileron.bindBidirectional(this.aileron);
		vm.elevator.bindBidirectional(this.elevator);

	}


	public void innerPressed(MouseEvent e) {
			joystick.innerPressed(e);
	}

	public void innerDragged(MouseEvent e) {
			joystick.innerDragged(e);


	}

	public void innerReleased(MouseEvent e) {
		joystick.innerReleased(e);

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		joystick = new JoystickDisplayer(innerCircle,outerCircle);
		aileron = new SimpleDoubleProperty();
		elevator = new SimpleDoubleProperty();
		elevator.bind(joystick.elevator);
		aileron.bind(joystick.aileron);
	}
	
	public void resRudder(){
		System.out.println("hi");
	}
}
