/*
   the runner of dancingBug
*/
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;
 
public final class DancingBugRunner
{
   private DancingBugRunner() {}
   public static void main(String[] args) {
      // create a patten array
      int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
      ActorWorld world = new ActorWorld();
      //creat a DancingBug with the patten array above
      DancingBug zyh = new DancingBug(a);
      zyh.setColor(Color.ORANGE);
      //add the bug into grid, and set the location initially
      world.add(new Location(5,5), zyh);
      world.show();
   }
}
