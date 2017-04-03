/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrossCritter</code>
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class CrossCritter extends Critter
{
    public CrossCritter()
    {
        setColor(Color.DARK_GRAY);
    }

    /**
     * A CrossCritter gets the actors in the four locations
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.LEFT, Location.RIGHT, Location.HALF_CIRCLE };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }
        return actors;
    }

    public void processActors(ArrayList<Actor> actors)
    {
    	int[] dirs =
            { Location.AHEAD, Location.LEFT, Location.RIGHT, Location.HALF_CIRCLE};
    	boolean flag = false;

        for (Actor a : actors)
        {
            if (a instanceof Rock)
            {
            	flag = true;
            	Location loc = a.getLocation();
            	Flower flower = new Flower(Color.RED);
            	a.removeSelfFromGrid();
            	flower.putSelfInGrid(getGrid(), loc);
            }      
        }
        if (flag) return;
        
        for (Actor a : actors)
        {
        	Location loc = a.getLocation();
        	Rock rock = new Rock(getColor());
        	a.removeSelfFromGrid();
        	rock.putSelfInGrid(getGrid(), loc);
        }
        return;
    }


    
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
            neighborLoc = neighborLoc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }    

    public ArrayList<Location> getLocationsInDirectionsOneStep(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }  
}
