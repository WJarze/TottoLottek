package model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LotteryLogicTest {

    @Test
    void controlLoop() {
        LotteryLogic mock = Mockito.mock(LotteryLogic.class);
        mock.controlLoop ();
        Mockito.verify(mock, Mockito.times(1)).controlLoop ();
    }
}