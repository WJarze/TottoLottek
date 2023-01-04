package io.output;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ShowTest {
    Show mock = Mockito.mock(Show.class);

    @Test
    void printMessageLuckyDip() {

    }

    @Test
    void printMessageChoices() {
    }

    @Test
    void printOptions() {
        mock.printOptionsLotteryLogic (  );
        Mockito.verify(mock, Mockito.times(1)).printOptionsLotteryLogic (  );
    }

    @Test
    void hits() {
    }

    @Test
    void noOption() {
        mock.noOption ();
        Mockito.verify(mock, Mockito.times(1)).noOption ();

    }
}