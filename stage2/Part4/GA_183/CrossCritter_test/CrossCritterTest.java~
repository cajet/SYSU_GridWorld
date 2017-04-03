/* GA_183_Part4
   CrossCritter: a romantic critter
               it finds rock in its cross(with 2 steps length)
               if find rock, it will be turned to a flower
               if no rock, it will turn every actor in the cross
                  to a rock

   test for processActor
   check some kinds of actors in the cross
   after act 
*/
import static org.junit.Assert.*;
import org.junit.Test;

/*gridword*/
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import info.gridworld.gui.*;

import java.awt.Color;
import java.util.ArrayList;

public class CrossCritterTest {
    public ActorWorld world = new ActorWorld();
    public CrossCritter cross1 = new CrossCritter();
    public Bug bug = new Bug();
    public Rock rock = new Rock();
    public Flower flower = new Flower();
   
    /*1 found a rock in the cross, and turn it to a flower*/
    @Test
    public void testRtoF() {
        world.add(new Location(4, 4), cross1);
        world.add(new Location(5, 4), rock);
        cross1.act();
        Actor a = cross1.getGrid().get(new Location(5,4));
        assertTrue(a instanceof Flower);
        cross1.removeSelfFromGrid();
        a.removeSelfFromGrid();
    }

   /*2 found no rock in the cross, and turn all to a rock*/
    @Test
    public void testAtoR() {
        world.add(new Location(4, 4), cross1);
        world.add(new Location(5, 4), bug);
        cross1.act();
        Actor a = cross1.getGrid().get(new Location(5,4));
        assertTrue(a instanceof Rock);
    }

    /*3 found no actor in the cross, and do nothing*/
    @Test
    public void testNtoD() {
        world.add(new Location(4, 4), cross1);
        cross1.act();
        Actor a = cross1.getGrid().get(new Location(5,4));
        assertTrue(a == null);
    }
}

