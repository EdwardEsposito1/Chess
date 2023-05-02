package Piece;

import Chess66.chess;


public class Rook extends Piece {

	public boolean hasMoved = false;
	
	public Rook(String value, int color) {
		super(value, color);
	}
	
	public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
		int currRow = curr.charAt(1) - '0';
		int newRow = newpos.charAt(1) - '0';
		char currCol = curr.charAt(0);
		char newCol = newpos.charAt(0);
		
		if((currRow == newRow) || (curr.charAt(0) == newpos.charAt(0))) {
			if(!(clearPath(curr,newpos))) {
				//System.out.println("here");
				return false;
			}
				if(isOccupied(newpos) == true) {
					Piece newspot = chess.board.get(newpos);
					
					// same color piece so capture not permitted
					if(newspot.getColor() == color) {
						//System.out.println("here1");
						return false;
					}
				}
				// Rook has moved so now cannot be used for castling
				this.hasMoved = true;
				return true;
			
		}
		//System.out.println("here4");
		return false; 
	
	}
	
public boolean clearPath(String curr, String newpos) {
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
	
	public boolean isClearHelper(char o, int i) {
		String filerank = o + "" + i;
		
		if(chess.board.get(filerank).getvalue().equals("##") || chess.board.get(filerank).getvalue().equals("  ")) { 
			//System.out.println("Box is empty");
			return true;
		}
		
		return false;
	}
}
