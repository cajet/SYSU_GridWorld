import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;

public class ChameleonKid extends MChameleonCritter
{
     public ArrayList<Actor> getActors(){
            ArrayList<Actor> actors = new ArrayList<Actor>();
            int[] dirs =
            { Location.AHEAD, Location.HALF_CIRCLE };
            for (Location loc : getLocationsInDirections(dirs))
            {
                Actor a = getGrid().get(loc);
                if (a != null)
                    actors.add(a);
            }

            return actors;
     }

     public ArrayList<Location> getLocationsInDirections(int[] directions) {
          ArrayList<Location> locs = new ArrayList<Location>();
          Grid gr = getGrid();
          Location loc = getLocation();
          for (int d: directions) {
              Location neighborLoc = loc.getAdjacentLocation(getDirection()+d);
              if (gr.isValid(neighborLoc))
                 locs.add(neighborLoc);
          }
          return locs;
     }
}
