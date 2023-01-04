package model;


import data.lotto.Hit;
import data.lotto.Hits;
import databaseService.ConnectToDatabase;
import io.DataReader;
import io.output.Show;
import java.util.Scanner;
import model.statistics.controller.Controller;

import static io.output.Show.CHOICE_NUMBERS;
import static io.output.Show.EXIT;
import static io.output.Show.HITS;
import static io.output.Show.LUCKY_DIP;
import static io.output.Show.LUCKY_DIP_STATISTICS;


public class LotteryLogic {
    private final DataReader io = new DataReader ( );
    private final LottoCheckWin lottoCheckWin = new LottoCheckWin ( );
    private final Scanner sc = new Scanner ( System.in );
    private final Hit hit = new Hit ( );
    private final Hits hits = new Hits ( );
    private final Show show = new Show ( );
    String nameDatabase = "myDb";
    String nameCollection = "hits";
    ConnectToDatabase database = new ConnectToDatabase ( nameDatabase , nameCollection );
    Controller statisticsController = new Controller ( );

    public void controlLoop() {
        int option;
        do {
            show.printOptionsLotteryLogic ( );
            option = io.getOption ( sc );
            switch (option) {
                case CHOICE_NUMBERS -> {
                    lottoCheckWin.checkWinChoice ( );
                    lottoCheckWin.showChoiceResult ( show );
                    lottoCheckWin.setChoiceHit ( hit );
                    hits.saveChoiceHints ( hit );
                    database.addOne ( hit.hitChoiceDTO ( ) );
                }
                case LUCKY_DIP -> {
                    lottoCheckWin.checkWinLuckyDip ( );
                    lottoCheckWin.showLuckyDipResult ( show );
                    lottoCheckWin.setLuckyDipHit ( hit );
                    hits.saveLuckyDipHints ( hit );
                    database.addOne ( hit.hitLuckyDipDTO ( ) );
                }
                case HITS -> show.hits ( hits );
                case LUCKY_DIP_STATISTICS -> statisticsController.controlLoop ( );
                case EXIT -> io.exit ( sc );
                default -> show.noOption ( );
            }
        } while (option != EXIT);
    }
}
