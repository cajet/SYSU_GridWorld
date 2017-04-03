import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.gui.*;
import info.gridworld.world.*;

import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter 
{
    public ArrayList<Location> getMoveLocations() {
          ArrayList<Location> locs = new ArrayList<Location>();
          int[] dirs = { Location.LEFT, Location.RIGHT};
          for (Location loc : getFarLocationsInDirections(dirs)) {
              if (getGrid().get(loc) == null)
                 locs.add(loc);   
          }

          if (locs.size() != 0) return locs;

          for (Location loc : getLocationsInDirections(dirs)) {
              if (getGrid().get(loc) == null)
                 locs.add(loc);   
          }
          return locs;
    }
  
    public ArrayList<Location> getFarLocationsInDirections(int[] directions) {
         ArrayList<Location> locs = new ArrayList<Location>();
         Grid gr = getGrid();
         Location loc = getLocation();

        for (int d : directions) {
             Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
             Location tmp = neighborLoc.getAdjacentLocation(getDirection() + d);
             if (gr.isValid(tmp) && gr.get(neighborLoc) == null)
               locs.add(tmp);
        }
        return locs;
    }

    public ArrayList<Location> getLocationsInDirections(int[] directions) {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }
}
