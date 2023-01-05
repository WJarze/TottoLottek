package model.statistics;


import data.lotto.AllHitsLuckyDipDrawFromDB;
import databaseService.ReaderDB;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.bson.Document;

public class LuckyDipStatistics {
    private List<Integer> listLuckyDipLottery;
    private ReaderDB readerDB;
    private final MathContext mc = new MathContext ( 4 , RoundingMode.HALF_UP );

    public LuckyDipStatistics(ReaderDB readerDB) {
        this.readerDB = readerDB;
    }

    public LuckyDipStatistics() {

    }

    public List<Integer> getListLuckyDipLottery() {
        return listLuckyDipLottery;
    }

    public void setCountAllLuckyDipLottery(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB) {
        ArrayList<List<Integer>> allLuckyDipDraws = new ArrayList<> ( );
        int countAllLottery = 0;
        while (countAllLottery < readerDB.getResults ( ).size ( )) {
            addLuckyDipDraws ( allHitsLuckyDipDrawsFromDB , allLuckyDipDraws , countAllLottery );
            countAllLottery++;
        }
    }

    private void addLuckyDipDraws(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB
            , ArrayList<List<Integer>> allLuckyDipDraws , int countAllLottery) {
        mapDocument ( countAllLottery );
        if ( ifResultsIsLuckyDip ( ) ) {
            allLuckyDipDraws.add ( listLuckyDipLottery );
            allHitsLuckyDipDrawsFromDB.setAllLuckyDipDraws ( allLuckyDipDraws );
        }
    }

    @SuppressWarnings(value = "unchecked")
    private void mapDocument(int countAllLottery) {
        Document doc = readerDB.getResults ( ).get ( countAllLottery );
        listLuckyDipLottery = (List<Integer>) doc.get ( "lucky dip" );
    }

    public boolean ifResultsIsLuckyDip() {
        return listLuckyDipLottery != null;
    }

    public IntStream numberHitsOfDraws(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB) {
        return allHitsLuckyDipDrawsFromDB.getAllLuckyDipDraws ( )
                .stream ( )
                .mapToInt ( List::size );
    }

    public long numberOfNumberHitsInLuckyDipDraw(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB , int index) {
        return getIntStream ( allHitsLuckyDipDrawsFromDB )
                .filter ( hit -> hit == index )
                .count ( );
    }

    private IntStream getIntStream(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB) {
        return allHitsLuckyDipDrawsFromDB.getAllLuckyDipDraws ( )
                .stream ( )
                .flatMapToInt ( list -> list.stream ( ).mapToInt ( hit -> hit ) );
    }

    public BigDecimal percentNumberOfNumberHitsInLuckyDipDraw(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB , int index) {

        double percentNumberOfNumberHits = (double) numberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDrawsFromDB , index ) * 100
                / allHitsLuckyDipDrawsFromDB.getAllLuckyDipDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentNumberOfNumberHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }

    public long filteredLuckyDipDrawsHitNumber(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB , int index) {
        return numberHitsOfDraws ( allHitsLuckyDipDrawsFromDB )
                .filter ( e -> e == index )
                .boxed ( )
                .count ( );
    }

    public BigDecimal percentHitsOfLuckyDip(AllHitsLuckyDipDrawFromDB allHitsLuckyDipDrawsFromDB , int index) {
        double percentHits = (double) filteredLuckyDipDrawsHitNumber ( allHitsLuckyDipDrawsFromDB , index ) * 100
                / allHitsLuckyDipDrawsFromDB.getAllLuckyDipDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }
}
