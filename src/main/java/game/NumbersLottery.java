package game;


import model.LotteryLogic;

public class NumbersLottery implements Game {
    LotteryLogic lotteryLogic = new LotteryLogic ( );

    @Override
    public void play() {
        lotteryLogic.controlLoop ( );
    }
}
