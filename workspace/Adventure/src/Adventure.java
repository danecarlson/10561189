//Dane Carlson
//3250
//Program 1: Adventure
//All of the code used is my own

import java.util.Scanner;//create a scanner to read input

public class Adventure {
	
	public static void main(String[] args)
	{		
		
		Scanner in = new Scanner(System.in);
		String [] inventory = { "brass lantern", "rope", "rations", "staff"}; 
		char command = '0';
		int north = 0, east = 0;
		String input = "";
		String [] split;
		String direction = "";
		System.out.println("Go to church\u2713\nDo the laundry\u2713\nEat my lunch");
		
		while(command != 'q')//loop until user prompts for quit
		{
			input = in.nextLine();
			input = input.toLowerCase();
			command = input.charAt(0);//command will be known as first letter of input
			
			
			if(command == 'g' && input.contains(" "))
			{
				split = input.split(" ");
				direction = split[1];
				
				
				if(direction.equals("north"))
				{
					if(north > 0)
					{
						north--;
					}
					else
					{	
						System.out.println("You can't go any further north!");
					}
					System.out.println("Your coordinates are " + north + ", " + east);
					
				}
				else if(direction.equals("east"))
				{
					if(east < 4)
					{
						east++;
					}
					else
					{
						System.out.println("You can't go any further east!");
					}
					System.out.println("Your coordinates are " + north + ", " + east);
					
				}
				else if(direction.equals("west"))
				{
					if(east > 0)
					{
						east--;
					}
					else
					{
						System.out.println("You can't go any further west!");
					}
					System.out.println("Your coordinates are " + north + ", " + east);
					
				}
				
				else if(direction.equals("south"))
				{
					if(north < 4)
					{
						north++;
					}
					else
					{
						System.out.println("You can't go any further south!");
					}
					System.out.println("Your coordinates are " + north + ", " + east);
					
				}
				else
				{
					System.out.println("Cannot go " + direction);
					System.out.println("Your coordinates are " + north + ", " + east);
					
				}
				
			}
			
			
			else if(command == 'i')
			{
				System.out.println("You are carrying:");
				for(String inv : inventory)
				{
					System.out.println(inv);
				}

				System.out.println("\nYour coordinates are " + north + ", " + east);
			}
			else if(command == 'q')
			{
				in.close();
				System.out.println("\nYour final coordinates are " + north + ", " + east + "\nDane Carlson\nProject 1\n3250");
			}
			else
			{
				System.out.println("Invalid command: " + input);
				System.out.println("\nYour coordinates are " + north + ", " + east);
			}
		}
		
	
	}
	

}

