package data.lotto;

import java.util.List;
import org.bson.Document;

public class Hit {
    private List<Integer> hitLuckyDip;
    private List<Integer> hitChoice;

    public List<Integer> getHitChoice() {
        return hitChoice;
    }

    public void setHitChoice(List<Integer> hitChoice) {
        this.hitChoice = hitChoice;
    }

    public List<Integer> getHitLuckyDip() {
        return hitLuckyDip;
    }

    public void setHitLuckyDip(List<Integer> hitLuckyDip) {
        this.hitLuckyDip = hitLuckyDip;
    }

    public Document hitLuckyDipDTO() {
        return new Document ( "title" , "Hits" )
                .append ( "lucky dip" , hitLuckyDip );
    }

    public Document hitChoiceDTO() {
        return new Document ( "title" , "Hits" )
                .append ( "choice" , hitChoice );
    }

    @Override
    public String toString() {
        return "Hit{" +
                "hitLuckyDip=" + hitLuckyDip +
                ", hitChoice=" + hitChoice +
                '}';
    }
}
