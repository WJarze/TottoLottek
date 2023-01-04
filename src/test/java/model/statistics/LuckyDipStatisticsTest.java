package model.statistics;

import data.lotto.AllHitsLuckyDipDraw;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class LuckyDipStatisticsTest {


    @DisplayName("should be returned how many lucky dip draws")
    @Test
    public void numberHitsOfDraws() {
        // When
        LuckyDipStatistics luckyDipStatistics = new LuckyDipStatistics ( );
        List<Integer> draw = List.of ( 1 , 2 , 3 );
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
}