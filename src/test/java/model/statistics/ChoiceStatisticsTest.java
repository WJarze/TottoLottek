package model.statistics;

import data.lotto.AllHitsChoiceDraw;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChoiceStatisticsTest {


    @DisplayName("should be returned how many draws of the user")
    @Test
    public void numberHitsOfDraws() {
        // When
        ChoiceStatistics choiceStatistics = new ChoiceStatistics ( );
        List<Integer> draw = List.of ( 1 , 2 , 3 );
        List<List<Integer>> userDrawList = new ArrayList<> ( );

        //Then
        int loops = 100_000;
        int startLoopValue = loops;
        while (loops > 0) {
            loops--;
            int actualLoopValue = loops;
            userDrawList.add ( draw );
            AllHitsChoiceDraw allHitsChoiceDraws = new AllHitsChoiceDraw ( userDrawList );
            long actual = choiceStatistics.numberHitsOfDraws ( allHitsChoiceDraws ).count ( );

            // Given
            Assertions.assertEquals ( startLoopValue - actualLoopValue , actual );
        }
    }
}