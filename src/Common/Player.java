package Common;


import java.util.ArrayList;

/**
 * Joint class by Battleship group on 7/16/2015.
 *
 * Player class will be used to hold the gameboard for the player (server side) and to do basic battleship game functions
 * it will check to see if they hit a ship. will report of the ship has been sunk
 *
 */
public class Player extends User
{
    private ArrayList<Ship> ships;
    private GameboardArray hitMissBoard;
    private GameboardArray myboard;
    private boolean allShipsSunk;
    private int[] shipsize = new int[]{2,3,3,4,5};
    private String[] shiplabels = new String[]{"Destroyer","Submarine","Cruiser","Battleship","Aircraft Carrier"};
    private boolean[][] placementBoard;

    public Player(String userName)
    {
        super(userName);
        ships = new ArrayList<Ship>();
        createShips();
        hitMissBoard = new GameboardArray();
        allShipsSunk = false;
        myboard = new GameboardArray();
        placementBoard = new boolean[10][10];
    }

    public boolean addPlayerShips()
    {
        return myboard.addShips(ships);
    }

    public boolean allShipsSet()
    {
        boolean allset = true;
        for(Ship ship: ships)
        {
            if(!ship.isSet())
            {
                allset = false;
            }
        }
        return allset;
    }

    private void resetplacementboard()
    {
        for(int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                placementBoard[row][col] = false;
            }
        }
    }

    public void placeShips()
    {
        resetplacementboard();
        for(Ship ship : ships)
        {
            if(ship.isSet())
            {
//                System.out.println(ship.toString());
                addshiptoplacement(ship);
            }
        }
//        System.out.println("Drawing stored board");
//        showboard();
//        System.out.println();
    }

//    private void showboard()
//    {
//        for(int row = 0; row < 10; row++)
//        {
//            for(int col = 0; col < 10; col++)
//            {
//                System.out.print((placementBoard[row][col] == true) ? "T ":"F ");
//            }
//            System.out.println();
//        }
//    }

    private void addshiptoplacement(Ship ship)
    {
        int size = ship.getSize();
        while (size > 0) {
            int position = ship.getSize() - size;
            if (ship.getDirection().equals(Ship.VERTICAL)) {
                placementBoard[ship.getYcoordinate()+ position][ship.getXcoordinate()] = true;
            }
            else {
                placementBoard[ship.getYcoordinate()][ship.getXcoordinate()+ position] = true;
            }
            size--;
        }
    }

    public boolean[][] getPlacementBoard()
    {
        placeShips();
        return placementBoard;
    }

    public void resetShips()
    {
        for(Ship ship: ships)
        {
            ship.resetShip();
        }
    }

    public boolean addShip(Ship s)
    {
        if(myboard.checkShip(s))
        {
            myboard.addShips(ships);
            return true;
        }
        return false;
    }

    private void createShips()
    {
        ships.clear();
        for(int i = 0; i<5; i++)
        {
            ships.add(new Ship(shipsize[i],shiplabels[i]));
        }
    }

    public ArrayList<Ship> getShips()
    {
        return ships;
    }

    public Ship[] sendShips()
    {
        Ship[] s = new Ship[5];
        for(int i = 0; i < 5; i++)
        {
            s[i] = ships.get(i);
        }
        return s;
    }

    //check to see if player still has ships that have not been sunk
    public boolean playerAttackable()
    {
        if(allShipsSunk)
        {
            return false;
        }
        return true;
    }

    //check attack against player and store hit/miss in hitMissBoard
    //created to seperate responsibilities of functions.
    protected boolean attackPlayer(int x, int y)
    {
        if(checkAttack(x,y))
        {
            hitMissBoard.addHit(x,y);
            checkShips();
            return true;
        }
        hitMissBoard.addMiss(x, y);
        return false;
    }

    //verify that all ships have not been sunk
    private void checkShips()
    {
        allShipsSunk = true;
        for (Ship s : ships)
        {
            if(!s.hasBeenSunk())
            {
                allShipsSunk = false;
                return;
            }
        }
    }

    //check ship array to see if attack hits a ship.
    private boolean checkAttack(int x, int y)
    {
        if(!allShipsSunk)
        {
            for(Ship s : ships)
            {
                if(s.hitShip(x, y))
                {
                    return true;
                }
            }
        }
        return false;
    }
}