import java.util.Scanner;

public class Table {
	Scanner s = new Scanner(System.in);
	
	private int rows;
	private int columns;
	
	//Getters & setters
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//	A function that checks whether the input is an integer, 
	//   if not, the user is prompted to enter the value again
	public int getInt(Scanner s) {
		while (!s.hasNextInt()) {
			System.out.print("Incorrect input. ");
			System.out.print("Please enter an integer: ");
			s.next();
		}
		
		return s.nextInt();
	}
	
	// A function that checks whether the value input is within our
	//  number range (1 to number of columns)
	public int getValidInt(Scanner s, int columns) {

	    int input = getInt(s);
	    while (input < 0 || input > columns) {
	        System.out.print("Please enter a number between 1 and " + columns + ": ");
	     
	        input = getInt(s);
	    }
	    return input;
	}

	
	public void askForSize() {
		
		System.out.print("Please enter the number of rows: ");
		do
		{
			rows = s.nextInt();
			if(rows < 4 || rows >15)
			{
				System.out.print("Incorrect input. ");
				System.out.print("Please enter the number of rows: ");
			}
			
		}while(rows < 4 || rows > 15);
		
		System.out.print("Please enter the number of columns: ");
		do
		{
			columns = s.nextInt();
			if(columns < 4 || columns >15)
			{
				System.out.print("Incorrect input. ");
				System.out.print("Please enter the number of columns: ");
			}
			
		}while(columns < 4 || columns > 15);
	}	
	
	public String[][] fillTable(int rows, int columns, String[][] table) {
		
		//A loop that fills the table with "-", not very necessary but makes 
		// the game more visually pleasing
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				table[i][j] = "-";
			}
		}
		return table;
	}
	
	public void printTable(int rows,int columns, String[][] table) {
		
		int lines = 0;
		
		//A loop that prints the left lines to act as the left wall of the game
		for(int j=0; j<rows; j++) {
			System.out.println();
			System.out.print("|");
		//A loop inside a loop that prints the table elements spaced out and the right walls
		for (int i=0; i<columns; i++) {
			System.out.print(" " + table[j][i]);
		 }
		System.out.print(" |");
		}
		
		System.out.println();
		
		
		//This while loop essentially fills the bottom of the table and acts as a floor for the elements to sit on
		while(lines<(columns * 2)+3) {
			System.out.print("-");
			lines++;
		}
		
		
		// A line break and a space for the column numbers to match the columns
		//  stops working after 10 due to the numbers being 2 digits and taking
		//   2 spaces. Could fix by making the whole thing take up more space
		System.out.println();
		System.out.print(" ");				
		
		
		//Printing out the numbers of the columns
		for (int k=1; k<columns+1; k++) {
			System.out.print(" " + k);
		}
		
		System.out.print(" ");
		System.out.println();
	}
	
	public void printEmptyTable(int rows, int columns, String[][] table) {
		
		//fillTable fills the table with "-"
		fillTable(rows, columns, table);
		//Then printTable prints an empty table
		printTable(rows, columns, table);
	}
	
	public void playerTurn(String name, String table[][], String symbol,int stop[]) {
		
		System.out.print("\n" + name + ", your turn. Select column: ");
		//Rows-1 since the counter starts from 0 instead of 1
		int i=rows-1;
		int column = getValidInt(s,columns);
		boolean occupied = true;
			
		
		/*This is the loop to check if there's a symbol in a certain spot, if it is, it means that it's occupied,
		   therefore it checks above it and if that's occupied it checks the next, meanwhile every time one 
		    spot is occupied the stop table gets a +1, once the table stop reaches the number of the capacity of
		     the column, it stops the loop alerting the user that it's full, and that they should select another one. */
		while(occupied){
			
		    if(table[i][column-1].equals("-")){
		    	
		       table[i][column-1]= symbol;
		       stop[column-1]++;
		       occupied=false;
		    }
		    else if(stop[column-1]==rows) {
		    	
		        System.out.print("\n" + name + " column " + column + " is full. Select a different one: ");
		        column= getValidInt(s,columns);
		    }
		    // This reduces the i in order for the next element to be inserted above the current position
		    else
		       i--;
		}
		// A function that prints the table
		printTable(rows, columns, table);
			
		}	
	
	public boolean playerWin(String name, String table[][], int rows, int columns, String symbol) {
		
		// Horizontal win, basically adding +1 on the j because j=columns 
		for(int i =0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				if(table[i][j] == symbol && table[i][j+1] == symbol &&
					table[i][j+2] == symbol && table[i][j+3] == symbol) {
					System.out.println("\nGAME IS OVER. THE WINNER IS " + name);
					System.exit(0);
					return true;
				}	
			}
		}
		
		// Vertical win, same thing but adding to the i because i=rows
		for(int i =0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				if(table[i][j] == symbol && table[i+1][j] == symbol &&
				    table[i+2][j] == symbol && table[i+3][j] == symbol) {
					System.out.println("\nGAME IS OVER. THE WINNER IS " + name);
					System.exit(0);
					return true;
				}	
			}
		}
		
		// Diagonal starting from the bottom left and going up right, same thing as above but now you start from the +3 of the i,
		//  meaning you start from the bottom 
		for(int i =0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				if(table[i+3][j] == symbol && table[i+2][j+1] == symbol &&
				    table[i+1][j+2] == symbol && table[i][j+3] == symbol) {
					System.out.println("\nGAME IS OVER. THE WINNER IS " + name);
					System.exit(0);
					return true;
				}	
			}
		}
		
		// Diagonal starting from the bottom right and going up left, so same thing as above but now the increment is in both
		//  variables since you have to start from the right and head to the left.
		for(int i =0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				if(table[i+3][j+3] == symbol && table[i+2][j+2] == symbol &&
				    table[i+1][j+1] == symbol && table[i][j] == symbol) {
					System.out.println("\nGAME IS OVER. THE WINNER IS " + name);
					System.exit(0);
					return true;
				}	
			}
		}
		
		// Return false if no table slots match the symbols, meaning no winner yet
		return false;
	} 
	
	
}
