package game;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NumbersLotteryTest {

    @Test
    void play() {
        NumbersLottery mock = Mockito.mock(NumbersLottery.class);
        mock.play ();
        Mockito.verify(mock, Mockito.times(1)).play();
    }
}