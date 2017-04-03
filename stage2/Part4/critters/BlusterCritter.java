import info.gridworld.actor.*;
import info.gridworld.gui.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;

import java.awt.Color;
import java.util.ArrayList;


public class BlusterCritter extends Critter 
{
    private int courage;
    private static final double COLOR_FACTOR = 0.05;  

    public BlusterCritter(int c) {
          this.courage = c;
          setColor(Color.PINK);
    }

    public ArrayList<Actor> getActors() {
          ArrayList<Actor> actors = new ArrayList<Actor>();
          int[] dirs= {0, 45, 90, 135, 180, -45, -90, -135};
          for (Location loc : getLocationsInDirections(dirs)) {
		Actor a = getGrid().get(loc);
                if (a != null && a instanceof Critter)
                    actors.add(a);
          }
          return actors;
    }


    public ArrayList<Location> getLocationsInDirections(int[] directions) {
         ArrayList<Location> locs1 = new ArrayList<Location>();
         ArrayList<Location> locs2 = new ArrayList<Location>();
         Grid gr = getGrid();
         Location loc = getLocation();
         Location loc1 = loc.getAdjacentLocation(-45);
         Location loc2 = loc.getAdjacentLocation(45);
         Location loc3 = loc.getAdjacentLocation(-135);
         Location loc4 = loc.getAdjacentLocation(135);

        if (gr.isValid(loc1)) locs1.add(loc1);
        if (gr.isValid(loc2)) locs1.add(loc2); 
        if (gr.isValid(loc3)) locs1.add(loc3); 
        if (gr.isValid(loc4)) locs1.add(loc4);
 
         for (Location l : locs1) {
             for (int d : directions) {
                  Location tmp = l.getAdjacentLocation(getDirection()+d);
                  if (gr.isValid(tmp) && !locs2.contains(tmp))
                     locs2.add(tmp);
             }
         }
         
         if (gr.isValid(loc1)) locs2.add(loc1); 
         if (gr.isValid(loc2)) locs2.add(loc2); 
         if (gr.isValid(loc3)) locs2.add(loc3); 
         if (gr.isValid(loc4)) locs2.add(loc4); 
         return locs2;
    }

    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        Color c = getColor();
        int red, green, blue;
        if (n >= this.courage) {
           red = (int)(c.getRed()*(1-COLOR_FACTOR));
           green = (int)(c.getGreen()*(1-COLOR_FACTOR));
           blue = (int)(c.getBlue()*(1-COLOR_FACTOR));
        } else {
           red = (int)(c.getRed()+ 100 * COLOR_FACTOR);
           green = (int)(c.getGreen()+ 100 * COLOR_FACTOR);
           blue = (int)(c.getBlue() + 100 *COLOR_FACTOR);
        }
           red = (red >= 255) ? 255 : red;
           green = (green >= 255) ? 255 : green;
           blue = (blue >= 255) ? 255 : blue;
        setColor(new Color(red, green, blue));
    }
}
