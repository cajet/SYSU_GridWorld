import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	public Stack<Location> trueWay = new Stack<Location>();
	boolean hasShown = false;//final message has been shown
	private int[] probility = {1, 1, 1, 1};
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		//add the initial location to the first array list
		if(stepCount==0){
			Location local = this.getLocation();
			directionPredic();
			ArrayList<Location> first = new ArrayList<Location>();
			first.add(local);
			trueWay.push(local);
			crossLocation.add(first);
		}
	
		if(stepCount==341){
			directionPredic();
		}
		boolean willMove = canMove();
		if (isEnd == true) {
			setRightWay(trueWay);
		<span style="white-space:pre">	</span>//to show step count when reach the goal
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move
			stepCount++;
		} else {
			//If can't move, return to last location
			goBack();
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> g = getGrid();
		if (g == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();

		//get the valid location of the determined four direction
		int[] dir = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		for( int i = 0; i < 4; i++ ){
			Location location = loc.getAdjacentLocation(dir[i]);
			if(g.isValid(location)){
				Actor actor = g.get(location);
				//if the goal is around, return the location of the goal
				if(( actor instanceof Rock) && actor.getColor().equals(Color.RED) ){
					next = location;
					ArrayList<Location> tar = new ArrayList<Location>();
					tar.add(next);
					return tar;
				}else if(actor == null){
					valid.add(location);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> validLocation = new ArrayList<Location>();
		Location current = this.getLocation();
		
		validLocation = getValid(current);

		if (validLocation.size() == 0) {
			return false;
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> g = getGrid();
		if (g == null)
			return;
		Location loc = this.getLocation();
		
		ArrayList<Location> chooseLocation = getValid(loc);
		//根据当前每个方向走了多少次来分配概率。某一方向走的步数最多，则往该方向概率最大
		int max = 0;
		int j = 0;
		int total = 0;
		int whichOne = 0;
		for( Location got : chooseLocation ){
			int direc = loc.getDirectionToward(got);
			if( probility[direc/90] > max ){
				max = probility[direc/90];
				j = (int) direc/90;
				whichOne = total;
			}
			total++;
		}
		
		if(chooseLocation.size() == 1){
			next = chooseLocation.get(whichOne); 
			probility[j]++;
		}else {
			int randomNumber = (int) (Math.random() * 10);
			if(randomNumber >= 0 && randomNumber < 7){
				next = chooseLocation.get(whichOne); 
				probility[j]++;
			}else {
				next = chooseLocation.get(randomNumber % chooseLocation.size()); 
				int dire = loc.getDirectionToward(next);
				j = dire / 90;
				probility[j]++;
			}
		}
		
		for( Location l : chooseLocation ){
			if( this.getDirection() == this.getLocation().getDirectionToward(l) ){
				next = l;
				int dire = loc.getDirectionToward(next);
				j = dire / 90;
				probility[j]++;
				break;
			}
		}
		
		if (g.isValid(next)) {
			Actor actor = (Actor)g.get(next);
			
			if( actor instanceof Rock && actor.getColor().equals(Color.RED) ){
				isEnd = true;
				setRightWay(trueWay);
			}
			moveTo(next);
			trueWay.push(next);
			int facing = loc.getDirectionToward(next);
			this.setDirection(facing);
			
			ArrayList<Location> temp = crossLocation.pop();
			temp.add(next);
			crossLocation.push(temp);
			
			ArrayList<Location> latest = new ArrayList<Location>();
			latest.add(next);
			crossLocation.push(latest);
			
		} else {
			removeSelfFromGrid();
		}
		System.out.println(stepCount);
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(g, loc);
	}
	//回溯
	public void goBack(){
		if (crossLocation.size() > 0) {
			crossLocation.pop();
			trueWay.pop();
			if(crossLocation.size() > 0){
				Grid g = getGrid();
				if( g == null )
					return;
				ArrayList<Location> back = crossLocation.peek();
				Location returnLocation = back.get(0);

				Location current = this.getLocation();
				
				//set the direction when the bug return
				int dire = current.getDirectionToward(returnLocation);
				if (g.isValid(returnLocation)) {
					this.setDirection(dire);
					moveTo(returnLocation);
					stepCount++;
				}else {
					removeSelfFromGrid();
				}
				if ( (int)(dire/90) == 0 ) {
					probility[2]--;
				}else if ( (int)(dire/90) == 1 ) {
					probility[3]--;
				}else if ( (int)(dire/90) == 2 ) {
					probility[0]--;
				}else if ( (int)(dire/90) == 3 ) {
					probility[1]--;
				}
				Flower flower = new Flower(getColor());
				flower.putSelfInGrid(g, current);
			}
		}
	}
	//关于方向估计的函数，先判断终点大概位于虫子的哪个方向，然后选择位置的时候优先选择距离终点近的位置
	public void directionPredic(){
		Grid<Actor> g = getGrid();
		ArrayList<Location> array = g.getOccupiedLocations();
		
		for( Location a : array ){
			Actor act = (Actor) g.get(a);
			if ( act instanceof Rock && act.getColor().equals(Color.RED)){
				Location loc = this.getLocation();
				if( loc.getRow() < a.getRow() ){
					probility[2] = 6;
					probility[0] = 1;
				}else {
					probility[0] = 6;
					probility[2] = 1;
				}
				if( loc.getCol() < a.getCol() ){
					probility[1] = 6;
					probility[3] = 1;
				}else {
					probility[3] = 6;
					probility[1] = 1;
				}
				break;
			}
		}
	}
	
	//此函数是为了在虫子到达终点后把正确路径标记出来
	public void setRightWay(Stack<Location> way){
		for( Location w : way ){
			Grid g = getGrid();
			Actor act = (Actor) g.get(w);
			act.setColor(Color.GREEN);
		}
	}
}
