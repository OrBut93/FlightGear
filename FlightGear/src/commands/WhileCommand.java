package commands;

import expressions.ShuntingYard;
import interpeter.Interpeter;

import java.util.Arrays;

public class WhileCommand implements Command {
    @Override
    public void doCommand(String[] inputs)  {
        String condition = inputs[0].replace("[","").replace("]","");
        while (check(condition)){
            Interpeter.parser_helper(Arrays.asList(Arrays.copyOfRange(inputs,1,inputs.length)));
            }
        }

    private boolean check(String condition) {
        String[] data = condition.split(" ");
        String left = data[0];
        String right = data[2];
        String operator =data[1];
        return validate_condition(ShuntingYard.calc(left.split("")) , ShuntingYard.calc(right.split("")) ,operator);


    }

    private boolean validate_condition(Double left, Double right, String operator){
        switch (operator) {
            case ">=":
                if (left >= right) {
                    return true;
                }
                return false;
            case "<=":
                if (left <= right) {
                    return true;
                }
                return false;
            case "<":
                if (left < right) {
                    return true;
                }
                return false;

            case ">":
                if (left > right) {
                    return true;
                }
                return false;
            case "==":
                if(left.equals(right)) {
                    return true;
                }
                return false;
            case "!=":
                if (!(left.equals(right))) {
                    return true;
                }
                return false;

            default:

        }
        return false;
    }

}

