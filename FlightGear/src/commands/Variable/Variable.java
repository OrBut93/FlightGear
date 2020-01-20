package commands.Variable;

import commands.ConnectCommand;
import flightgear.FlightGearClient;
import interpeter.Interpeter;

import java.util.Observable;
import java.util.Observer;

public class Variable extends Observable implements Observer {

    public double value;
    public String bindPath;

    public Variable( double value,String bindPath) {
        this.bindPath = bindPath;
        if(! (bindPath.equals(" "))) {
            if (Interpeter.symbolTable.get(bindPath) != null) {
                this.setValue(Interpeter.symbolTable.get(bindPath).getValue());
                Interpeter.symbolTable.get(bindPath).addObserver(this);
                this.addObserver(Interpeter.symbolTable.get(bindPath));
            }
        }
        else {
            this.value = value;
        }
    }

    public String getBindPath() {
        return bindPath;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(double value) {
        if ( value != this.value) {
            this.value = value;
            if(!(this.bindPath.equals(" "))){
                Interpeter.client.writeClient("set "+bindPath+" "+value);
            }
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        this.setValue(((Variable)observable).getValue());
    }

    public void setBindPath(String path) {
        this.bindPath = path;
        Interpeter.symbolTable.get(path).addObserver(this);
        this.addObserver(Interpeter.symbolTable.get(path));

    }
}