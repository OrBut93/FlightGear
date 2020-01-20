package commands;

import commands.Variable.Variable;
import expressions.ShuntingYard;
import interpeter.Interpeter;
import java.util.Arrays;

public class EqualsCommand implements Command{
    @Override
    public void doCommand(String[] inputs)  {
        if(!(Arrays.toString(inputs).contains("bind"))) {
            SymbolMemberToDouble(inputs);
        }
      String commandType =inputs[0];
      if (commandType.equals("var")) {
          if (Interpeter.symbolTable.containsKey(inputs[1])){
              System.out.println("variable" + commandType +"is already define");
          }
          else {
              if(4 > inputs.length ){
                  if(inputs.length == 2)
                  {
                      Interpeter.symbolTable.put(inputs[1],new Variable(0.0," "));
                  }
                  else {
                      System.out.println(" many arguments for command");
                  }
              }
              else {
                     if(inputs[3].equals("bind"))
                     {
                         Variable newVal = new Variable(0.0,Arrays.copyOfRange(inputs, 4, inputs.length)[0]);
                         Interpeter.symbolTable.put(inputs[1],newVal);
                     }
                     else{
//                         String shunting_input = String.join(" ", Arrays.copyOfRange(inputs, 3, inputs.length));
  //                       double value = ShuntingYard.calc(shunting_input);
                         String [] shunting_input = Arrays.copyOfRange(inputs, 3, inputs.length);
                         double value = ShuntingYard.calc(shunting_input);
                        Interpeter.symbolTable.put(inputs[1],new Variable(value," "));
                     }
              }
          }
       }
      else{
            if (Interpeter.symbolTable.containsKey(commandType)){
                if(Arrays.toString(inputs).contains("bind"))
                {

                    Interpeter.symbolTable.get(inputs[0]).setBindPath(Arrays.copyOfRange(inputs, 3, inputs.length)[0]);

                }
                else {
                    String[] shunting_input = Arrays.copyOfRange(inputs, 2, inputs.length);
                    double value = ShuntingYard.calc(shunting_input);
                    Interpeter.symbolTable.get(commandType).setValue(value);
                }
            }
            else System.out.println("variable" + commandType +"is not define");
        }
    }

    private void SymbolMemberToDouble(String []data)
    {
        int index = Arrays.asList(data).indexOf("=");
        if (index != -1) {
            for (int i = index + 1; i < data.length; i++) {
                if (Interpeter.symbolTable.containsKey(data[i])) {
                    data[i] = Interpeter.symbolTable.get(data[i]).getValue().toString();
                }
            }
        }
    }
}
