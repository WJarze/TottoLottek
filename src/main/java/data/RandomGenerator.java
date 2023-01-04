package data;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomGenerator implements Generator {
    protected Random rand = new Random ( );
    @Override
    public Set<Integer> generate(GeneratorConditions generatorConditions) {
        return IntStream.generate ( () -> (rand.nextInt ( generatorConditions.getOrigin ( ) , generatorConditions.getBounds ( ) )) )
                .distinct ( )
                .limit ( generatorConditions.getSize ( ) )
                .boxed ( )
                .collect ( Collectors.toSet ( ) );
    }

}