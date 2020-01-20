package test;

import interpeter.Interpeter;

import java.util.Arrays;

public class MyInterpreter {
	public static Double value;
	public static  int interpret(String[] lines)  {
		Interpeter myInterpeter = new Interpeter();
		myInterpeter.lexer(Arrays.asList(lines));
		myInterpeter.parser();

		return value.intValue();
	}
}
