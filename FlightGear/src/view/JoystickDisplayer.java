package view;

import javafx.beans.property.DoubleProperty;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;


public class JoystickDisplayer extends AnchorPane {


    Circle outerCircle, innerCircle;

    DoubleProperty aileron, elevator;
    double orgSceneX, orgSceneY;
    public JoystickDisplayer(Circle inner, Circle outer) {
        outerCircle = outer;
        innerCircle = inner;
        aileron = new SimpleDoubleProperty();
        elevator = new SimpleDoubleProperty();
        orgSceneX = orgSceneY = 0;

    }

    public void innerReleased(MouseEvent e) {              	// When inner circle is released event handler
        innerCircle.setCenterY(0);
        innerCircle.setCenterX(                                                                                                                                                          0);

        elevator.set(0);
        aileron.set(0);
    }

    public void innerPressed(MouseEvent e) {				// When inner circle is pressed event handler
        orgSceneX = e.getSceneX();
        orgSceneY = e.getSceneY();
        this.innerCircle.toFront();
    }

    public void innerDragged(MouseEvent e) {				// When inner circle is dragged event handler

        double maxRange = outerCircle.getRadius() - this.innerCircle.getRadius();
        innerCircle.setCenterX(0);
        innerCircle.setCenterY(0);
        innerCircle.setCenterX(e.getX());
        innerCircle.setCenterY(e.getY());

        if (e.getX() >= 0)
            innerCircle.setCenterX(Double.min(maxRange, e.getX()));
         else
             innerCircle.setCenterX(Double.max(- maxRange, e.getX()));
         if (e.getY() >= 0)
             innerCircle.setCenterY(Double.min( maxRange, e.getY()));
         else
             innerCircle.setCenterY(Double.max(- maxRange, e.getY()));
         aileron.set(innerCircle.getCenterX()/maxRange);
         elevator.set(-innerCircle.getCenterY()/maxRange);

    }
}