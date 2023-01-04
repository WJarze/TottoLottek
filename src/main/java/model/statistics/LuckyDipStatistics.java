package model.statistics;


import data.lotto.AllHitsLuckyDipDraw;
import databaseService.ReaderDB;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.bson.Document;

public class LuckyDipStatistics {
    int countAllLuckyDipLottery;
    List<Integer> listLuckyDipLottery;
    ReaderDB readerDB;

    public LuckyDipStatistics(ReaderDB readerDB) {
        this.readerDB = readerDB;
    }

    public LuckyDipStatistics() {

    }

    public void setCountAllLuckyDipLottery(AllHitsLuckyDipDraw allHitsLuckyDipDraws) {
        ArrayList<List<Integer>> allLuckyDipDraws = new ArrayList<> ( );
        int countAllLottery = 0;
        while (countAllLottery < readerDB.getResults ( ).size ( )) {
            addLuckyDipDraws ( allHitsLuckyDipDraws , allLuckyDipDraws , countAllLottery );
            countAllLottery++;
        }
    }

    private void addLuckyDipDraws(AllHitsLuckyDipDraw allHitsLuckyDipDraws
            , ArrayList<List<Integer>> allLuckyDipDraws , int countAllLottery) {
        mapDocument ( countAllLottery );
        if ( ifResultsIsLuckyDip ( ) ) {
            allLuckyDipDraws.add ( listLuckyDipLottery );
            allHitsLuckyDipDraws.setAllLuckyDipDraws ( allLuckyDipDraws );
            countAllLuckyDipLottery++;
        }
    }

    @SuppressWarnings(value = "unchecked")
    private void mapDocument(int countAllLottery) {
        Document doc = readerDB.getResults ( ).get ( countAllLottery );
        listLuckyDipLottery = (List<Integer>) doc.get ( "lucky dip" );
    }

    private boolean ifResultsIsLuckyDip() {
        return listLuckyDipLottery != null;
    }

    public IntStream numberHitsOfDraws(AllHitsLuckyDipDraw allHitsLuckyDipDraws) {
        return allHitsLuckyDipDraws.getAllLuckyDipDraws ( )
                .stream ( )
                .mapToInt ( List::size );
    }

    public long numberOfNumberHitsInLuckyDipDraw(AllHitsLuckyDipDraw allHitsLuckyDipDraws , int index) {
        return getIntStream ( allHitsLuckyDipDraws )
                .filter ( hit -> hit == index )
                .count ( );
    }

    private  IntStream getIntStream(AllHitsLuckyDipDraw allHitsLuckyDipDraws) {
        return allHitsLuckyDipDraws.getAllLuckyDipDraws ( )
                .stream ( )
                .flatMapToInt ( list -> list.stream ( ).mapToInt ( hit -> hit ) );
    }

    public double percentNumberOfNumberHitsInLuckyDipDraw(AllHitsLuckyDipDraw allHitsLuckyDipDraws , int index) {
        return (double) numberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDraws , index )
                / allHitsLuckyDipDraws.getAllLuckyDipDraws ( ).size ( );
    }

    public long filteredLuckyDipDrawsHitNumber(AllHitsLuckyDipDraw allHitsLuckyDipDraws , int index) {
        return numberHitsOfDraws ( allHitsLuckyDipDraws )
                .filter ( e -> e == index )
                .boxed ( )
                .count ( );
    }

    public double percentHitsOf(AllHitsLuckyDipDraw allHitsLuckyDipDraws , int index) {
        return (double) filteredLuckyDipDrawsHitNumber ( allHitsLuckyDipDraws , index )
                / allHitsLuckyDipDraws.getAllLuckyDipDraws ( ).size ( );
    }
}
