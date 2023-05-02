package Piece;

import Chess66.*;


public class Piece {
	String value;
	private int color;
	// 0 for black, 1 for white
	boolean hasMoved;
	public Piece(String value, int color) {
		this.value = value;
		this.color = color;
		this.hasMoved = false;
	}
	
	public String getvalue() {
		return this.value;
	}
	
	/*Piece LegalMove. Checks if move is legal for piece. Overwritten by subclasses
	 * @param curr piece's current position before moving
	 * @param newpos position that piece is moving to 
	 * @param  color color of piece
	 * @param enpass specifically for pawn, check if enpass is valid, ignored by other pieces 
	 * @return if the move is legal 
	 * 
	 */
	public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
		return true; 
	}
	
	/* returns piece color. Inherited by subclasses
	 * @return color of piece
	 * 
	 * 
	 */
	public int getColor() {
		return this.color;
	}
	
	/*Piece Move. If this is called it is known that the move is legal. Moves piece.
	 * @param curr piece's current position before moving
	 * @param newpos position that piece is moving to 
	 * @param  color color of piece
	 * @param promopiece specificly for pawn, check if promotion is valid, ignored by other pieces 
	 * 
	 * 
	 */
	public void move(String curr, String newpos, char promopiece, int color) {
		Piece piece_curr = chess.board.get(curr);
		
		//move piece to newPos
		chess.board.put(newpos, piece_curr);
		
		//make oldPos an empty box
		if(chess.BlackSpot(curr.charAt(0), curr.charAt(1)-'0')) {
			chess.board.put(curr, new Empty("##", 0));
		}
		else {
			chess.board.put(curr, new Empty("  ", 1));
		}
	}
	
	
/* checks to see if new position that piece will move to is occupied
 * @param newpos the new position of the piece 
 * @return if the spot is not empty
 */
		public boolean isOccupied(String newpos) {
			Piece newspot = chess.board.get(newpos);
			if(newspot instanceof Empty) {
				return false;
			}
			return true; 
		}
		
	/*Checks to see if path is clear for movement
	 * 	@param curr the piece's current position 
	 * @param newpos the piece's new position 
	 */
		public boolean clearPath(String curr, String newpos) {
			return true;
		}
		
}


