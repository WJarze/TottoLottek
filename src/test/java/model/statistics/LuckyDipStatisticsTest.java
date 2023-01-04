package model.statistics;

import java.util.Collections;
import java.util.stream.Stream;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import data.lotto.AllHitsLuckyDipDraw;
import java.util.ArrayList;
import org.mockito.Mockito;

public class LuckyDipStatisticsTest {
    LuckyDipStatistics luckyDipStatistics = new LuckyDipStatistics ( );

    @DisplayName("should be returned how many lucky dip draws")
    @Test
    public void numberHitsOfDraws() {
        // When
        List<Integer> draw = List.of ( 1 , 2 , 3 , 0 , 0 , 0 );
        List<List<Integer>> luckyDipDrawList = new ArrayList<> ( );

        //Then
        int loops = 100_000;
        int startLoopValue = loops;
        while (loops > 0) {
            loops--;
            int actualLoopValue = loops;
            luckyDipDrawList.add ( draw );
            AllHitsLuckyDipDraw allHitsLuckyDipDraw = new AllHitsLuckyDipDraw ( luckyDipDrawList );
            long actual = luckyDipStatistics.numberHitsOfDraws ( allHitsLuckyDipDraw ).count ( );

            // Given
            Assertions.assertEquals ( startLoopValue - actualLoopValue , actual );
        }
    }

    @DisplayName("should return what number how many hits")
    @ParameterizedTest
    @MethodSource("isNumberLuckyDipHitsArgumentsProvider")
    public void testNumberOfNumberHitsInLuckyDipDraw(int expected , int number , List<Integer> draw) {

        List<List<Integer>> luckyDipDrawList = new ArrayList<> ( );
        //Then
        luckyDipDrawList.add ( draw );
        AllHitsLuckyDipDraw allHitsLuckyDipDraw = new AllHitsLuckyDipDraw ( luckyDipDrawList );
        long actual = luckyDipStatistics.numberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDraw , number );
        // Given
        Assertions.assertEquals ( expected , actual );

    }

    private static Stream<Arguments> isNumberLuckyDipHitsArgumentsProvider() {
        return Stream.of (
                Arguments.of ( 1 , 1 , List.of ( 1 , 2 , 3 , 0 , 0 , 0 ) ) ,
                Arguments.of ( 3 , 0 , List.of ( 1 , 2 , 3 , 0 , 0 , 0 ) ) ,
                Arguments.of ( 2 , 4 , List.of ( 1 , 4 , 13 , 30 , 4 , 49 ) ) ,
                Arguments.of ( 4 , 19 , List.of ( 19 , 4 , 13 , 19 , 4 , 49 , 19 , 19 ) ) ,
                Arguments.of ( 8 , 11 , List.of ( 11 , 11 , 11 , 11 , 11 , 11 , 11 , 11 ) )
        );
    }

    @DisplayName("should return what number how many hits")
    @ParameterizedTest
    @MethodSource("isNumberLuckyDipDrawHitsArgumentsProvider")
    public void testFilteredLuckyDipDrawsHitNumber(int expected , int number , List<Integer> draw) {
        // Initialize the object to be tested

        List<List<Integer>> luckyDipDrawList = new ArrayList<> ( );
        //Then
        luckyDipDrawList.add ( draw );
        AllHitsLuckyDipDraw allHitsLuckyDipDraw = new AllHitsLuckyDipDraw ( luckyDipDrawList );
        long actual = luckyDipStatistics.filteredLuckyDipDrawsHitNumber ( allHitsLuckyDipDraw , number );
        // Given
        Assertions.assertEquals ( expected , actual );
    }

    private static Stream<Arguments> isNumberLuckyDipDrawHitsArgumentsProvider() {
        return Stream.of (
                Arguments.of ( 1 , 0 , Collections.EMPTY_LIST ) ,
                Arguments.of ( 1 , 4 , List.of ( 1 , 1 , 1 , 1 ) ) ,
                Arguments.of ( 1 , 2 , List.of ( 1 , 2 ) ) ,
                Arguments.of ( 1 , 5 , List.of ( 1 , 2 , 3 , 4 , 5 ) ) ,
                Arguments.of ( 1 , 6 , List.of ( 1 , 4 , 13 , 30 , 4 , 49 ) )
        );
    }

    @Test
    public void testIfResultsIsLuckyDip() {
        // when
        LuckyDipStatistics luckyDipStatisticsMock = Mockito.mock ( LuckyDipStatistics.class );
        Mockito.when ( luckyDipStatisticsMock.getListLuckyDipLottery ( ) ).thenReturn ( List.of ( 1 , 2 , 3 ) );
        // then
        luckyDipStatistics.ifResultsIsLuckyDip ( );
    }
}