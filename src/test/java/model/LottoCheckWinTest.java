package model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LottoCheckWinTest {

    @Test
    void checkWinChoice() {
        LottoCheckWin mock = Mockito.mock(LottoCheckWin.class);
        mock.checkWinChoice();
        Mockito.verify(mock, Mockito.times(1)).checkWinChoice();
    }
    @Test
    void checkWinLuckyDip() {
        LottoCheckWin mock = Mockito.mock(LottoCheckWin.class);
        mock.checkWinLuckyDip();
        Mockito.verify(mock, Mockito.times(1)).checkWinLuckyDip();
    }
}