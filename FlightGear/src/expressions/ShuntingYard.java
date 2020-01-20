package expressions;



import interpeter.Interpeter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ShuntingYard {

    public static double calc(String[] expression) {
        LinkedList<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
//        String[] parser_exp = expression.split("(?<=[-+*/()])|(?=[-+*/()])");
//        List<String> list_exp = new ArrayList<String>();
//        for(String str : parser_exp){
//            if (!(str.equals(" "))){
//                list_exp.add(str);
//            }
//        }
      // String [] token = list_exp.toArray(new String[0]);
        String [] token = expression;
        for (int i = 0; i < token.length; i++) {
            switch (token[i]) {
                case "+":
                case "-": {
                    while (!stack.isEmpty() && !stack.peek().equals("(")) {
                        queue.addLast(stack.pop());
                    }
                    stack.push(token[i]);
                    break;
                }
                case "(":
                case "*":
                case "/": {
                    stack.push(token[i]);
                    break;
                }
                case ")": {
                    while (!stack.isEmpty() && !(stack.peek().equals("("))) {
                        queue.addLast(stack.pop());
                    }
                    stack.pop();
                    break;
                }
                default: {
                    String val=token[i];
                    if(Interpeter.symbolTable.containsKey(val)) {
                       val=String.valueOf(Interpeter.symbolTable.get(val).getValue());
                    }
                    queue.addLast(val);
                    break;
                }
            }
        }
        while (!stack.isEmpty()) {
            queue.addLast(stack.pop());
        }
        return ExTree(queue).calculate(null, 0);

    }

    public static Expression ExTree(LinkedList<String> queue) {
        Expression temp;
        if(queue.isEmpty()) return new Number(0);
        String e = queue.pollLast();
        switch (e) {
            case "+": {
                Expression right = ExTree(queue);
                Expression left = ExTree(queue);
                temp = new Plus(left, right);
                break;
            }
            case "-": {
                Expression right = ExTree(queue);
                Expression left = ExTree(queue);
                temp = new Minus(left, right);
                break;
            }
            case "*": {
                Expression right = ExTree(queue);
                Expression left = ExTree(queue);
                temp = new Mul(left, right);
                break;
            }
            case "/": {
                Expression right = ExTree(queue);
                Expression left = ExTree(queue);
                temp = new Div(left, right);
                break;
            }
            default: {
                double num = Double.parseDouble(e);
                temp = new Number((float)num);
                break;
            }
        }
        return temp;
    }

}
