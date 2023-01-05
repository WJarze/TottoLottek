package model.statistics.controller;


import data.lotto.AllHitsLuckyDipDrawFromDB;
import data.lotto.Lotto;
import databaseService.ConnectToDatabase;
import databaseService.ReaderDB;
import io.DataReader;
import io.output.Show;
import java.util.Scanner;
import model.statistics.LuckyDipStatistics;

import static io.output.Printer.printer;
import static io.output.Show.CLEAR_DATABASE;
import static io.output.Show.EXIT;
import static io.output.Show.HIT_PERCENTAGE;
import static io.output.Show.NUMBER_OF_ALL_LOTTERIES;
import static io.output.Show.NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW;
import static io.output.Show.PERCENTAGE_OF_NUMBER_HIT;


public class LuckyDipController {

    Scanner scanner;
    DataReader io;
    ConnectToDatabase database;
    Lotto lotto = new Lotto ( );
    ReaderDB readerDB = new ReaderDB ( );
    Show show = new Show ( );
    LuckyDipStatistics luckyDipStatistics = new LuckyDipStatistics ( readerDB );
    AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB = new AllHitsLuckyDipDrawFromDB ( );

    public LuckyDipController(Scanner scanner, DataReader io , ConnectToDatabase database) {
        this.scanner = scanner;
        this.io = io;
        this.database = database;
    }

    public void controlLoop() {
        int option;
        readerDB.listHitFromDB ( database );
        luckyDipStatistics.setCountAllLuckyDipLottery ( allHitsLuckyDipDrawsFromDB );
        do {
            show.printOptionsLuckyDipStatistics ( );
            option = io.getOptionLuckyDipController ( scanner );
            printer ( ).print ( "Connected to the database successfully" );
            switch (option) {
                case EXIT -> {
                    readerDB.getResults ( ).clear ( );
                    database.exit ( );
                    show.databaseExit ( );
                }
                case PERCENTAGE_OF_NUMBER_HIT -> {
                    show.showNumberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDrawsFromDB , lotto , luckyDipStatistics );
                    show.showPercentNumberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDrawsFromDB , lotto , luckyDipStatistics );
                }
                case NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW -> {
                    show.allNotNullLuckyDipDrawsCounter ( allHitsLuckyDipDrawsFromDB );
                    show.allLuckyDipDraws ( allHitsLuckyDipDrawsFromDB );
                    show.allLuckyDipDrawsHitNumber ( allHitsLuckyDipDrawsFromDB , luckyDipStatistics );
                }
                case NUMBER_OF_ALL_LOTTERIES -> show.allDraws ( readerDB );
                case HIT_PERCENTAGE -> {
                    luckyDipStatistics.setCountAllLuckyDipLottery ( allHitsLuckyDipDrawsFromDB );
                    show.showLuckyDipPercentHits ( allHitsLuckyDipDrawsFromDB , lotto , luckyDipStatistics );
                }
                case CLEAR_DATABASE -> database.clear ( );
                default -> printer ( ).print ( "No such option: " );
            }
        } while (option != EXIT);
    }
}
