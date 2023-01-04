package model.statistics.controller;


import databaseService.ConnectToDatabase;
import io.DataReader;
import io.output.Show;
import java.util.Scanner;

import static io.output.Printer.printer;
import static io.output.Show.CHOICE_NUMBERS;
import static io.output.Show.EXIT;
import static io.output.Show.LUCKY_DIP;


public class Controller {
    Scanner scanner = new Scanner ( System.in );
    DataReader io = new DataReader ( );
    String nameDatabase = "myDb";
    String nameCollection = "hits";
    ConnectToDatabase database = new ConnectToDatabase ( nameDatabase , nameCollection );
    Show show = new Show ( );
    LuckyDipController luckyDipController = new LuckyDipController ( scanner , io , database);
    ChoiceController choiceController = new ChoiceController ( scanner , io , database  );

    public void controlLoop() {
        int option;
        do {
            show.printOptionsStatisticsController ( );
            option = io.getOptionStatisticsController ( scanner );
            printer ( ).print ( "Connected to the database successfully" );
            switch (option) {
                case EXIT -> show.databaseExit ( );
                case CHOICE_NUMBERS -> choiceController.controlLoop ( );
                case LUCKY_DIP -> luckyDipController.controlLoop ( );
                default -> printer ( ).print ( "No such option: " );
            }
        } while (option != EXIT);
    }
}
