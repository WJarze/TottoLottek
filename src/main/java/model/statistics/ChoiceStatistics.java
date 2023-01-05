package model.statistics;

import data.lotto.AllHitsChoiceDraw;
import databaseService.ReaderDB;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.bson.Document;


public class ChoiceStatistics {
    private List<Integer> listChoiceLottery;
    private ReaderDB readerDB;
    private final MathContext mc = new MathContext ( 4 , RoundingMode.HALF_UP );

    public List<Integer> getListChoiceLottery() {
        return listChoiceLottery;
    }

    public ChoiceStatistics() {
    }

    public ChoiceStatistics(ReaderDB readerDB) {
        this.readerDB = readerDB;
    }

    public void setAllChoiceLottery(AllHitsChoiceDraw allHitsChoiceDraws) {
        ArrayList<List<Integer>> allChoiceDraws = new ArrayList<> ( );
        int countAllLottery = 0;
        while (countAllLottery < readerDB.getResults ( ).size ( )) {
            addChoiceDraws ( allHitsChoiceDraws , allChoiceDraws , countAllLottery );
            countAllLottery++;
        }
    }

    private void addChoiceDraws(AllHitsChoiceDraw allHitsChoiceDraws
            , ArrayList<List<Integer>> allChoiceDraws , int countAllLottery) {
        mapDocument ( countAllLottery );
        if ( ifResultsIsChoice ( ) ) {
            allChoiceDraws.add ( listChoiceLottery );
            allHitsChoiceDraws.setAllChoiceDraws ( allChoiceDraws );
        }
    }
    @SuppressWarnings(value = "unchecked")
    private void mapDocument(int countAllLottery) {
        Document doc = readerDB.getResults ( ).get ( countAllLottery );
        listChoiceLottery = (List<Integer>) doc.get ( "choice" );
    }

    public boolean ifResultsIsChoice() {
        return listChoiceLottery != null;
    }

    public IntStream numberHitsOfDraws(AllHitsChoiceDraw allHitsChoiceDraws) {
        return allHitsChoiceDraws.getAllChoiceDraws ( )
                .stream ( )
                .mapToInt ( List::size );
    }


    public long numberOfNumberHitsInChoiceDraw(AllHitsChoiceDraw allHitsChoiceDraws , int i) {
        return getIntStream ( allHitsChoiceDraws )
                .filter ( hit -> hit == i )
                .count ( );
    }

    public  IntStream getIntStream(AllHitsChoiceDraw allHitsChoiceDraws) {
        return allHitsChoiceDraws.getAllChoiceDraws ( )
                .stream ( )
                .flatMapToInt ( list -> list.stream ( ).mapToInt ( hit -> hit ) );
    }

    public BigDecimal percentNumberOfNumberHitsInChoiceDraw(AllHitsChoiceDraw allHitsChoiceDraws , int i) {
        double percentNumberOfNumberHits = (double) numberOfNumberHitsInChoiceDraw ( allHitsChoiceDraws , i ) * 100
                / allHitsChoiceDraws.getAllChoiceDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentNumberOfNumberHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }

    public long filteredChoiceDrawsHitNumber(AllHitsChoiceDraw allHitsChoiceDraws , int index) {
        return numberHitsOfDraws ( allHitsChoiceDraws )
                .filter ( e -> e == index )
                .boxed ( )
                .count ( );
    }

    public BigDecimal percentHitsOfChoice(AllHitsChoiceDraw allHitsChoiceDraws , int index) {
        double percentHits = (double) filteredChoiceDrawsHitNumber ( allHitsChoiceDraws , index ) * 100
                / allHitsChoiceDraws.getAllChoiceDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }
}
