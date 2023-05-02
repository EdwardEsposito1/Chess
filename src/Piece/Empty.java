package Piece;

public class Empty extends Piece {
	/* 
	 * Constructor for Empty Spot
	 */
	public Empty(String value, int color) {
		super(value, color);
	}
	
	/*Piece LegalMove. Checks if move is legal for Empty Sqaure. Can not move empty spot
	 * @param curr 
	 * @param newpos 
	 * @param  color 
	 * @param enpass 
	 * @return Move is always false for empty spot
	 * 
	 */
	public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
		return false; 
	}

}




