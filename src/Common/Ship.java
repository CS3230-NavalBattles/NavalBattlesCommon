package Common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Joint class by Battleship group on 7/16/2015.
 */
public class Ship implements Serializable {
    private int size;
    private String direction;
    private int xcoordinate;
    private int ycoordinate;
    private ArrayList<int[]> availhits;
    private String name;
    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String VERTICAL = "VERTICAL";

    public Ship(int ssize, String label) {
        size = ssize;
        name = label;
        direction = VERTICAL;
        xcoordinate = 0;
        ycoordinate = 0;
        availhits = new ArrayList<int[]>();
    }

    protected Boolean hitShip(int x, int y) {
        for (int[] i : availhits) {
            if (i[0] == x && i[1] == y) {
                availhits.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean checkOutOfBound(int xcoord, int ycoord) {
        if (direction == VERTICAL) {
            if (ycoord >= 0 && ycoord < (10 - size)) {
                return true;
            }
        } else {
            if (xcoord >= 0 && xcoord < (10 - size)) {
                return true;
            }
        }
        return false;
    }

    protected boolean setLocation(int x, int y) {
        if (checkOutOfBound(x, y)) {
            xcoordinate = x;
            ycoordinate = y;
            availhits.clear();
            for (int i = 0; i < size; i++) {
                int[] loc = new int[2];
                if (direction.equals(HORIZONTAL)) {
                    loc[0] = xcoordinate + i;
                    loc[1] = ycoordinate;
                } else {
                    loc[0] = xcoordinate;
                    loc[1] = ycoordinate + i;
                }
                availhits.add(loc);
            }
            return true;
        }
        return false;
    }

    //Accessor Methods
    protected int getXcoordinate() {
        int x = xcoordinate;
        return x;
    }

    protected int getYcoordinate() {
        int y = ycoordinate;
        return y;
    }

    protected int getSize() {
        int s = size;
        return s;
    }

    protected String getDirection() {
        String d = direction;
        return d;
    }

    protected String getName()
    {
        String n = name;
        return n;
    }

    protected void changeDirection() {
        if (direction.equals(HORIZONTAL)) {
            direction = VERTICAL;
        } else {
            direction = HORIZONTAL;
        }
    }

    protected boolean hasBeenSunk() {
        if (availhits.size() > 0) {
            return false;
        }
        return true;
    }

    //test method
//    public static void test()
//    {
//        Ship s = new Ship(4,"Battleship");
//        s.setLocation(3,3);
//        System.out.println(s.availhits.toString());
//        s.hitShip(3, 5);
//        System.out.println(s.availhits.toString());
//    }
//
//    public static void main(String[] args)
//    {
//        test();
//    }

}