package io.output;

import data.lotto.AllHitsChoiceDrawFromDB;
import data.lotto.AllHitsLuckyDipDrawFromDB;
import data.lotto.Hits;
import data.lotto.Lotto;
import databaseService.ReaderDB;
import java.util.List;
import java.util.Set;
import model.statistics.ChoiceStatistics;
import model.statistics.LuckyDipStatistics;

import static io.output.Printer.printer;

public class Show {
    public static final int EXIT = 0;
    public static final int CHOICE_NUMBERS = 1;
    public static final int LUCKY_DIP = 2;
    public static final int HITS = 3;
    public static final int LUCKY_DIP_STATISTICS = 4;
    public static final int PERCENTAGE_OF_NUMBER_HIT = 1;
    public static final int NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW = 2;
    public static final int NUMBER_OF_ALL_LOTTERIES = 3;
    public static final int HIT_PERCENTAGE = 4;
    public static final int CLEAR_DATABASE = 5;

    public void printMessageLuckyDip(Set<Integer> luckyDipRNum , Set<Integer> randNums , List<Integer> numberHints , int count) {
        printer ( ).print ( Message.LUCKY_DIP_NUMBERS.getMsg ( ) + "\n"
                + luckyDipRNum + "\n"
                + Message.GENERATE_NUMBERS.getMsg ( ) + "\n"
                + randNums + "\n"
                + Message.WIN_NUMBERS.getMsg ( ) + "\n"
                + numberHints + "\n"
                + Message.HITS.getMsg ( ) + "\n"
                + count );
    }

    public void printMessageChoices(Set<Integer> choiceNums , Set<Integer> randNums , List<Integer> numberHints , int count) {
        printer ( ).print ( Message.CHOICE_NUMBERS.getMsg ( ) + "\n"
                + choiceNums + "\n"
                + (Message.GENERATE_NUMBERS.getMsg ( )) + "\n"
                + randNums + "\n"
                + Message.WIN_NUMBERS.getMsg ( ) + "\n"
                + numberHints + "\n"
                + Message.HITS.getMsg ( ) + "\n"
                + count );
    }

    public void printOptionsLotteryLogic() {
        printer ( ).print ( Message.OPTION.getMsg ( ) + "\n"
                + EXIT + Message.EXIT.getMsg ( ) + "\n"
                + CHOICE_NUMBERS + " - " + Message.CHOICE_NUMBERS.getMsg ( ) + "\n"
                + LUCKY_DIP + " - " + Message.LUCKY_DIP_NUMBERS.getMsg ( ) + "\n"
                + HITS + " - " + Message.HITS.getMsg ( ) + "\n"
                + LUCKY_DIP_STATISTICS + " - " + Message.HITS_FROM_DATABASE.getMsg ( ) );
    }

    public void showHits(Hits hits) {
        printer ( ).print ( String.valueOf ( hits ) );
    }

    public void noOption() {
        printer ( ).print ( Message.NO_OPTION.getMsg ( ) );
    }

    public void showLuckyDipPercentHits(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB
            , Lotto lotto , LuckyDipStatistics luckyDipStatistics) {
        for (int i = 0; i < lotto.getNumbers ( ) + 1; i++) {
            printer ( ).print ( "hit percentage " + i + " = "
                    + luckyDipStatistics.percentHitsOfLuckyDip ( allHitsLuckyDipDrawsFromDB , i ) + " % " );
        }
    }

    public void showPercentNumberOfNumberHitsInLuckyDipDraw(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB
            , Lotto lotto , LuckyDipStatistics luckyDipStatistics) {
        for (int i = lotto.getMinNumbers ( ); i <= lotto.getMaxNumbers ( ); i++) {
            printer ( ).print ( "percentage of hits for each number " + i + " = "
                    + luckyDipStatistics.percentNumberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDrawsFromDB , i ) + " % " );
        }
    }

    public void showNumberOfNumberHitsInLuckyDipDraw(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB
            , Lotto lotto
            , LuckyDipStatistics luckyDipStatistics) {
        for (int i = lotto.getMinNumbers ( ); i <= lotto.getMaxNumbers ( ); i++) {
            printer ( ).print ( "number of number hits " + i + " = "
                    + luckyDipStatistics.numberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDrawsFromDB , i ) );
        }
    }

    public void allNotNullLuckyDipDrawsCounter(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB) {
        printer ( ).print ( Message.ALL_LUCKY_DIP.getMsg ( )
                + allHitsLuckyDipDrawsFromDB.getAllLuckyDipDraws ( ).size ( ) );
    }

    public void databaseExit() {
        printer ( ).print ( Message.EXIT_DATABASE.getMsg ( ) );
    }

    public void printOptionsLuckyDipStatistics() {
        printer ( ).print ( Message.OPTION.getMsg ( ) + "\n"
                + EXIT + Message.EXIT.getMsg ( ) + "\n"
                + PERCENTAGE_OF_NUMBER_HIT + " - " + Message.PERCENTAGE_OF_NUMBER_HIT.getMsg ( ) + "\n"
                + NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW + " - " + Message.NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW.getMsg ( ) + "\n"
                + NUMBER_OF_ALL_LOTTERIES + " - " + Message.NUMBER_OF_ALL_LOTTERIES.getMsg ( ) + "\n"
                + HIT_PERCENTAGE + " - " + Message.HIT_PERCENTAGE.getMsg ( ) + "\n"
                + CLEAR_DATABASE + " - " + Message.CLEAR_DATABASE.getMsg ( ) );
    }

    public void allDraws(ReaderDB readerDB) {
        printer ( ).print ( Message.NUMBER_OF_ALL_LOTTERIES.getMsg ( ) + readerDB.size ( ) );
    }

    public void allLuckyDipDraws(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB) {
        allHitsLuckyDipDrawsFromDB.getAllLuckyDipDraws ( )
                .forEach ( e -> printer ( ).print ( String.valueOf ( e ) ) );
    }

    public void allLuckyDipDrawsHitNumber(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB , LuckyDipStatistics luckyDipStatistics) {
        luckyDipStatistics.numberHitsOfDraws ( allHitsLuckyDipDrawsFromDB )
                .boxed ( )
                .toList ( )
                .forEach ( e -> printer ( ).print ( String.valueOf ( e ) ) );
    }

    public void showChoicePercentHits(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB
            , Lotto lotto , ChoiceStatistics choiceStatistics) {
        for (int i = 0; i < lotto.getNumbers ( ) + 1; i++) {
            printer ( ).print ( "hit percentage " + i + " = "
                    + choiceStatistics.percentHitsOfChoice ( allHitsChoiceDrawsFromDB , i ) + " % " );
        }
    }

    public void allChoiceDrawsHitNumber(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB , ChoiceStatistics choiceStatistics) {
        choiceStatistics.numberHitsOfDraws ( allHitsChoiceDrawsFromDB )
                .boxed ( )
                .toList ( )
                .forEach ( e -> printer ( ).print ( String.valueOf ( e ) ) );
    }

    public void allChoiceDraws(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB) {
        allHitsChoiceDrawsFromDB.getAllChoiceDraws ( )
                .forEach ( e -> printer ( ).print ( String.valueOf ( e ) ) );
    }

    public void allNotNullChoiceDrawsCounter(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB) {
        printer ( ).print ( Message.ALL_CHOICE.getMsg ( )
                + allHitsChoiceDrawsFromDB.getAllChoiceDraws ( ).size ( ) );
    }

    public void showPercentNumberOfNumberHitsInChoiceDraw(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB
            , Lotto lotto , ChoiceStatistics choiceStatistics) {
        for (int i = lotto.getMinNumbers ( ); i <= lotto.getMaxNumbers ( ); i++) {
            printer ( ).print ( "percentage of hits for each number " + i + " = "
                    + choiceStatistics.percentNumberOfNumberHitsInChoiceDraw ( allHitsChoiceDrawsFromDB , i ) + " % " );
        }
    }

    public void showNumberOfNumberHitsInChoiceDraw(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB
            , Lotto lotto , ChoiceStatistics choiceStatistics) {
        for (int i = lotto.getMinNumbers ( ); i <= lotto.getMaxNumbers ( ); i++) {
            printer ( ).print ( "number of number hits " + i + " = "
                    + choiceStatistics.numberOfNumberHitsInChoiceDraw ( allHitsChoiceDrawsFromDB , i ) );
        }
    }

    public void printOptionsChoiceStatistics() {
        printer ( ).print ( Message.OPTION.getMsg ( ) + "\n"
                + EXIT + Message.EXIT.getMsg ( ) + "\n"
                + PERCENTAGE_OF_NUMBER_HIT + " - " + Message.PERCENTAGE_OF_NUMBER_HIT.getMsg ( ) + "\n"
                + NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW + " - " + Message.NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW.getMsg ( ) + "\n"
                + NUMBER_OF_ALL_LOTTERIES + " - " + Message.NUMBER_OF_ALL_LOTTERIES.getMsg ( ) + "\n"
                + HIT_PERCENTAGE + " - " + Message.HIT_PERCENTAGE.getMsg ( ) + "\n"
                + CLEAR_DATABASE + " - " + Message.CLEAR_DATABASE.getMsg ( ) );
    }

    public void printOptionsStatisticsController() {
        printer ( ).print ( Message.OPTION.getMsg ( ) + "\n"
                + EXIT + Message.EXIT.getMsg ( ) + "\n"
                + CHOICE_NUMBERS + " - " + Message.CHOICE_NUMBERS.getMsg ( ) + "\n"
                + LUCKY_DIP + " - " + Message.LUCKY_DIP_NUMBERS.getMsg ( ) + "\n" );
    }
}
