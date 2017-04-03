import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.gui.*;
import info.gridworld.world.*;
/**
* This class runs a world with additional grid choices.
*/
public class GridRunner
{
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("SparseBoundedGrid");
    world.addGridClass("SparseBoundedGrid2");
    world.addGridClass("UnboundedGrid1");
    world.add(new Location(2, 2), new Bug());
    world.add(new Location(2, 3), new Rock());
    world.show();
  }
}

