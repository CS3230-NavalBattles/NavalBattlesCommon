package Common;

import java.util.ArrayList;

/**
 * Created by Clinton on 7/21/2015.
 *
 * Player class will be used to hold the gameboard for the player (server side) and to do basic battleship game functions
 * it will check to see if they hit a ship. will report of the ship has been sunk
 *
 */
public class Player extends User
{
    private ArrayList<Ship> ships;
    private GameboardArray hitMissBoard;
    private boolean allShipsSunk;
    private int[] shipsize = new int[]{2,3,3,4,5};
    private String[] shiplabels = new String[]{"Destroyer","Submarine","Cruiser","Battleship","Aircraft Carrier"};

    public Player(String userName)
    {
        super(userName);
        ships = new ArrayList<Ship>();
        createShips();
        hitMissBoard = new GameboardArray();
        allShipsSunk = false;
    }

    protected void addPlayerShips(ArrayList<Ship> shipArray)
    {
        ships = shipArray;
    }

    private void createShips()
    {

        ships.clear();
        for(int i = 0; i<5; i++)
        {
            ships.add(new Ship(shipsize[i],shiplabels[i]));
        }
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
        hitMissBoard.addMiss(x,y);
        checkShips();
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
