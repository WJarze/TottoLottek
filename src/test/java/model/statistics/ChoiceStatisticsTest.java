package model.statistics;

import data.lotto.AllHitsChoiceDrawFromDB;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

class ChoiceStatisticsTest {
    ChoiceStatistics choiceStatistics = new ChoiceStatistics ( );


    @DisplayName("should be returned how many draws of the user")
    @Test
     void numberHitsOfDraws() {
        // When
        List<Integer> draw = List.of ( 1 , 2 , 3 );
        List<List<Integer>> userDrawList = new ArrayList<> ( );

        //Then
        int loops = 100_000;
        int startLoopValue = loops;
        while (loops > 0) {
            loops--;
            int actualLoopValue = loops;
            userDrawList.add ( draw );
            AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB = new AllHitsChoiceDrawFromDB ( userDrawList );
            long actual = choiceStatistics.numberHitsOfDraws ( allHitsChoiceDrawsFromDB ).count ( );

            // Given
            Assertions.assertEquals ( startLoopValue - actualLoopValue , actual );
        }
    }

    @DisplayName("should return what number how many hits")
    @ParameterizedTest
    @MethodSource("isNumberChoiceHitsArgumentsProvider")
     void testNumberOfNumberHitsInChoiceDraw(int expected , int number , List<Integer> draw) {
        List<List<Integer>> choiceDrawList = new ArrayList<> ( );
        //Then
        choiceDrawList.add ( draw );
        AllHitsChoiceDrawFromDB allHitsChoiceDrawFromDB = new AllHitsChoiceDrawFromDB ( choiceDrawList );
        long actual = choiceStatistics.numberOfNumberHitsInChoiceDraw ( allHitsChoiceDrawFromDB , number );
        // Given
        Assertions.assertEquals ( expected , actual );
    }

    private static Stream<Arguments> isNumberChoiceHitsArgumentsProvider() {
        return Stream.of (
                Arguments.of ( 1 , 1 , List.of ( 1 , 2 , 3 , 0 , 0 , 0 ) ) ,
                Arguments.of ( 3 , 0 , List.of ( 1 , 2 , 3 , 0 , 0 , 0 ) ) ,
                Arguments.of ( 2 , 4 , List.of ( 1 , 4 , 13 , 30 , 4 , 49 ) ) ,
                Arguments.of ( 4 , 19 , List.of ( 19 , 4 , 13 , 19 , 4 , 49 , 19 , 19 ) ) ,
                Arguments.of ( 8 , 11 , List.of ( 11 , 11 , 11 , 11 , 11 , 11 , 11 , 11 ) )
        );
    }

    @Test
     void testIfResultsIsChoice() {
        // When
        ChoiceStatistics choiceStatisticsMock = Mockito.mock ( ChoiceStatistics.class );
        // Then
        Mockito.when ( choiceStatisticsMock.getListChoiceLottery ( ) ).thenReturn ( List.of ( 1 , 2 , 3 ) );
        // Given
        Assertions.assertFalse ( choiceStatistics.ifResultsIsChoice ( ) );
    }
}
