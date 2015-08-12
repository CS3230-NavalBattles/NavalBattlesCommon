package Common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Joint class by Battleship group on 7/16/2015.
 */
public class Ship implements Serializable {
    private static final long serialVersionUID = 5358685165989124933L;

    private int size;
    private String direction;
    private int xcoordinate;
    private int ycoordinate;
    private ArrayList<int[]> availhits;
    private String name;
    private boolean isset;
    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String VERTICAL = "VERTICAL";

    public Ship(int ssize, String label) {
        size = ssize;
        name = label;
        isset = false;
        direction = VERTICAL;
        xcoordinate = 0;
        ycoordinate = 0;
        availhits = new ArrayList<int[]>();
    }

    public Boolean hitShip(int x, int y) {
        for (int[] i : availhits) {
            if (i[0] == x && i[1] == y) {
                availhits.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean isSet()
    {
        return isset;
    }

    public void setShip()
    {
        isset = true;
    }

    public void resetShip()
    {
        isset = false;
        xcoordinate = 0;
        ycoordinate = 0;
        direction = VERTICAL;
        availhits.clear();
    }

    private boolean checkOutOfBound(int xcoord, int ycoord) {
        if (direction == VERTICAL) {
            if (ycoord >= 0 && ycoord < 10 - (size - 1)) {
                return true;
            }
        } else {
            if (xcoord >= 0 && xcoord < 10 - (size -1)) {
                return true;
            }
        }
        return false;
    }



    public boolean setLocation(int x, int y) {
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
    public int getXcoordinate() {
        int x = xcoordinate;
        return x;
    }

    public int getYcoordinate() {
        int y = ycoordinate;
        return y;
    }

    public int getSize() {
        int s = size;
        return s;
    }


    public String getDirection() {
        String d = direction;
        return d;
    }

    public String getName()
    {
        return name;
    }

    public void changeDirection() {
        if (direction.equals(HORIZONTAL)) {
            direction = VERTICAL;
        } else {
            direction = HORIZONTAL;
        }
    }

    public boolean hasBeenSunk() {
        if (availhits.size() > 0) {
            return false;
        }
        return true;
    }

    public static boolean placeShips(Ship[] ships, boolean[][] board) {
        if (board == null || board.length <= 0 || board[0].length <= 0) {
            throw new IllegalStateException("No valid board found to place ships");
        }

        for (Ship ship: ships) {
            if (isValidPlacement(ship, board)) {
                place(ship, board);
            }
            else {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPlacement(Ship ship, boolean[][] board) {
        int size = ship.size;
        String orientation = ship.direction;

        if (ship.xcoordinate < 0 || ship.ycoordinate < 0) {
            return false;
        }
        else if (orientation.equals(HORIZONTAL) && ship.xcoordinate + size > board[0].length ||
                orientation.equals(VERTICAL) && ship.ycoordinate + size > board.length) {
            return false;
        }

        while (size > 0) {
            int column = ship.xcoordinate;
            int row = ship.ycoordinate;
            if (orientation.equals(VERTICAL)) {
                row += ship.size - size;
            }
            else {
                column += ship.size - size;
            }
            if (board[row][column]) {
                return false;
            }

            size--;
        }

        return true;
    }

    private static void place(Ship ship, boolean[][] board) {
        int size = ship.size;
        while (size > 0) {
            int position = ship.size - size;
            if (ship.direction.equals(VERTICAL)) {
                board[ship.ycoordinate + position][ship.xcoordinate] = true;
            }
            else {
                board[ship.ycoordinate][ship.xcoordinate + position] = true;
            }
            size--;
        }
    }

    @Override
    public String toString() {
        return name + ": " +
                "Start position(" + xcoordinate + "," + ycoordinate + "), " +
                "Size(" + size + "), " +
                "Direction(" + direction + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ship other = (Ship) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
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