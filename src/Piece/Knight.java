package Piece;

import Chess66.chess;
//commit check 
public class Knight extends Piece {

	public Knight(String value, int color) {
		super(value, color);
		// TODO Auto-generated constructor stub
	}
	
	public boolean clearPath(String curr, String newpos) {
		return true;
	}
	
	public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
		int currRow = curr.charAt(1) - '0';
		int newRow = newpos.charAt(1) - '0';
		char currCol = curr.charAt(0);
		char newCol = newpos.charAt(0);
		int deltaX;
		int deltaY;
		
		deltaX = Math.abs(newRow-currRow);
		deltaY = Math.abs(newCol-currCol);
		
		if(!(isOccupied(newpos))) {

		if (deltaX == 2 && deltaY == 1){
			return true;
		} else if (deltaX == 1 && deltaY == 2) {
			return true;
		}
	}	
		
		if(isOccupied(newpos)) {
			Piece newspot = chess.board.get(newpos);
			if(newspot.getColor()==color) {
				return false;
			}else if(deltaX == 2 && deltaY == 1){
				return true;
			} else if (deltaX == 1 && deltaY == 2) {
				return true;
			}
		}
		return false;
	}
		
		
		/*if(isOccupied(newpos)) {
			Piece newspot = chess.board.get(newpos);
			if(newspot.getColor()==color) {
				return false;
			}else {
				return true;
			}
		}
		
		return false;
	}*/

}
