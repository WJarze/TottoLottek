package data;


import data.lotto.Lotto;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomGeneratorTest extends RandomGenerator {
    Lotto lotto = new Lotto ( );
    GeneratorConditions generatorConditions = new GeneratorConditions ( lotto.getMinNumbers ( )
            , lotto.getMaxNumbers ( )
            , lotto.getNumbers ( ) );

    @DisplayName("Should bounds from 1 to 49")
    @Test
    void should_from_one_to_forty_nine() {
        int loops = 100_000;

        while (loops > 0) {
            Set<Integer> randomNumbers = generate ( generatorConditions );
            for (int i = 0; i < randomNumbers.size ( ); i++) {
                assertTrue ( randomNumbers.stream ( ).allMatch ( n -> n <= 49 ) );
                assertTrue ( randomNumbers.stream ( ).allMatch ( n -> n >= 1 ) );
                loops--;
            }
        }
    }

    @DisplayName("Should be six numbers")
    @Test
    void should_be_six_numbers() {
        int loops = 100_0000;
        while (loops > 0) {
            Set<Integer> currentRandomInt = generate ( generatorConditions );
            assertTrue ( currentRandomInt.size ( ) == 6 );
            loops--;
        }
    }

}
