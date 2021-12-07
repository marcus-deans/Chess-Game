package ooga.logic.board.spot.spotactions;

import ooga.logic.board.spot.GameSpot;
import ooga.logic.board.spot.Spot;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PortalSpotAction implements SpotAction{
    private boolean search=true;
    private boolean search2=true;
    private Logger myLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public List<Spot> commitSpotAction(List<Spot> a, Spot s) {
        ((GameSpot) a.stream().filter(spot -> spot.equals(s)).findFirst().orElse(null)).setTypeOfSpot(0);
        while (search)
        {
            int random=new Random().nextInt(a.size());
            if (a.get(random).getPiece()==null)
            {
                a.get(random).setPiece(s.getPiece());
                search=false;
                a.stream().filter(spot -> spot.equals(s)).findFirst().orElse(null).setPiece(null);
            }
        }
        while (search2)
        {
            int random=new Random().nextInt(a.size());
            if (a.get(random).getPiece()==null)
            {
                ((GameSpot) a.get(random)).setTypeOfSpot(1);
                search2=false;
            }
        }
        myLogger.log(Level.INFO, "Your piece has entered a portal and teleported to a random spot");
        return a;
    }
}
