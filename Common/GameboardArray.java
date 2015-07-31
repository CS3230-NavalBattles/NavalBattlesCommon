package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Created by Clinton on 7/16/2015.
 */
public class GameboardArray implements Serializable {
    private int[][] shipArray;
    private int[][] publicboard;
    private int total_row = 10;
    private int total_col = 10;
    public final int HIT = 1;
    public final int MISS = -1;
    private HashMap<String, Integer> shipType = new HashMap<String, Integer>()
    {{
	     put("Aircraft Carrier", 5);
	     put("Battleship", 4);
	     put("Destroyer", 3);
	     put("Submarine", 3);
	     put("Boat", 2);
    }};
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
        shipArray = new int[total_row][total_col];
        publicboard = new int[total_row][total_col];
        initializeBoard();
    }
    
    public GameboardArray(ArrayList<Ship> addships)
    {
        //TODO: constructor
    }
    
    public void initializeBoard()
    {
    	for(int row = 0; row < total_row; row++){
    		for(int col = 0; col < total_col; col++){
    			shipArray[row][col] = 0;
    			publicboard[row][col] = 0;
    		}
    	}    	
    }
    
    public void resetIsSet(){
    	for (String ship : isShipSet.keySet()){
    		isShipSet.put(ship, false);
    	}
    }
    
    public void addShips(String ship, int row, int col, String direction)
    {
    	if(!isShipSet.get(ship)){
	    	if(direction.equals("Horizontal")){
	    		addShipHorizontal(ship, row, col);
	    	}
	    	else{
	    		addShipVertical(ship, row, col);
	    	}
    	}
    	else{
    		JOptionPane.showMessageDialog(null, ship + " is already set");
    	}
    }
    
    public void addShipHorizontal(String ship, int row, int col)
    {
    	if(checkAddingShipHorizontal(ship, row, col)){
	    	for(int i = 0; i < shipType.get(ship); i++){ 		
				shipArray[row][col] = shipType.get(ship);
				col++;	
			}
	    	isShipSet.put(ship, true);
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Error adding ship due to out of bound or overlapping another ship");
    	}
    }
    
    public void addShipVertical(String ship, int row, int col)
    {
    	if(checkAddingShipVertical(ship, row, col)){
    		for(int i = 0; i < shipType.get(ship); i++){
    			shipArray[row][col] = shipType.get(ship);
    			row++;
    		}
    		isShipSet.put(ship, true);
    	}
    	else{
    		JOptionPane.showMessageDialog(null, "Error adding ship due to out of bound or overlapping another ship");
    	}
    }
    
    public boolean checkAddingShipHorizontal(String ship, int row, int col)
    {	
    	for(int i = 0; i < shipType.get(ship); i++){
    		if((col >= 0 && col < total_col) && shipArray[row][col] == 0){			
    			col++;
    		}
    		else{
    			return false;
    		}
    	}
    	return true;
    }

	public boolean checkAddingShipVertical(String ship, int row, int col)
	{	
		for(int i = 0; i < shipType.get(ship); i++){
			if((row >= 0 && row < total_row) && shipArray[row][col] == 0){			
    			row++;
    		}
    		else{
    			return false;
    		}		
		}
		return true;
	}
    
    //Accessor Methods
    public int[][] getPublicboard()
    {
        int[][] viewableboard = publicboard;
        return viewableboard;
    }
    
    public int[][] getShipArray(){
    	return shipArray;
    }
    
    public void setShipArray(int[][] ships){
    	shipArray = ships;
    }

    public void addGuess(int row, int col)
    {
    	if(shipArray[row][col] > 0){
    		publicboard[row][col] = HIT;
    	}
    	else{
    		publicboard[row][col] = MISS;
    	}
    }
}