package model.statistics;

import data.lotto.AllHitsChoiceDraw;
import databaseService.ReaderDB;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.bson.Document;


public class ChoiceStatistics {
    int countAllChoiceLottery;
    List<Integer> listChoiceLottery;
    ReaderDB readerDB;

    public List<Integer> getListChoiceLottery() {
        return listChoiceLottery;
    }

    public void setListChoiceLottery(List<Integer> listChoiceLottery) {
        this.listChoiceLottery = listChoiceLottery;
    }

    public ChoiceStatistics() {
    }

    public ChoiceStatistics(ReaderDB readerDB) {
        this.readerDB = readerDB;
    }

    public void setCountAllChoiceLottery(AllHitsChoiceDraw allHitsChoiceDraws) {
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
            countAllChoiceLottery++;
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

    public double percentNumberOfNumberHitsInChoiceDraw(AllHitsChoiceDraw allHitsChoiceDraws , int i) {
        return (double) numberOfNumberHitsInChoiceDraw ( allHitsChoiceDraws , i )
                / allHitsChoiceDraws.getAllChoiceDraws ( ).size ( );
    }

    public long filteredChoiceDrawsHitNumber(AllHitsChoiceDraw allHitsChoiceDraws , int index) {
        return numberHitsOfDraws ( allHitsChoiceDraws )
                .filter ( e -> e == index )
                .boxed ( )
                .count ( );
    }

    public double percentHitsOfChoice(AllHitsChoiceDraw allHitsChoiceDraws , int index) {
        return (double) filteredChoiceDrawsHitNumber ( allHitsChoiceDraws , index )
                / allHitsChoiceDraws.getAllChoiceDraws ( ).size ( );
    }
}
