package Common;

public class Main {

	public static void main(String[] args)
	{
		GameboardArrays game = new GameboardArrays();
		game.addShips(3, 4, 5, "horizontal");
		game.printBoard();
		game.addGuess(4, 5);
		game.addGuess(4, 6);
		game.addGuess(4, 8);
		game.printPublicBoard();
	}

}
