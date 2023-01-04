package io.output;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Printer {
private static final Logger log = Logger.getLogger ( Printer.class.getName () );
    public static Printer printer(){
        return new Printer();
    }
    public void print(String line){
        log.log ( Level.INFO,line );
    }
}
