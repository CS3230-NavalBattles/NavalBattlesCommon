package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

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
		gameBoard = new int[xsize][ysize];
		resetBoard();
		ships = new ArrayList<Ship>();
	}

	private void resetBoard()
	{
		for(int i = 0; i < xsize; i++)
		{
			for(int j = 0; j < ysize; j++)
			{
				gameBoard[i][j] = 0;
			}
		}
	}

	public boolean checkShip(Ship s)
	{
		boolean test = true;
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
			s.setShip();
		}
		return test;
	}


	public boolean addShips(ArrayList<Ship> shiparray)
	{
		resetBoard();
		ships.clear();
		boolean allpassed = true;
		for(Ship s: shiparray)
		{
			ships.add(s);
			if(s.isSet())
			{
				if(!addShip(s.getXcoordinate(),s.getYcoordinate(),s))
				{
					allpassed = false;
				}
			}
		}
		return allpassed;
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
			if(checkHorizOutOfBound(xcoord + i, ycoord))
			{
				gameBoard[xcoord + i][ycoord] = size;
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
			if(checkVertOutOfBound(xcoord, ycoord + 1))
			{
				gameBoard[xcoord][ycoord + i] = size;
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
		if(ycoord >= 0 && ycoord < ysize && xcoord >= 0 && xcoord <= xsize && gameBoard[xcoord][ycoord] == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean checkHorizOutOfBound(int xcoord, int ycoord)
	{
		if(ycoord >= 0 && ycoord < ysize && xcoord >= 0 && xcoord <= xsize && gameBoard[xcoord][ycoord] == 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	protected void addHit(int x, int y)
	{
		gameBoard[x][y] = HIT;
	}

	protected void addMiss(int x, int y)
	{
		gameBoard[x][y] = MISS;
	}

}