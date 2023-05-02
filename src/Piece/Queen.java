package Piece;

import java.util.ArrayList;
import java.util.List;

import Chess66.chess;

public class Queen extends Piece {

	public Queen(String value, int color) {
		super(value, color);
		
	}
	
	public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
		
		int currRow = curr.charAt(1) - '0';
		int newRow = newpos.charAt(1) - '0';
		char currCol = curr.charAt(0);
		char newCol = newpos.charAt(0);
		
		if((currRow == newRow)|| (currCol ==newCol)|| (Math.abs(currCol-newCol) == Math.abs(currRow-newRow))){ 
			Piece newspot = chess.board.get(newpos);
			//Can't capture same color piece
			
			if(clearPath(curr,newpos)) {
			if((isOccupied(newpos)== true) &&(newspot.getColor() == color) ) {

					return false;
				
			}else {
				return true;
			}
		}
		}
		return false; 
		


	}
	
	public boolean clearPath(String curr, String newpos) {
		int currRow = curr.charAt(1) - '0';
		int newRow = newpos.charAt(1) - '0';
		char currCol = curr.charAt(0);
		char newCol = newpos.charAt(0);
		
		if((Math.abs(currCol - newCol)) == (Math.abs(currRow -newRow )) && !(curr.equals(newpos))) {
			return BishopclearPath(curr,newpos);
		}
		
		if((currRow == newRow)|| (currCol ==newCol)) {
			return RookclearPath(curr,newpos);
		}
		return false;
	}
	
	
	public boolean RookclearPath(String curr, String newpos) {
		int i;
		int currRow = curr.charAt(1) - '0';
		int newRow = newpos.charAt(1) - '0';
		if(curr.charAt(0) == newpos.charAt(0)) {
				if(currRow < newRow) { //going forward for white, backwards for black
					for (i = currRow+1 ; i < newRow ; i++) {
						System.out.println("1: "+ i);
						if(!(isClearHelper(curr.charAt(0), i))) {
							System.out.println("1: problem "+ i);
						return false;
						}
					}
				} else { //forward for black, backwards for white
					for (i = newRow+1 ; i < currRow ; i++) {
						if(!(isClearHelper(curr.charAt(0), i))) {
							System.out.println("2: "+ i);
						return false;
						}
					}
				}
		}else if (currRow == newRow) {
			char letter;
			char letteroldPos = curr.charAt(0);
			char letternewPos = newpos.charAt(0);
			
			if(letteroldPos < letternewPos) { //going right for white, left for black	
				for (letter = (char)(letteroldPos+1) ; letter < letternewPos ; letter++) {
					if(!(isClearHelper(letter, curr.charAt(1)-'0'))) {
						return false;
					}
				}
			}
			else { //going left for white, right for black
				for (letter = (char)(letternewPos+1) ; letter < letteroldPos ; letter++) {
					if(!(isClearHelper(letter, curr.charAt(1)-'0'))) {
						return false;
					}
				}
			}
		}
			
			return true;
		}
	
	
	public boolean BishopclearPath(String oldPos, String newPos) {
		List<String> boxes=getIndicesInBetween(oldPos, newPos);
		
		for (String index:boxes) {
			if(!(chess.board.get(index).getvalue().equals("##") || chess.board.get(index).getvalue().equals("  "))) //box is empty
				return false;
		
		}
		return true;
	}
	
	public static List<String> getIndicesInBetween(String oldPos, String newPos) {
		
		
		List<String> indicesList = new ArrayList<String>();
		
		int x1 = (int)(oldPos.charAt(0)); int x2 = (int)(newPos.charAt(0));
		int y1 = oldPos.charAt(1) - '0'; int y2 = newPos.charAt(1) - '0';
		
		int xGradient = Math.abs(x2 - x1)/(x2 - x1);
		int yGradient = Math.abs(y2 - y1)/(y2 - y1);
		
		for(int i = 1; i < Math.abs(x2 - x1); i++) {
			char nextX = (char)(x1 + i*xGradient);
			int nextY = y1 + i*yGradient;
			
			indicesList.add(Character.toString(nextX) + Integer.toString(nextY) + "");
		}
		
		return indicesList;
	}
	
	public boolean isClearHelper(char o, int i) {
		String filerank = o + "" + i;
		
		if(chess.board.get(filerank).getvalue().equals("##") || chess.board.get(filerank).getvalue().equals("  ")) { 
			//System.out.println("Box is empty");
			return true;
		}
		
		return false;
	}

	
	
}
