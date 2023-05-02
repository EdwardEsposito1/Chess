package Piece;

import Chess66.chess;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
/*
 * Constructor for Bishop Class
 */
	public Bishop(String value, int color) {
		super(value, color);
		
	}
/*Overwrite of Piece LegalMove. Checks if move is legal for piece 
 * @param curr piece's current position before moving
 * @param newpos position that piece is moving to 
 * @param  color color of piece
 * @param enpass specificly for pawn, check if enpass is valid, ignored by other pieces 
 * @return if the move is legal 
 * 
 */
	public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
		if((Math.abs(curr.charAt(0) - newpos.charAt(0)) == Math.abs (curr.charAt(1) - newpos.charAt(1))) && !(curr.equals(newpos))) {
			
			Piece newspot = chess.board.get(newpos);
			//Can't capture same color piece
			
			if(!(clearPath(curr,newpos))) {
				//System.out.println("here");
				return false;
			}
			if((isOccupied(newpos)== true) &&(newspot.getColor() == color) ) {
				

					return false;
				
			}else{
					return true;
				}
			}
		
		return false;
	}
	
	/*Checks to see if path is clear for movement
	 * 	@param curr the Bishop's current position 
	 * @param newpos the Bishop's new position 
	 */
	public boolean clearPath(String curr, String newpos) {
		List<String> validspots=validSpots(curr, newpos);
		
		for (String index:validspots) {
			if((isOccupied(index))) { 
				return false;
			}
		}
		return true;
	}
	
	public static List<String> validSpots(String curr, String newpos) {
		
		
		List<String> indicesList = new ArrayList<String>();
		
		int x1 = (int)(curr.charAt(0)); int x2 = (int)(newpos.charAt(0));
		int y1 = curr.charAt(1) - '0'; int y2 = newpos.charAt(1) - '0';
		
		int xGradient = Math.abs(x2 - x1)/(x2 - x1);
		int yGradient = Math.abs(y2 - y1)/(y2 - y1);
		
		for(int i = 1; i < Math.abs(x2 - x1); i++) {
			char nextX = (char)(x1 + i*xGradient);
			int nextY = y1 + i*yGradient;
			
			indicesList.add(Character.toString(nextX) + Integer.toString(nextY) + "");
		}
		
		return indicesList;
	}
	
}

