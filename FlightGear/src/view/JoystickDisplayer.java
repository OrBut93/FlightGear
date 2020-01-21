package view;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
public class JoystickDisplayer extends AnchorPane {


    Circle outerCircle, innerCircle;

    DoubleProperty aileron, elevator;
    //double orgSceneX, orgSceneY;
    public JoystickDisplayer(Circle inner, Circle outer) {
        outerCircle = outer;
        innerCircle = inner;
        aileron = new SimpleDoubleProperty();
        elevator = new SimpleDoubleProperty();
        //orgSceneX = orgSceneY = 0;
    }

    public void innerReleased(MouseEvent e) {              	// When inner circle is released event handler
        innerCircle.setCenterY(0);
        innerCircle.setCenterX(                                                                                                                                                          0);

        elevator.set(0);
        aileron.set(0);
    }

    public void innerDragged(MouseEvent e) {				// When inner circle is dragged event handler
        double max_range = 90.0;
        if (e.getX() >= 0)
            innerCircle.setCenterX(Double.min(max_range, e.getX()));
         else
             innerCircle.setCenterX(Double.max(- max_range, e.getX()));
         if (e.getY() >= 0)
             innerCircle.setCenterY(Double.min( max_range, e.getY()));
         else
             innerCircle.setCenterY(Double.max(- max_range, e.getY()));
         aileron.set(innerCircle.getCenterX()/max_range);
         elevator.set(-innerCircle.getCenterY()/max_range);

    }
}