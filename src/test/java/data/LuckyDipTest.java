package data;


import data.lotto.Lotto;
import data.lotto.LuckyDip;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import model.LottoCheckWin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class LuckyDipTest extends LuckyDip {
    Lotto lotto = new Lotto ( );
    GeneratorConditions generatorConditions = new GeneratorConditions ( lotto.getMinNumbers ( )
            , lotto.getMaxNumbers ( )
            , lotto.getNumbers ( ) );

    @DisplayName("Should bounds from 1 to 49 ")
    @Test
    void should_return_true_if_number_is_beetween_1_to_49() {
        int loops = 100_000;

        while (loops > 0) {
            Set<Integer> currentRandomInt = generate ( generatorConditions );
            assertTrue ( currentRandomInt.stream ( ).allMatch ( n -> n <= 49 ) );
            assertTrue ( currentRandomInt.stream ( ).allMatch ( n -> n >= 1 ) );
            loops--;
        }
    }

    @DisplayName("Should be six number")
    @Test
    void should_be_six_numbers() {
        int loops = 100_000;
        while (loops > 0) {
            Set<Integer> currentRandomInt = generate ( generatorConditions );
            assertTrue ( currentRandomInt.size ( ) == 6 );
            loops--;
        }
    }

    @DisplayName("Should countGuessedNumbers() return how many lottery numbers you guessed")
    @ParameterizedTest
    @MethodSource("countGuessedNumbersArgumentsProvider")
    void countGuessedNumbers(int expected , Set<Integer> numbersGuessed , Set<Integer> lotteryNumbers) {
        LottoCheckWin lottoCheckWin = new LottoCheckWin ( );
        assertEquals ( expected , lottoCheckWin.countGuessedLuckyDipNumbers ( numbersGuessed , lotteryNumbers ) );
    }

    private static Stream<Arguments> countGuessedNumbersArgumentsProvider() {
        return Stream.of (
                Arguments.of ( 0 , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) , Set.of ( 11 , 22 , 33 , 44 , 55 , 66 ) ) ,
                Arguments.of ( 1 , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) , Set.of ( 6 , 7 , 8 ) ) ,
                Arguments.of ( 2 , Set.of ( 11 , 12 , 13 , 14 , 15 ) , Set.of ( 15 , 13 ) ) ,
                Arguments.of ( 6 , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) )
        );
    }

    @DisplayName("Should luckyDipNumbersHint() return list numbers you guessed")
    @ParameterizedTest
    @MethodSource("luckyDipNumbersHintArgumentsProvider")
    void luckyDipNumbersHint(List<Integer> expected , Set<Integer> luckyDip , Set<Integer> randNums) {
        LottoCheckWin lottoCheckWin = new LottoCheckWin ( );
        assertEquals ( expected , lottoCheckWin.luckyDipNumbersHint ( luckyDip , randNums ) );
    }

    private static Stream<Arguments> luckyDipNumbersHintArgumentsProvider() {
        return Stream.of (
                Arguments.of ( List.of ( 1 , 2 , 3 ) , Set.of ( 1 , 2 , 3 , 11 , 12 , 13 ) , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) ),
                Arguments.of ( List.of ( 1 , 2  ) , Set.of ( 1 , 2 , 11 , 22 , 33 , 44 ) , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) )
        );
    }
}
