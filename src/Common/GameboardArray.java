package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Clinton on 7/16/2015.
 */
public class GameboardArray implements Serializable {
    private int[][] gameBoard;
    private int xsize = 10;
    private int ysize = 10;
    private ArrayList<Ship> ships;

    //class constants
    public static final int HIT = 1;
    public static final int MISS = -1;

    //copied from Chris's code. not sure if i want to use them yet.
    private HashMap<String, Integer> shipType = new HashMap<String, Integer>()
    {{
            put("Aircraft Carrier", 5);
            put("Battleship", 4);
            put("Destroyer", 3);
            put("Submarine", 3);
            put("Boat", 2);
        }};

    //copied from Chris's code. not sure if i want to use them yet.
    private HashMap<String, Boolean> isShipSet = new HashMap<String, Boolean>()
    {{
            put("Aircraft Carrier", false);
            put("Battleship", false);
            put("Destroyer", false);
            put("Submarine", false);
            put("Boat", false);
        }};




    public GameboardArray()
    {
        gameBoard = new int[ysize][xsize];
        resetBoard();
        ships = new ArrayList<Ship>();
    }

    private void resetBoard()
    {
        for(int row = 0; row < ysize; row++)
        {
            for(int col = 0; col < xsize; col++)
            {
                gameBoard[row][col] = 0;
            }
        }
    }

//    private void printBoard()
//    {
//        for(int row = 0; row < ysize; row++)
//        {
//            for(int col = 0; col < xsize; col++)
//            {
//                System.out.print(gameBoard[row][col] + " ");
//            }
//            System.out.println();
//        }
//    }

    private void populateboardwithships()
    {
        if(ships.size() > 0)
        {
            for(Ship ship: ships)
            {
                if(ship.isSet())
                {
                    addshiptoboard(ship);
                }
            }
        }
    }

    public boolean checkShip(Ship s)
    {
        boolean test = true;
        resetBoard();
        populateboardwithships();
        if(s.getDirection().equals(Ship.HORIZONTAL))
        {
            for(int i = 0; i < s.getSize(); i++)
            {
                if(!checkHorizOutOfBound((s.getXcoordinate()+i),s.getYcoordinate()))
                {
                    test = false;
                }
            }
        }
        else
        {
            for(int i = 0; i < s.getSize(); i++)
            {
                if(!checkVertOutOfBound(s.getXcoordinate(), (s.getYcoordinate() + i)))
                {
                    test = false;
                }
            }
        }
        if(test)
        {
            if(!s.isSet())
            {
                s.setShip();
            }
        }
        else
        {
            if(s.isSet())
            {
                s.setShip();
            }
        }
//        System.out.println("Ship placement: " + test);
        return test;
    }

//    private void printShips()
//    {
//        for (Ship ship : ships)
//        {
//            System.out.println("Ship: " + ship.getName() + " : Direc: " + ship.getDirection() + " : Coords: " + ship.getXcoordinate() + "," + ship.getYcoordinate() + " : isset: " + ship.isSet());
//        }
//    }


    public boolean addShips(ArrayList<Ship> shiparray)
    {
        resetBoard();
        ships.clear();
        boolean allpassed = true;
        for(Ship s: shiparray)
        {
            if(s.isSet())
            {
                ships.add(s);
                if(!addShip(s.getXcoordinate(),s.getYcoordinate(),s))
                {
                    allpassed = false;
                }
            }
        }
//        printBoard();
//        printShips();
        return allpassed;
    }

    private void addshiptoboard(Ship ship)
    {
        if(ship.getDirection().equals(Ship.HORIZONTAL))
        {
            for(int i = 0; i < ship.getSize(); i++)
            {
                gameBoard[ship.getYcoordinate()][ship.getXcoordinate()+i] = ship.getSize();
            }
        }
        else
        {
            for(int i = 0; i < ship.getSize(); i++)
            {
                gameBoard[ship.getYcoordinate()+i][ship.getXcoordinate()] = ship.getSize();
            }
        }
    }


    private boolean addShip(int xcoord, int ycoord, Ship s)
    {
        if(s.getDirection().equals(Ship.HORIZONTAL))
        {
            if(addShipHorizontal(s.getSize(), xcoord, ycoord))
            {
                return true;
            }
        }
        else
        {
            if(addShipVertical(s.getSize(), xcoord, ycoord))
            {
                return true;
            }
        }

        return false;
    }

    public int[][] getGameboardArray()
    {
        return gameBoard;
    }

    private boolean addShipHorizontal(int size, int xcoord, int ycoord)
    {
        boolean passed = true;
        for(int i = 0; i < size; i++)
        {
            if(checkHorizOutOfBound(xcoord, ycoord + i))
            {
                gameBoard[ycoord][xcoord + i] = size;
            }
            else {
                passed = false;
            }
        }
        return passed;
    }

    private boolean addShipVertical(int size, int xcoord, int ycoord)
    {
        boolean passed = true;
        for(int i = 0; i < size; i++)
        {
            if(checkVertOutOfBound(xcoord + i, ycoord))
            {
                gameBoard[ycoord + i][xcoord] = size;
            }
            else
            {
                passed = false;
            }
        }
        return passed;
    }

    private boolean checkVertOutOfBound(int xcoord, int ycoord)
    {
        if(ycoord >= 0 && ycoord < ysize && xcoord >= 0 && xcoord < xsize)
        {
            if(gameBoard[ycoord][xcoord] == 0)
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        else
        {
            return false;
        }
    }

    private boolean checkHorizOutOfBound(int xcoord, int ycoord)
    {
        if(ycoord >= 0 && ycoord < ysize && xcoord >= 0 && xcoord < xsize )
        {
            if(gameBoard[ycoord][xcoord] == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }


    protected void addHit(int x, int y)
    {
        gameBoard[y][x] = HIT;
    }

    protected void addMiss(int x, int y)
    {
        gameBoard[y][x] = MISS;
    }

}