package interpeter;

import commands.*;
import commands.Variable.Variable;
import flightgear.FlightGearClient;
import sun.awt.windows.WPrinterJob;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Interpeter {

    public static List<String> lexerOutput;
    public static Map<String, Command> commands;
    public static Map<String, Variable> symbolTable ;
    public static FlightGearClient client;
    private static String[] Configure = new String[]{
            "simX",
            "simY",
            "simZ",
            "instrumentation/airspeed-indicator/indicated-speed-kt",
            "instrumentation/altimeter/indicated-altitude-ft",
            "instrumentation/altimeter/pressure-alt-ft",
            "instrumentation/attitude-indicator/indicated-pitch-deg",
            "instrumentation/attitude-indicator/indicated-roll-deg",
            "instrumentation/attitude-indicator/internal-pitch-deg",
            "instrumentation/attitude-indicator/internal-roll-deg",
            "instrumentation/encoder/indicated-altitude-ft",
            "instrumentation/encoder/pressure-alt-ft",
            "instrumentation/gps/indicated-altitude-ft",
            "instrumentation/gps/indicated-ground-speed-kt",
            "instrumentation/gps/indicated-vertical-speed",
            "instrumentation/heading-indicator/indicated-heading-deg",
            "instrumentation/magnetic-compass/indicated-heading-deg",
            "instrumentation/slip-skid-ball/indicated-slip-skid",
            "instrumentation/turn-indicator/indicated-turn-rate",
            "instrumentation/vertical-speed-indicator/indicated-speed-fpm",
            "controls/flight/aileron",
            "controls/flight/elevator",
            "controls/flight/rudder",
            "controls/flight/flaps",
            "controls/engines/current-engine/throttle",
            "engines/engine/rpm"
    };

    public Interpeter() {
        client = null ;
        lexerOutput = new ArrayList<String>();
        commands = new ConcurrentHashMap<>();
        symbolTable = new ConcurrentHashMap<String, Variable>();
        Init_symbol_table();
        commands.put("openDataServer", new OpenDataServerCommand());
        commands.put("connect", new ConnectCommand());
        commands.put("while", new WhileCommand());
        commands.put("equals", new EqualsCommand());
        commands.put("disconnect", new DisconnectCommand());
        commands.put("print", new PrintCommand());
        commands.put("sleep", new SleepCommand());
    }

    private void Init_symbol_table() {
        for (String conf : Configure)
        {
            symbolTable.put(conf, new Variable(0.0," "));
        }
    }


    public void lexer (List<String> inputFromClient){
        List<String> output = new ArrayList<String>();
        for (String s : inputFromClient) {
            s = s.replaceAll("\\+", " + ");
            s = s.replaceAll("\\-", " - ");
            s = s.replaceAll("\\*", " * ");
            s = s.replaceAll("\\/", " / ");
            s = s.replaceAll("\\)", " ) ");
            s = s.replaceAll("\\(", " ( ");
            s = s.replaceAll("\"" , "");
            s = s.replaceAll("="," = ");
            s = s.trim().replaceAll(" +", " ");
            output.add(s);
        }

        lexerOutput = output;
    }

    public void parser()  {
        parser_helper(lexerOutput);

    }
    public static void parser_helper(List<String> parser_input){
            String[] arr = parser_input.toArray(new String[0]);

            int lineCounter = 0;
            String line ;
            while (lineCounter < parser_input.size())
            {
                line = arr[lineCounter];
                Command c ;
                String[] params = line.split(" ");
                String[] inputs;
                List<String> whileCommands = new ArrayList<String>();

                if (commands.containsKey(params[0])) {
                    c = commands.get(params[0]);
                    if (params[0].equals("while")) {
                        String condition = String.join(" ",Arrays.copyOfRange(params, 1, params.length -1));
                        whileCommands.add(condition);
                        lineCounter++;
                        String while_line = arr[lineCounter];
                        while(!(while_line.equals("}")))
                        {
                            whileCommands.add(while_line);
                            lineCounter++;
                            while_line = arr[lineCounter];
                        }
                        inputs = whileCommands.toArray(new String[0]);
                    }
                    else {
                        inputs = Arrays.copyOfRange(params, 1, params.length);
                    }

                }
                else {
                        c = commands.get("equals");
                        inputs = Arrays.copyOfRange(params, 0, params.length);


                }
                lineCounter++;
                c.doCommand(inputs);
            }

        }
    }

