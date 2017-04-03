
import info.gridworld.actor.Bug;

public class CircleBug extends Bug
{
   private int steps;
   private int sideLength;

   public CircleBug(int length) {
       //initial
       steps = 0;
       sideLength = length;
     }

   public void act() {
       if (steps < sideLength && canMove()) {
             move();
             steps++;
          } else {
            //turn only once:45 degrees
            turn();
            //set the steps to 0 after turn
           steps = 0;
          }	
       }
}
