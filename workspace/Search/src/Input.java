import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input//SWITCH X AND Y BACK BEFORE YOU TURN IN
{
	String path;
	BufferedReader file;
	String line;
	int size;
	char [][] puzzle;
	List<String> words;
	public enum Direction {UP,DOWN,UPLEFT,UPRIGHT,LEFT,RIGHT,DOWNLEFT,DOWNRIGHT}
	
	
	public Input(String input){
			path = input;
			try{
				file = new BufferedReader(new FileReader(input));
				line = file.readLine();
				size = 0;
				pullPuzzle();
				words = pullWords();
				while(!words.isEmpty()){
					String nextW = words.remove(0);//go through all words
					
					
					for(int two = 0; two < size; two++){//go through the puzzle looking for proper starting points
						for(int one = 0; one < size; one++){
							if(puzzle[one][two] == nextW.charAt(0)){
	
								List<Possibles> possibles = getPossibles(nextW.toCharArray(), one, two);
								
								boolean found = false;
								for(Possibles p:possibles)
								{
									if(trackItDown(p) == true){
										found = true;
										break;
									}
								}
								if(found ==false){
									System.out.println("Could not find match for " + nextW);
								}
								
							}
						}
					}
				}				
				
				
			}
			catch(IOException e)
			{
				System.out.println("bad path");
			}
		
		}
	
	public boolean trackItDown(Possibles p){
		int xInc = 0, yInc = 0;
		int xCop = p.x, yCop = p.y;
		boolean toReturn = true;
		switch(p.dir) {
			case UP:
			case UPLEFT:
			case UPRIGHT:
			if(p.dir == Direction.UPRIGHT){
				xInc = 1;
			}
			else if(p.dir == Direction.UPLEFT){
				xInc = -1;
			}
			break;
			case RIGHT:
				xInc = 1;
				break;
			case LEFT:
				xInc = -1;
				break;
			case DOWN:
			case DOWNLEFT:
			case DOWNRIGHT:
			yInc = 1;
			if(p.dir == Direction.DOWNRIGHT){
				xInc = 1;
			}
			else if(p.dir == Direction.DOWNLEFT){
				xInc = -1;
			}
			break;
		}
		
		for(int i = 1; i < p.word.length(); i++){
			xCop += xInc;//increment or decrement
			yCop += yInc;//increment or decrement
			if(xCop < 0 || xCop >= size || yCop >= size || yCop < 0){
				toReturn = false;
				break;
			}
			if(xCop >= size || yCop >= size){
				toReturn = false;
				break;
			}
			else if(toReturn == true && p.word.charAt(i) != puzzle[xCop][yCop]){
				toReturn = false;
				break;
			}
			else if(i + 1 == p.word.length() && toReturn == true){
				System.out.println("Found " + p.word + " starting at " + p.x + ", " + p.y + " and ending at " + xCop + ", " + yCop);
				break;
			}
		}
		return toReturn;
	}

		
		
	
	
	public List<Possibles> getPossibles(char[] letters, int x, int y){
	
		char nextL = letters[1];
		List<Possibles> toReturn = new ArrayList<Possibles>();
		
		
		
		
			for(int b = y-1; Math.abs(b-y) <= 1; b++){
				for(int a = x-1; Math.abs(a-x) <= 1; a++){//left to right, b is down another row
					
					if(b >= 0 && a >= 0 && a < size && b < size){//if appropriate
					
						if(puzzle[a][b] == nextL && !((a == x) && (b == y))){
							String passBack = new String(letters);
							Possibles newPos = new Possibles(passBack, x, y);
							toReturn.add(newPos);
							int xdif = a-x;
							int ydif = b-y;
							switch(xdif){
								case -1://left
									switch(ydif){
									case -1://moved up
										newPos.setDirection(Direction.UPLEFT);
										break;
										
									case 0://neither up nor down
										newPos.setDirection(Direction.LEFT);
										break;
										
									case 1://moved down
										newPos.setDirection(Direction.DOWNLEFT);
										break;
										
									}
								break;
								
								
								case 0://neither left nor right
									switch(ydif){
									case -1://moved up
										newPos.setDirection(Direction.UP);
										break;
										
									case 0://neither up nor down
										break;
										
									case 1://moved down
										newPos.setDirection(Direction.DOWN);
										break;
										
									}
								break;
								
								
								case 1://right
									switch(ydif){
									case -1://moved up
										newPos.setDirection(Direction.UPRIGHT);
										break;
										
									case 0://neither up nor down
										newPos.setDirection(Direction.RIGHT);
										break;
										
									case 1://moved down
										newPos.setDirection(Direction.DOWNRIGHT);
										break;
									}
								break;
								
								
							}

							
						}
				}
			}
		}
			return toReturn;
	
	
	}

	public void pullPuzzle() throws IOException
	{	
		
		line = line.toUpperCase();
		char [] nextChar = line.toCharArray();
		size = nextChar.length;
		puzzle = new char [size][size];
		
		for(int b = 0; b < size; b++)
		{
			for(int a = 0; a < size; a++)
			{
				puzzle[a][b] = nextChar[a];
				System.out.print(puzzle[a][b]);
			}
			line = file.readLine();
			line = line.toUpperCase();
			nextChar = line.toCharArray();
			System.out.println("");
		}
	}
	
	
	public List<String> pullWords() throws IOException{
		List<String> toReturn = new LinkedList<String>(); 
		line = file.readLine();
		while(line.isEmpty()){
			line = file.readLine();
		}

		while(line != null && !(line.isEmpty())){
			toReturn.add(line);
			line = file.readLine();
		}
		return toReturn;
	}
	
	





}