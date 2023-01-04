package io.output;

public enum Message {
    WIN_NUMBERS ( "Win numbers !!!" ),
    LUCKY_DIP_NUMBERS ( "lucky dip numbers" ),
    CHOICE_NUMBERS ( "choice numbers" ),
    GENERATE_NUMBERS ( "generated numbers" ),
    HITS ( "Hits" ),
    OPTION ( "Options: " ),
    NO_OPTION ( "No options: " ),
    EXIT ( " - exit" ),
    RANGE_NUMBERS ( "enter the correct (not the same, range 1 - 49) number: " ),
    CORRECT_NUMBERS ( "enter correct number not a letter or a character" ),
    ENTER_NUMBER ( "enter a number: " ),
    HITS_FROM_DATABASE ( "hits from database" ),
    ALL_LUCKY_DIP ( "All lucky dip draws " ),
    HIT_PERCENTAGE ( "hit percentage" ),
    PERCENTAGE_OF_NUMBER_HIT ( "percentage of number hit" ),
    NUMBER_OF_ALL_LOTTERIES ( "number of all lotteries " ),
    NUMBER_OF_ALL_LOTTERIES_OF_HIT_IN_DRAW ( "number of all lotteries hit in draw" ),
    EXIT_DATABASE ( "exit database" ),
    CLEAR_DATABASE ( "clear database" ),
    ALL_CHOICE ( "All choice draws " );

    private final String msg;

    Message(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
