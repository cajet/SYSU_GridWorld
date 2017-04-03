import info.gridworld.actor.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public final class JumperRunner
{
   private JumperRunner() {}
  
   public static void main(String[] args) {
     ActorWorld world = new ActorWorld();
     Jumper zyh = new Jumper(Color.YELLOW);
     Jumper zjs = new Jumper(Color.blue);
     Bug yqm = new Bug();
     Rock zyy = new Rock();
     Flower ztt = new Flower();


     world.add(new Location(5, 5), zyh);
     world.add(new Location(2,2), zjs);
     world.add(new Location(4, 3), yqm);
     world.add(new Location(6, 2), zyy);
     world.add(new Location(4,5), ztt);
     world.show();
   }
}
