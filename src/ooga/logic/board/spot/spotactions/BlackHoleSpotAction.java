package ooga.logic.board.spot.spotactions;

import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;

import java.util.List;
import java.util.Random;

public class BlackHoleSpotAction implements SpotAction{
    private boolean search=false;

    @Override
    public void commitSpotAction(List<Spot> a, Spot s) {
        s.setPiece(null);
        ((GameSpot) s).setTypeOfSpot(0);
        while (search)
        {
            int random=new Random().nextInt(a.size());
            if (a.get(random).getPiece()==null)
            {
                ((GameSpot) a.get(random)).setTypeOfSpot(2);
                search=true;
            }
        }


    }


}
