package model;


import data.GeneratorConditions;
import data.RandomGenerator;
import data.lotto.Choice;
import data.lotto.Hit;
import data.lotto.Lotto;
import data.lotto.LuckyDip;
import io.output.Show;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoCheckWin extends Lotto {
    private final Scanner scanner = new Scanner ( System.in );
    private final RandomGenerator randomNumbers = new RandomGenerator ( );
    private final LuckyDip luckyDip = new LuckyDip ( );
    private final GeneratorConditions generatorConditions =
            new GeneratorConditions ( getMinNumbers ( ) , getMaxNumbers ( ) , getNumbers ( ) );
    private List<Integer> numberHits;
    private Set<Integer> luckyDipRNum;
    private Set<Integer> randNums;
    private Set<Integer> choiceNums;

    public void checkWinChoice() {
        Set<Integer> numbersEntered = new TreeSet<> ( );
        Choice choiceNumbers = new Choice ( numbersEntered );
        choiceNums = choiceNumbers.choiceNums ( scanner );
        randNums = randomNumbers.generate ( generatorConditions );
        numberHits = choiceNumbersHint ( choiceNums , randNums );
    }

    void showChoiceResult(Show show) {
        show.printMessageChoices ( choiceNums
                , randNums
                , numberHits
                , countGuessedChoiceNumbers ( choiceNums , randNums ) );
    }

    void setChoiceHit(Hit hit) {
        if ( numberHits != null ) {
            hit.setHitChoice ( numberHits );
        }
    }

    public int countGuessedChoiceNumbers(Set<Integer> choiceNums , Set<Integer> randNums) {
        return choiceNumbersHint ( choiceNums , randNums ).size ( );
    }

    public List<Integer> choiceNumbersHint(Set<Integer> choiceNums , Set<Integer> randNums) {
        return choiceNums.stream ( ).filter ( randNums::contains ).collect ( Collectors.toList ( ) );
    }

    public void checkWinLuckyDip() {
        luckyDipRNum = luckyDip.generate ( generatorConditions );
        randNums = randomNumbers.generate ( generatorConditions );
        numberHits = luckyDipNumbersHint ( luckyDipRNum , randNums );
    }

    void showLuckyDipResult(Show show) {
        show.printMessageLuckyDip ( luckyDipRNum
                , randNums
                , numberHits
                , countGuessedLuckyDipNumbers ( luckyDipRNum , randNums ) );
    }

    void setLuckyDipHit(Hit hit) {
        if ( numberHits != null ) {
            hit.setHitLuckyDip ( numberHits );
        }
    }

    public List<Integer> luckyDipNumbersHint(Set<Integer> luckyDip , Set<Integer> randNums) {
        return luckyDip.stream ( ).filter ( randNums::contains ).collect ( Collectors.toList ( ) );
    }

    public int countGuessedLuckyDipNumbers(Set<Integer> luckyDip , Set<Integer> randNums) {
        return luckyDipNumbersHint ( luckyDip , randNums ).size ( );
    }
}




