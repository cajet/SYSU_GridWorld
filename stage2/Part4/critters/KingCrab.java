import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.gui.*;
import info.gridworld.world.*;

import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter 
{
     public void processActors(ArrayList<Actor> actors) {
          Grid gr = getGrid();
          int[] dirs = {-45, 0, 45};
          Location loc = getLocation();

          for (Actor a : actors) {
             if (a.getLocation().equals(loc.getAdjacentLocation(getDirection() + dirs[0]))) {
                 Location tmp = a.getLocation().getAdjacentLocation(getDirection() + dirs[0]);
                 if(gr.isValid(tmp) && gr.get(tmp) == null) {
                    a.moveTo(tmp);
                  } else {
                    a.removeSelfFromGrid();
                  }
              } else if (a.getLocation().equals(loc.getAdjacentLocation(getDirection() + dirs[1]))) {

                 Location tmp = a.getLocation().getAdjacentLocation(getDirection() + dirs[1]);
                 if(gr.isValid(tmp) && gr.get(tmp) == null) {
                    a.moveTo(tmp);
                  } else {
                    a.removeSelfFromGrid();
                  }
              } else if (a.getLocation().equals(loc.getAdjacentLocation(getDirection() + dirs[2]))) {
                 Location tmp = a.getLocation().getAdjacentLocation(getDirection() + dirs[2]);
                 if(gr.isValid(tmp) && gr.get(tmp) == null) {
                    a.moveTo(tmp);
                  } else {
                    a.removeSelfFromGrid();
                  }
              }  
          }
     }
}
