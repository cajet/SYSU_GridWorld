/*
    zbug: extends bug, the path is a z
    a z path has two turn
    use a private int to record it
*/

import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
   private int steps;
   private int turn;
   private int sideLength;

   public ZBug(int length) {
      turn = 0;
      steps = 0;
      //sideLength is the length of z`s side
      sideLength = length;
   }

   public void act() {
      if (!canMove()) {
         ;//cannot move, stop
      } else {
       //bug go along the side of z
        if (steps < sideLength) {
           steps++;
           move();
        } else {
          switch(turn) {
                case 0:
                  //the first turn, bug is facing east now
                  setDirection(225);
                  break;
                case 1:
                  //the second turn, bug is facing southwest now
                  setDirection(90);
                  break;
                default:
                  //z has finished now, stop the bug
                  //set all thing to 0
                  sideLength = 0;
                  steps = 0;
                  return;
          }
         //after changing direction, add turn to mark it
         turn++;
         //a new side, set the steps to 0
         steps = 0;
        }
      }
   }
}
