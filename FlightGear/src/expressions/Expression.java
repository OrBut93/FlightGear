package expressions;

import java.util.ArrayList;

public interface Expression {

    public double calculate(ArrayList<String> args, int index);
}
