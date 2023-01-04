package data.lotto;

public class Lotto {
    private final int numbers;
    private final int minNumbers;
    private final int maxNumbers;

    public Lotto() {
        this.numbers = Conditions.NUMBERS.getValue ( );
        this.minNumbers = Conditions.MIN_NUMBERS.getValue ( );
        this.maxNumbers = Conditions.MAX_NUMBERS.getValue ( );
    }

    public int getNumbers() {
        return numbers;
    }

    public int getMinNumbers() {
        return minNumbers;
    }

    public int getMaxNumbers() {
        return maxNumbers;
    }
}