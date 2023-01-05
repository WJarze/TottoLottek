package model.statistics.controller;


import data.lotto.AllHitsChoiceDraw;
import data.lotto.Lotto;
import databaseService.ConnectToDatabase;
import databaseService.ReaderDB;
import io.DataReader;
import io.output.Show;
import java.util.Scanner;
import model.statistics.ChoiceStatistics;

import static io.output.Printer.printer;
import static io.output.Show.CLEAR_DATABASE;
import static io.output.Show.EXIT;
import static io.output.Show.HIT_PERCENTAGE;
import static io.output.Show.NUMBER_OF_ALL_LOTTERIES;
import static io.output.Show.NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW;
import static io.output.Show.PERCENTAGE_OF_NUMBER_HIT;


public class ChoiceController {
    Scanner scanner;
    DataReader io;
    ConnectToDatabase database;
    Lotto lotto = new Lotto ( );
    ReaderDB readerDB = new ReaderDB ( );
    Show show = new Show ( );
    ChoiceStatistics choiceStatistics = new ChoiceStatistics ( readerDB );
    AllHitsChoiceDraw allHitsChoiceDraws = new AllHitsChoiceDraw ( );

    public ChoiceController(Scanner scanner , DataReader io,ConnectToDatabase database ) {
        this.scanner = scanner;
        this.io = io;
        this.database = database;
    }

    public void controlLoop() {
        int option;
        readerDB.listHitFromDB ( database );
        choiceStatistics.setAllChoiceLottery ( allHitsChoiceDraws );
        do {
            show.printOptionsChoiceStatistics ( );
            option = io.getOptionChoiceController ( scanner );
            printer ( ).print ( "Connected to the database successfully" );
            switch (option) {
                case EXIT -> {
                    readerDB.getResults ( ).clear ( );
                    database.exit ( );
                    show.databaseExit ( );
                }
                case PERCENTAGE_OF_NUMBER_HIT -> {
                    show.showNumberOfNumberHitsInChoiceDraw ( allHitsChoiceDraws , lotto , choiceStatistics );
                    show.showPercentNumberOfNumberHitsInChoiceDraw ( allHitsChoiceDraws , lotto , choiceStatistics );
                }
                case NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW -> {
                    show.allNotNullChoiceDrawsCounter ( allHitsChoiceDraws );
                    show.allChoiceDraws ( allHitsChoiceDraws );
                    show.allChoiceDrawsHitNumber ( allHitsChoiceDraws , choiceStatistics );
                }
                case NUMBER_OF_ALL_LOTTERIES -> show.allDraws ( readerDB );
                case HIT_PERCENTAGE -> show.showChoicePercentHits ( allHitsChoiceDraws , lotto , choiceStatistics );
                case CLEAR_DATABASE -> database.clear ( );
                default -> printer ( ).print ( "No such option: " );
            }
        } while (option != EXIT);
    }
}
