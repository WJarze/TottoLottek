package data;


import data.lotto.Choice;
import data.lotto.Lotto;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import model.LottoCheckWin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChoiceTest {
    Choice choiceNumsMock = Mockito.mock ( Choice.class );
    Scanner scanner = new Scanner ( System.in );
    @DisplayName("Should be number ")
    @Test
    void getNumbersFromUser() {
        Choice choice = new Choice ( );
        String data = "10";
        System.setIn ( new ByteArrayInputStream ( data.getBytes ( ) ) );
        assertEquals ( "10" , Integer.toString ( choice.getNumbersFromUser ( new Scanner ( System.in ) ) ) );
    }
    @DisplayName("Should bounds from 1 to 49 ")
    @Test
    void should_from_one_to_forty_nine() {
        Set<Integer> numbersEntered = new TreeSet<> ( );
        numbersEntered.add ( 1 );
        numbersEntered.add ( 21 );
        numbersEntered.add ( 33 );
        numbersEntered.add ( 44 );
        numbersEntered.add ( 49 );
        Mockito.when ( choiceNumsMock.choiceNums ( scanner) ).thenReturn ( numbersEntered );

        int loops = 100_000;

        while (loops > 0) {
            Set<Integer> currentRandomInt = choiceNumsMock.choiceNums (scanner );
            assertTrue ( currentRandomInt.stream ( ).allMatch ( n -> n <= 49 ) );
            assertTrue ( currentRandomInt.stream ( ).allMatch ( n -> n >= 1 ) );
            loops--;
        }
    }
    @DisplayName("Should countGuessedNumbers() return how many lottery numbers you guessed")
    @ParameterizedTest
    @MethodSource("countGuessedNumbersArgumentsProvider")
    void should_return_countGuessedNumbers(int expected, Set<Integer> numbersGuessed, Set<Integer> lotteryNumbers){
        LottoCheckWin lottoCheckWin = new LottoCheckWin();
        assertEquals(expected, lottoCheckWin.countGuessedChoiceNumbers(numbersGuessed,lotteryNumbers));
    }
    private static Stream<Arguments> countGuessedNumbersArgumentsProvider(){
        return Stream.of(
                Arguments.of(0, Set.of(1, 2, 3, 4, 5, 6), Set.of(11, 22, 33, 44, 55, 66)),
                Arguments.of(1, Set.of(1, 2, 3, 4, 5, 6), Set.of(6, 7, 8)),
                Arguments.of(2, Set.of(11, 12, 13, 14, 15), Set.of(15, 13)),
                Arguments.of(6, Set.of(1, 2, 3, 4, 5, 6), Set.of(1, 2, 3, 4, 5, 6))
        );
    }

    @DisplayName("should isNumberDuplicate() return correct answer")
    @ParameterizedTest
    @MethodSource("isNumberDuplicateArgumentsProvider")
    void should_isNumberDuplicate(boolean expected , int number , Set<Integer> numbersEntered) {
        Choice choice = new Choice(numbersEntered);
        assertEquals ( expected , choice.isNumberDuplicate ( number ) );
    }

    private static Stream<Arguments> isNumberDuplicateArgumentsProvider(){
        return Stream.of(
                Arguments.of(true, 1, Set.of(1,2,3)),
                Arguments.of(false, 0, Set.of(1,2,3)),
                Arguments.of(false, 1, Collections.emptySet()),
                Arguments.of(false, Integer.MAX_VALUE, Set.of(1, 2, 3)),
                Arguments.of(true, Integer.MIN_VALUE, Set.of(Integer.MIN_VALUE, Integer.MAX_VALUE, -19))
        );
    }
    @DisplayName("Should isNumberOutOfBounds() work")
    @ParameterizedTest
    @MethodSource("isNumberOutOfBoundsArgumentsProvider")
    void isNumberOutOfBounds(boolean expected, int number){
        Choice choice = new Choice (  );
        assertEquals(expected, choice.isNumberOutOfBounds(number));
    }
    private static Stream<Arguments> isNumberOutOfBoundsArgumentsProvider(){
        Lotto lotto = new Lotto();
        return Stream.of(
                Arguments.of(true, lotto.getMinNumbers () - 1),
                Arguments.of(false, lotto.getMinNumbers ()),
                Arguments.of(false, lotto.getMaxNumbers ()),
                Arguments.of(true, lotto.getMaxNumbers () + 1),
                Arguments.of(true, Integer.MIN_VALUE),
                Arguments.of(true, Integer.MAX_VALUE)
        );
    }
    @DisplayName("Should luckyDipNumbersHint() return list numbers you guessed")
    @ParameterizedTest
    @MethodSource("choiceNumbersHintArgumentsProvider")
    void choiceNumbersHint(List<Integer> expected , Set<Integer> choiceNums , Set<Integer> randNums) {
        LottoCheckWin lottoCheckWin = new LottoCheckWin ( );
        assertEquals ( expected , lottoCheckWin.choiceNumbersHint ( choiceNums , randNums ) );
    }

    private static Stream<Arguments> choiceNumbersHintArgumentsProvider() {
        return Stream.of (
                Arguments.of ( List.of ( 1 , 2 , 3 ) , Set.of ( 1 , 2 , 3 , 11 , 12 , 13 ) , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) ),
                Arguments.of ( List.of ( 1 , 2  ) , Set.of ( 1 , 2 , 11 , 22 , 33 , 44 ) , Set.of ( 1 , 2 , 3 , 4 , 5 , 6 ) )
        );
    }
}