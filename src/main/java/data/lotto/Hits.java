package data.lotto;

import java.util.ArrayList;
import java.util.List;

public class Hits {

    List<List<Integer>> hitsLuckyDipList = new ArrayList<> ( );
    List<List<Integer>> hitsChoiceList = new ArrayList<> ( );

    public void saveLuckyDipHits(Hit hit) {
        hitsLuckyDipList.add ( hit.getHitLuckyDip ( ) );
    }

    public void saveChoiceHits(Hit hit) {
        hitsChoiceList.add ( hit.getHitChoice ( ) );
    }

    @Override
    public String toString() {
        return "Hits{" +
                "hitsLuckyDipList=" + hitsLuckyDipList +
                ", hitsChoiceList=" + hitsChoiceList +
                '}';
    }
}
