package Common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Clinton on 7/16/2015.
 */
public class GameboardArray implements Serializable {
    private int[][] gameBoard;
    private int xsize = 10;
    private int ysize = 10;

    //class constants
    public static final int HIT = 1;
    public static final int MISS = 0;




    public GameboardArray()
    {
        gameBoard = new int[xsize][ysize];
        //initializeBoard(); //are we going to initialize to a set value or work with null?
    }
    
    public void initializeBoard()
    {
    	for(int row = 0; row < xsize; row++)
    	{
    		for(int col = 0; col < ysize; col++)
    		{
    			gameBoard[row][col] = 7;
    		}
    	}
    }
    
    public void printBoard()
    {
    	System.out.println("BATTLESHIP\n");
    	for(int row = 0; row < xsize; row++)
    	{
    		for(int col = 0; col < ysize; col++)
    		{
    			System.out.print(" " + gameBoard[row][col] + " ");
    		}
    		System.out.println();
    	}
    }

//    this can be all handled with the printBoard method.
//    public void printPublicBoard()
//    {
//    	System.out.println("BATTLESHIP\n");
//    	for(int row = 0; row < xsize; row++)
//    	{
//    		for(int col = 0; col < ysize; col++)
//    		{
//    			if(publicboard[row][col] != 7)
//    			{
//    				System.out.print(" " + publicboard[row][col] + " ");
//    			}
//    			else
//    			{
//    				System.out.print(" ~ ");
//    			}
//    		}
//    		System.out.println();
//    	}
//    }
    
    public void addShips(int size, int xcoord, int ycoord, String direction)
    {
    	if(direction.equals("horizontal"))
    	{
    		addShipHorizontal(size, xcoord, ycoord);
    	}
    	else
    	{
    		addShipVertical(size, xcoord, ycoord);
    	}
    }
    
    public void addShipHorizontal(int size, int xcoord, int ycoord)
    {
    	for(int i = 0; i < size; i++)
		{
    		if(checkOutOfBound(xcoord, ycoord))
    		{
    			gameBoard[xcoord][ycoord] = size;
    			ycoord++;
    		}
		}
    }
    
    public void addShipVertical(int size, int xcoord, int ycoord)
    {
    	for(int i = 0; i < size; i++)
		{
    		if(checkOutOfBound(xcoord, ycoord))
    		{
    			gameBoard[xcoord][ycoord] = size;
    			xcoord++;
    		}
		}
    }
    
    public boolean checkOutOfBound(int xcoord, int ycoord)
    {
    	if(ycoord >= 0 && ycoord < ysize && xcoord >= 0 && xcoord < xsize )
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    //Accessor Methods
    public int[][] getPublicboard()
    {
        int[][] viewableboard = gameBoard.clone();

        return viewableboard;
    }

	protected void addHit(int x, int y)
	{
		gameBoard[x][y] = HIT;
	}

	protected void addMiss(int x, int y)
	{
		gameBoard[x][y] = MISS;
	}

//	  Need to rethink the guessing portion for the gameboard.
//    public void addGuess(int x, int y)
//    {
//        //TODO: addGuess
//    	if(shipArray[x][y] != 0)
//    	{
//    		publicboard[x][y] = HIT;
//    	}
//    	else
//    	{
//    		publicboard[x][y] = MISS;
//    	}
//    }
}

