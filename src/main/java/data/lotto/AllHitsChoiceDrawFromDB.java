package data.lotto;

import java.util.List;

public class AllHitsChoiceDrawFromDB {
    private List<List<Integer>> allChoiceDraws;

    public AllHitsChoiceDrawFromDB(List<List<Integer>> allChoiceDraws) {
        this.allChoiceDraws = allChoiceDraws;
    }

    public AllHitsChoiceDrawFromDB() {

    }

    public List<List<Integer>> getAllChoiceDraws() {
        return allChoiceDraws;
    }

    public void setAllChoiceDraws(List<List<Integer>> allChoiceDraws) {
        this.allChoiceDraws = allChoiceDraws;
    }

    public int size() {
        return allChoiceDraws.size ( );
    }

    @Override
    public String toString() {
        return "AllHitsChoiceDrawFromDB{" +
                "allChoiceDraws=" + allChoiceDraws +
                '}';
    }
}
