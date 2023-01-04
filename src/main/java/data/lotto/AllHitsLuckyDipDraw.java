package data.lotto;


import java.util.List;

public class AllHitsLuckyDipDraw {
    private List<List<Integer>> allLuckyDipDraws;

    public AllHitsLuckyDipDraw(List<List<Integer>> allLuckyDipDraws) {
        this.allLuckyDipDraws = allLuckyDipDraws;
    }

    public AllHitsLuckyDipDraw() {

    }

    public List<List<Integer>> getAllLuckyDipDraws() {
        return allLuckyDipDraws;
    }

    public void setAllLuckyDipDraws(List<List<Integer>> allLuckyDipDraws) {
        this.allLuckyDipDraws = allLuckyDipDraws;
    }

    public int size() {
        return allLuckyDipDraws.size ( );
    }

    @Override
    public String toString() {
        return "AllHitsLuckyDipDraw{" +
                "allLuckyDipDraws=" + allLuckyDipDraws +
                '}';
    }
}
