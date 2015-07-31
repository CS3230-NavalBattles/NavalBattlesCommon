package Common;

import java.util.ArrayList;

/**
 * Created by Clinton on 7/16/2015.
 */
public class Ship
{
    private int size;
    private String direction;
    private int xcoordinate;
    private int ycoordinate;
    private ArrayList<int[]> availhits;
    private String name;
    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String VERTICAL = "VERTICAL";

    public Ship(int ssize, String label)
    {
        size = ssize;
        name = label;
        direction = VERTICAL;
        xcoordinate = 0;
        ycoordinate = 0;
        availhits = new ArrayList<int[]>();
    }

    protected Boolean hitShip(int x, int y)
    {
        for(int[] i : availhits)
        {
            if(i[0] == x && i[1] == y)
            {
                return true;
            }
        }
        return false;
    }

    protected void setLocation(int x, int y)
    {
        xcoordinate = x;
        ycoordinate = y;
        availhits.clear();
        for (int i = 0; i<size; i++)
        {
            int[] loc = new int[2];
            if (direction.equals(HORIZONTAL))
            {
                loc[0] = xcoordinate + i;
                loc[1] = ycoordinate;
            }
            else
            {
                loc[0] = xcoordinate;
                loc[1] = ycoordinate + i;
            }
            availhits.add(loc);
        }
    }

    //Accessor Methods
    protected int getXcoordinate()
    {
        int x = xcoordinate;
        return x;
    }

    protected int getYcoordinate()
    {
        int y = ycoordinate;
        return y;
    }

    protected int getSize()
    {
        int s = size;
        return s;
    }

    protected String getDirection()
    {
        String d = direction;
        return d;
    }

    protected void changeDirection()
    {
        if(direction.equals(HORIZONTAL))
        {
            direction = VERTICAL;
        }
        else
        {
            direction = HORIZONTAL;
        }
    }

    protected boolean hasBeenSunk()
    {
        if(availhits.size() > 0)
        {
            return false;
        }
        return true;
    }

//    These two methods will need to be changed.
//    protected void addHit()
//    {
//        if(hits < size)
//        {
//            hits++;
//        }
//    }
//
//    protected boolean hasBeenSunk()
//    {
//        if (hits == size)
//        {
//            return true;
//        }
//        return false;
//    }
}
