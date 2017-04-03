/* 
   dancinbug:accept a array as a parameter
   before each move, turn some certain angle
*/
import java.util.Arrays;
import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
   private int[] arr;
   private int index;

   public DancingBug(int[] a) {
      arr = Arrays.copyOf(a, a.length);
      //index records which num in the array should use
      index = 0;
   }

   public void act() {
      //After carrying out the last turn in the array
      //it will start again with the initial array value
      index = index % arr.length;
      //each turn 45 degrees
      //the final angle = current angel + patten angle 
      setDirection(getDirection()+arr[index]*45);
      index++;
      // if it can move, move it
      //otherwise, ignore it
      if (canMove()){
         move();
      } 
   }
}
