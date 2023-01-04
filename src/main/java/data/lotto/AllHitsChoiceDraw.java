package data.lotto;

import java.util.List;

public class AllHitsChoiceDraw {
    private List<List<Integer>> allChoiceDraws;

    public AllHitsChoiceDraw(List<List<Integer>> allChoiceDraws) {
        this.allChoiceDraws = allChoiceDraws;
    }


    public AllHitsChoiceDraw() {

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
        return "AllHitsChoiceDraw{" +
                "allChoiceDraws=" + allChoiceDraws +
                '}';
    }
}
