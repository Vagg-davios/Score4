import java.util.Scanner;

public class Players{
	Scanner s = new Scanner(System.in);
	
	Table Table = new Table();
	
	private String name;
	private String chip;
	
	//Getters and Setters, in order to access the private variables above
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setChip(String chip) {
		this.chip = chip;
	}
	
	public String getChip() {
		return chip;
	}
	
	public String getValidChip(Scanner s) {

		
	    String input = s.next();
	    
	    while (!"x".equalsIgnoreCase(input) && !"o".equalsIgnoreCase(input))  {
	        System.out.println("Please enter a valid chip symbol. ");
	        System.out.print("Choose between x and o: ");
	     
	        input = s.next();
	    }
	    return input;
	}
	

	
	// Choose the name, p is used for 1st and 2nd
	public void chooseName(String p) {
		System.out.print("Please enter the name of the " + p + " player: ");
		name = s.nextLine();
	}
	
	//Choose the Chip
	public void chooseChip(String name1, String name2) {
		System.out.print(name + ", please select your chip: ");
		
		chip = getValidChip(s);
	}
	
}
