package data.lotto;


import java.util.List;

public class AllHitsLuckyDipDrawFromDB {
    private List<List<Integer>> allLuckyDipDraws;

    public AllHitsLuckyDipDrawFromDB(List<List<Integer>> allLuckyDipDraws) {
        this.allLuckyDipDraws = allLuckyDipDraws;
    }

    public AllHitsLuckyDipDrawFromDB() {

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
        return "AllHitsLuckyDipDrawFromDB{" +
                "allLuckyDipDraws=" + allLuckyDipDraws +
                '}';
    }
}
