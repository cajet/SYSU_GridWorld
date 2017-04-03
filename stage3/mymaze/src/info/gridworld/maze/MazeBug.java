package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
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
	public Location last;
	public boolean isEnd = false;
	//public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Stack<Location> crossLocation = new Stack<Location>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	int[] proturn = new int[4];

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = null;
		next = null;
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		last = getLocation();
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
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
			//no way to go ,back to last
			//System.out.println("test-back");
			Grid<Actor> gr = getGrid();
			last = crossLocation.peek();
			crossLocation.pop();
			//ArrayList<Location> tmp = crossLocation.peek();
			Location back = crossLocation.peek();
			setDirection(getLocation().getDirectionToward(back));
			moveTo(back);
			Flower flower = new Flower(getColor());
			flower.putSelfInGrid(gr, last);
			proturn[back.getDirectionToward(last)/90]--;
			last = back;
			next = null;
			stepCount++;

			//System.out.println(crossLocation.size());
			//System.out.println(crossLocation.peek().toString());
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
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		for (int i = 0; i < 4; i++) {
			Location tmpl = getLocation().getAdjacentLocation(i*90);
		    if (!gr.isValid(tmpl)) {
		    	continue;
		    } else if (gr.get(tmpl) == null) {
		    	valid.add(tmpl);
		    } else if ((gr.get(tmpl) instanceof Rock) && (gr.get(tmpl).getColor().equals(Color.RED))) {
		    	setDirection(getLocation().getDirectionToward(tmpl));
				isEnd = true;
				return null;
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
		ArrayList<Location> nextLocation = getValid(getLocation());
		ArrayList<Integer> select = new ArrayList<Integer>();
		if (nextLocation == null || nextLocation.size() == 0) {
			return false;
		} else {
			//增加方向概率估计
			for (int i = 0; i < nextLocation.size(); i++) {
				int tmp = (getLocation().getDirectionToward(nextLocation.get(i)))/90;
				//System.out.println(tmp);
				for (int j = 0; j < proturn[tmp]; j++)
					select.add(i);
			}
			//System.out.println(select.size());
		    int r = (int) (Math.random() * select.size());
		    //System.out.println(select.toString());
	        next = (select.size() == 0) ? nextLocation.get(0) : nextLocation.get(select.get(r));
	        proturn[getLocation().getDirectionToward(next)/90]++;
			return true;
		}
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		//if (gr.isValid(next)) {
		Location tmp;
		if (crossLocation.empty()) {
			crossLocation.push(getLocation());
		}
		setDirection(getLocation().getDirectionToward(next));
		
		crossLocation.push(next);
		moveTo(next);
		last = next;
		next = null;
		//} else
			//removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
		//System.out.println("test-move");
		//System.out.println(crossLocation.size());
		//System.out.println(crossLocation.toString());
		
	}
}
