package view;

import java.net.URL;
import javafx.fxml.FXML;
import view_model.ViewModel;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		joystick = new JoystickDisplayer(innerCircle,outerCircle);
		aileron = new SimpleDoubleProperty();
		elevator = new SimpleDoubleProperty();
		elevator.bind(joystick.elevator);
		aileron.bind(joystick.aileron);
	}

	public void setViewModel(ViewModel vm) {
		this.vm=vm;
		vm.rudder.bind(rudder.valueProperty());
		vm.throttle.bind(throttle.valueProperty());
		vm.aileron.bindBidirectional(this.aileron);
		vm.elevator.bindBidirectional(this.elevator);
	}

	public void innerDragged(MouseEvent e) { joystick.innerDragged(e); }

	public void innerReleased(MouseEvent e) { joystick.innerReleased(e); }

	public void rudder_reset(){
		rudder.setValue(0.0);
	}

	
}
