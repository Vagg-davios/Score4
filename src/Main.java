import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Random r = new Random();
		
		//Introduction to the game
		System.out.println("This is Score4");
	
		
		Players Player1 = new Players();
		Players Player2 = new Players();
		Table Table = new Table();
		
		
		String[][] table = new String[100][100];
		int stop[] = new int[100];
		int randomTurn;
		int counter=0;
		boolean winner0 = false;
		boolean winner1 = false;
		
		
		//This essentially initializes the table that will signal when the column is full (check line 114 on "Table.java")
		for(int i=0; i<100; i++)
			stop[i] = 0;
		
		
		//Lets the players choose their names
		Player1.chooseName("1st");
		Player2.chooseName("2nd");
		
		
		// Lets the first player choose the chip and the second one gets what's left
		Player1.chooseChip(Player1.getName(),Player2.getName());

			if(Player1.getChip().equalsIgnoreCase("x"))
				Player2.setChip("o");
			else if(Player1.getChip().equalsIgnoreCase("o"))
				Player2.setChip("x");
			else
				System.out.println("");
		
		
		//Gives the other player the chip that's left
		System.out.println(Player2.getName() + ", your chip is: " + Player2.getChip());
		
		
		//Self explanatory 
		Table.askForSize();
		
		
		//Print an empty table for the players to see where they can insert
		Table.printEmptyTable(Table.getRows(), Table.getColumns(), table);
		
		
		//A random int modded by 2 to determine if player 0 or player 1 plays 
		randomTurn = r.nextInt() % 2;
		do {
			if (randomTurn==0){
				Table.playerTurn(Player1.getName(),table, Player1.getChip(),stop);
				winner0 = Table.playerWin(Player1.getName(), table, Table.getRows(), Table.getColumns(), Player1.getChip());
			
				Table.playerTurn(Player2.getName(), table, Player2.getChip(),stop);
				winner1 = Table.playerWin(Player2.getName(), table, Table.getRows(), Table.getColumns(), Player2.getChip());
			
				counter+=2;
			}
			else {
				
				Table.playerTurn(Player2.getName(), table, Player2.getChip(),stop);
				winner1 = Table.playerWin(Player2.getName(), table, Table.getRows(), Table.getColumns(), Player2.getChip());
			
				Table.playerTurn(Player1.getName(),table, Player1.getChip(),stop);
				winner0 = Table.playerWin(Player1.getName(), table, Table.getRows(), Table.getColumns(), Player1.getChip());
			
				counter+=2;
			}
			
			/*If the counter equals the number of elements in the table,
			 * it means the table is full and no winner, therefore we have a draw*/
			if(counter==Table.getRows() * Table.getColumns()) System.out.print("\nGAME OVER. WE HAVE A DRAW.");
			
		}while(counter!=(Table.getRows() * Table.getColumns())); 
		
		
		//This basically states that if the playerWin function returns true for the first winner, player 1 is the winner
			if(winner0) System.out.println("GAME OVER. THE WINNER IS " + Player1.getName());
			
			
		//Same thing as above but now player 2 is the winner
			if(winner1) System.out.println("GAME OVER. THE WINNER IS " + Player2.getName());
		
	}
}
