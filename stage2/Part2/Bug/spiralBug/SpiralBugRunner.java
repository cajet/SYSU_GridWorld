

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public final class SpiralBugRunner
{
  public static void main(String[] args) {
      ActorWorld world = new ActorWorld();
      //create a Spiralbug 
      //the initial sideLength is 2
      SpiralBug zyh = new SpiralBug(2);
      //set its color 
      zyh.setColor(Color.ORANGE);
      //add the bug into grids, and set the location
      world.add(new Location(5,5), zyh);
      world.show();
  }

  private SpiralBugRunner() {}
}
