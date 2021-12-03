package ooga.logic.board.spot.spotactions;

import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlackHoleSpotAction implements SpotAction{
    private boolean search=false;
    private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public List<Spot> commitSpotAction(List<Spot> a, Spot s) {
        ((GameSpot) a.stream().filter(spot -> spot.equals(s)).findFirst().orElse(null)).setTypeOfSpot(0);
         a.stream().filter(spot -> spot.equals(s)).findFirst().orElse(null).setPiece(null);
        while (search)
        {
            int random=new Random().nextInt(a.size());
            if (a.get(random).getPiece()==null)
            {
                ((GameSpot) a.get(random)).setTypeOfSpot(2);
                search=true;
            }
        }
        myLogger.log(Level.INFO, "Your piece has entered a blackhole and been swallowed");
        return a;
    }


}
