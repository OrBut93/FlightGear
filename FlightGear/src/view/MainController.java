package view;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
public class MainController extends AnchorPane {
    Circle joystick_dot;
    DoubleProperty aileron, elevator;
    public MainController(Circle joystick_dot) {
        this.joystick_dot = joystick_dot;
        aileron = new SimpleDoubleProperty();
        elevator = new SimpleDoubleProperty();
    }

    public void innerReleased(MouseEvent e) {              	// When inner circle is released event handler
        joystick_dot.setCenterY(0);
        joystick_dot.setCenterX(0);
        elevator.set(0);
        aileron.set(0);
    }

    public void innerDragged(MouseEvent e) {				// When inner circle is dragged event handler
        double max_range = 100.0;
        if (e.getX() >= 0)
            joystick_dot.setCenterX(Double.min(max_range, e.getX()));
         else
             joystick_dot.setCenterX(Double.max(- max_range, e.getX()));
         if (e.getY() >= 0)
             joystick_dot.setCenterY(Double.min( max_range, e.getY()));
         else
             joystick_dot.setCenterY(Double.max(- max_range, e.getY()));
         aileron.set(joystick_dot.getCenterX()/max_range);
         elevator.set(-joystick_dot.getCenterY()/max_range);

    }
}