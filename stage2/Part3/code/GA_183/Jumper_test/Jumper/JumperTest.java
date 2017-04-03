/*test*/
import static org.junit.Assert.*;
import org.junit.Test;


/*gridword*/
import info.gridworld.actor.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.awt.Color;

public class JumperTest {
    public ActorWorld world = new ActorWorld();
    public Jumper jumper = new Jumper(Color.YELLOW);
    public Jumper jumper2 = new Jumper(Color.RED);
    public Bug bug = new Bug();
    public Rock rock = new Rock();
    public Flower flower = new Flower();
    /*1 normal move*/
    @Test
    public void testMove() {
        world.add(new Location(4, 4), jumper);
        jumper.act();
        Location loc = jumper.getLocation();
        Location loc2 = new Location(2, 4);
        assertEquals(loc, loc2);
    }
    /*2 forword one cell is not rock, forword two cell is empty*/
    @Test
    public void testJump() {
        world.add(new Location(4, 4), jumper);
        world.add(new Location(3, 4), rock);
        jumper.act();
        int direc = jumper.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = new Location(2, 4);
        assertTrue(loc.equals(loc2) && direc == Location.NORTH);
    }
    /*3 forword one cell is empty, forword two cell is Rock*/
    @Test
    public void testBlockRock() {
        world.add(new Location(4, 4), jumper);
        world.add(new Location(2, 4), rock);
        jumper.act();
        int direc = jumper.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = new Location(4, 4);
        assertTrue(loc.equals(loc2) && direc == Location.NORTHEAST);
    }
    /*4 forword one cell is empty, forword two cell is Bug*/
    @Test
    public void testBlockBug() {
        world.add(new Location(4, 4), jumper);
        world.add(new Location(2, 4), bug);
        jumper.act();
        int direc = jumper.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = new Location(4, 4);
        assertTrue(loc.equals(loc2) && direc == Location.NORTHEAST);
    }
    /*5 forword one cell is empty, forword two cell is Flower*/
    @Test
    public void testBlockFlower() {
        world.add(new Location(4, 4), jumper);
        world.add(new Location(2, 4), flower);
        jumper.act();
        int direc = jumper.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = new Location(4, 4);
        assertTrue(loc.equals(loc2) && direc == Location.NORTHEAST);
    }
    /*6 two cells in front of the jumper is out of the grid*/
    @Test
    public void testOutOfGrid() {
        world.add(new Location(1, 4), jumper);
        jumper.act();
        int direc = jumper.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = new Location(1, 4);
        assertTrue(loc.equals(loc2) && direc == Location.NORTHEAST);
    }
    /*7 facing an edge of the grid*/
    @Test
    public void testFacingGrid() {
        world.add(new Location(0, 4), jumper);
        jumper.act();
        int direc = jumper.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = new Location(0, 4);
        assertTrue(loc.equals(loc2) && direc == Location.NORTHEAST);
    }
    /*8 forword one cell is empty, forword two cell is another Jumper*/
    @Test
    public void testFacingJumper1() {
        world.add(new Location(3, 4), jumper);
        jumper2.setDirection(Location.SOUTH);
        world.add(new Location(1, 4), jumper2);
        jumper.act();
        jumper2.act();
        int direc = jumper.getDirection();
        int direc2 = jumper2.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = jumper2.getLocation();
        Location locr = new Location(3, 4);
        Location locr2 = new Location(1, 4);
        assertTrue(loc.equals(locr) && direc == Location.NORTHEAST);
        assertTrue(loc2.equals(locr2) && direc2 == Location.SOUTHWEST);
    }
    /*9 forword one cell is Jumper, forword two cell is empty*/
    @Test
    public void testFacingJumper2() {
        world.add(new Location(2, 4), jumper);
        jumper2.setDirection(Location.SOUTH);
        world.add(new Location(1, 4), jumper2);
        jumper.act();
        jumper2.act();
        int direc = jumper.getDirection();
        int direc2 = jumper2.getDirection();
        Location loc = jumper.getLocation();
        Location loc2 = jumper2.getLocation();
        Location locr = new Location(0, 4);
        Location locr2 = new Location(3, 4);
        assertTrue(loc.equals(locr) && direc == Location.NORTH);        
        assertTrue(loc2.equals(locr2) && direc2 == Location.SOUTH);
    }
}

