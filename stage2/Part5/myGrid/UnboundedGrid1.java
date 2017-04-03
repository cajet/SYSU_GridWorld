import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.gui.*;
import info.gridworld.world.*;
import java.util.*;

public class UnboundedGrid1<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;

    public UnboundedGrid1()
    {
        occupantArray = new Object[16][16];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return (loc.getRow() >= 0 && loc.getCol()>= 0);
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        for (int r = 0; r < occupantArray.length; r++)
        {
            for (int c = 0; c < occupantArray[0].length; c++)
            {
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length)
            return null;
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");

        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        E oldOccupant = get(loc);
        if (loc.getRow() < occupantArray.length && loc.getCol() < occupantArray[0].length) {
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        } else {
          Object[][] tmp = occupantArray;
          int ant = tmp.length;
          while(loc.getRow() >= ant || loc.getCol() >= ant)
               ant *= 2;
          occupantArray = new Object[ant][ant];
          for (int r = 0; r < tmp.length; r++) {
               for (int c = 0; c < tmp.length; c++)
                   occupantArray[r][c] = tmp[r][c];
          }
          occupantArray[loc.getRow()][loc.getCol()] = obj;
        }
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");

        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
 
        E r = get(loc);
        if (loc.getRow() < occupantArray.length && loc.getCol() < occupantArray.length)
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}





