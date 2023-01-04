package data.lotto;

enum Conditions {
    NUMBERS ( 6 ), MIN_NUMBERS ( 1 ), MAX_NUMBERS ( 49 );// 6 numbers choice or generated on range 1 - 49

    private final int value;

    Conditions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
