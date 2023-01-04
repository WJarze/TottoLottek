package io;
import io.output.Message;
import java.util.InputMismatchException;
import java.util.Scanner;

import static io.output.Printer.printer;


public class DataReader {

    public int getOption(Scanner io) {
        return getNumber ( io );
    }
    public int getOptionLuckyDipController(Scanner io){
        return getNumber ( io );
    }
    public int getOptionChoiceController(Scanner io) {
        return getNumber ( io );
    }
    public int getOptionStatisticsController(Scanner io) {
        return getNumber ( io );
    }
    private static int getNumber(Scanner io) {
        int number;
        while (true) {
            try {
                number = io.nextInt ( );
                break;
            } catch (InputMismatchException e) {
                printer().print ( Message.CORRECT_NUMBERS.getMsg () );
                io.next ( );
            }
        }
        return number;
    }

    public void exit(Scanner io) {
        io.close ( );
    }


}
