import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;


public final class CircleBugRunner
{
    private CircleBugRunner() {}
    public static void main(String[] args) {
          //create the grids
          ActorWorld world = new ActorWorld(); 
          //a bug with sideLength 6
          CircleBug alice = new CircleBug(6);  
          //set the color of bug
          alice.setColor(Color.ORANGE);
          //add it and set location  
          CircleBug bob = new CircleBug(3);
          world.add(new Location(7, 8), alice); 
          world.add(new Location(5, 5), bob);
          //show the grids
          world.show(); 
    }
}
