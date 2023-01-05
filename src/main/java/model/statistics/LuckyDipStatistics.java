package model.statistics;


import data.lotto.AllHitsLuckyDipDraw;
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

    private IntStream getIntStream(AllHitsLuckyDipDraw allHitsLuckyDipDraws) {
        return allHitsLuckyDipDraws.getAllLuckyDipDraws ( )
                .stream ( )
                .flatMapToInt ( list -> list.stream ( ).mapToInt ( hit -> hit ) );
    }

    public BigDecimal percentNumberOfNumberHitsInLuckyDipDraw(AllHitsLuckyDipDraw allHitsLuckyDipDraws , int index) {

        double percentNumberOfNumberHits = (double) numberOfNumberHitsInLuckyDipDraw ( allHitsLuckyDipDraws , index ) * 100
                / allHitsLuckyDipDraws.getAllLuckyDipDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentNumberOfNumberHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }

    public long filteredLuckyDipDrawsHitNumber(AllHitsLuckyDipDraw allHitsLuckyDipDraws , int index) {
        return numberHitsOfDraws ( allHitsLuckyDipDraws )
                .filter ( e -> e == index )
                .boxed ( )
                .count ( );
    }

    public BigDecimal percentHitsOfLuckyDip(AllHitsLuckyDipDraw allHitsLuckyDipDraws , int index) {
        double percentHits = (double) filteredLuckyDipDrawsHitNumber ( allHitsLuckyDipDraws , index ) * 100
                / allHitsLuckyDipDraws.getAllLuckyDipDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }
}
