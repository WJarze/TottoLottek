package model.statistics;

import data.lotto.AllHitsChoiceDrawFromDB;
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

    public void setAllChoiceLottery(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB) {
        ArrayList<List<Integer>> allChoiceDraws = new ArrayList<> ( );
        int countAllLottery = 0;
        while (countAllLottery < readerDB.getResults ( ).size ( )) {
            addChoiceDraws ( allHitsChoiceDrawsFromDB , allChoiceDraws , countAllLottery );
            countAllLottery++;
        }
    }

    private void addChoiceDraws(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB
            , ArrayList<List<Integer>> allChoiceDraws , int countAllLottery) {
        mapDocument ( countAllLottery );
        if ( ifResultsIsChoice ( ) ) {
            allChoiceDraws.add ( listChoiceLottery );
            allHitsChoiceDrawsFromDB.setAllChoiceDraws ( allChoiceDraws );
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

    public IntStream numberHitsOfDraws(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB) {
        return allHitsChoiceDrawsFromDB.getAllChoiceDraws ( )
                .stream ( )
                .mapToInt ( List::size );
    }


    public long numberOfNumberHitsInChoiceDraw(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB , int i) {
        return getIntStream ( allHitsChoiceDrawsFromDB )
                .filter ( hit -> hit == i )
                .count ( );
    }

    public  IntStream getIntStream(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB) {
        return allHitsChoiceDrawsFromDB.getAllChoiceDraws ( )
                .stream ( )
                .flatMapToInt ( list -> list.stream ( ).mapToInt ( hit -> hit ) );
    }

    public BigDecimal percentNumberOfNumberHitsInChoiceDraw(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB , int i) {
        double percentNumberOfNumberHits = (double) numberOfNumberHitsInChoiceDraw ( allHitsChoiceDrawsFromDB , i ) * 100
                / allHitsChoiceDrawsFromDB.getAllChoiceDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentNumberOfNumberHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }

    public long filteredChoiceDrawsHitNumber(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB , int index) {
        return numberHitsOfDraws ( allHitsChoiceDrawsFromDB )
                .filter ( e -> e == index )
                .boxed ( )
                .count ( );
    }

    public BigDecimal percentHitsOfChoice(AllHitsChoiceDrawFromDB allHitsChoiceDrawsFromDB , int index) {
        double percentHits = (double) filteredChoiceDrawsHitNumber ( allHitsChoiceDrawsFromDB , index ) * 100
                / allHitsChoiceDrawsFromDB.getAllChoiceDraws ( ).size ( );
        BigDecimal bd = BigDecimal.valueOf ( percentHits );
        return new BigDecimal ( String.valueOf ( bd ) , mc );
    }
}
