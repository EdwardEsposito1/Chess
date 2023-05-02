package Piece;

import Chess66.chess;

public class Pawn extends Piece {

/*
 * Constructor for pawn piece
 */

	public Pawn(String value, int color) {
		super(value, color);
		
	}
	/*Piece LegalMove. Checks if move is legal for pawn. 
	 * @param curr pawn current position before moving
	 * @param newpos position that pawn is moving to 
	 * @param  color color of pawn
	 * @param enpass specifically for pawn, check if enpass is valid, 
	 * @return if the move is legal 
	 * 
	 */
public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
	int currRow = curr.charAt(1) - '0';
	int newRow = newpos.charAt(1) - '0';
	char currCol = curr.charAt(0);
	char newCol = newpos.charAt(0);
	
	//Can not move backwards or stay still
	if(((newRow - currRow) <= 0) && (color == 1)) {
		return false;
	}else if(((currRow - newRow) <= 0) && (color == 0)) {
		return false; 
	}
	
	// can not move two steps besides first move 
	if((Math.abs(newRow - currRow) == 2) &&(color ==1)) {
		if(currRow != 2) {
			return false;
		}else if(!(clearPath(curr,newpos))) {
			return false;
		}
	} else if((Math.abs(newRow - currRow) == 2) &&(color ==0)) {
		
		if(currRow != 7) {
			return false;
		}else if(!(clearPath(curr,newpos))) {
			return false;
		}
	}
	
	if((Math.abs(newRow - currRow) >2)) {
		return false;
	}
	
	
	if(currCol != newCol) {
		if((Math.abs(newCol-currCol) == 1) && (enpass)) {
			return true;
		}
		if((isOccupied(newpos)==false) || (Math.abs((newCol-currCol))!=1)) {
			System.out.println("here 25");
			
			return false;
		}
		
	}
	
	//cannot move straight if something is in the way 
	if((newCol == currCol) && (isOccupied(newpos))) {
		return false;
	}
	
	
	
	
	return true;
}

/*Override specifically because of pawn promotion. 
 * @param curr pawn's current position 
 * @param newpos  pawn's new position 
 * @param promopiece pawns specified promopiece, checked prior to move being called
 * @param color  Pawn's color  
 * 
 */
public void move(String curr, String newpos, char promopiece, int color) {
	int newRow = newpos.charAt(1) - '0';
	System.out.println(color);
	System.out.println(newRow);
	
	
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
	
	if(!(promopiece==0)) {
		promotion(newpos,color, promopiece);
	}
	
}

/*Promotes the pawn to the specified piece. If none specified automatically promotes to Queen
 * @param newpos pawns new position
 * @param color the color of the pawn
 * @param promopiece the specified promotion piece for the pawn. 
 */

public void promotion(String newPos, int color, char promopiece) {
	Piece promoted = null;
	
	if(color ==1) {
		if(promopiece == 'r') {
			promoted = new Rook("wR",1);
		}else if(promopiece == 'n') {
			promoted = new Knight("wN",1);
		}else if(promopiece == 'b') {
			promoted = new Bishop("wB",1);
		}else if(promopiece == 'q') {
			promoted = new Queen("wQ",1);
		}
	}else if(color ==0) {
		if(promopiece == 'R') {
			promoted = new Rook("bR",0);
		}else if(promopiece == 'n') {
			promoted = new Knight("bK",0);
		}else if(promopiece == 'b') {
			promoted = new Bishop("bB",0);
		}else if(promopiece == 'q') {
			promoted = new Queen("bQ",0);
		}
	}
	chess.board.put(newPos, promoted);
}

/*Checks to see if path is clear for movement
 * 	@param curr the pawn's current position 
 * @param newpos the pawn's new position 
 * @return if path is clear
 */
public boolean clearPath(String curr, String newpos) {
	int i;
	int currRow = curr.charAt(1) - '0';
	int newRow = newpos.charAt(1) - '0';
	
	if(currRow < newRow) { //going forward for white
		for (i = currRow+1 ; i <= newRow ; i++) {
			if(!(isClearHelper(curr.charAt(0), i))) {
				return false;
			}
		}
	} else { //forward for black
		for (i = newRow ; i < currRow ; i++) {
			if(!(isClearHelper(curr.charAt(0), i))) {
				return false;
			}
		}
	}
	
	return true;
}
/*Helper for clearPath 
 * 	@param curr the piece's current position 
 * @param newpos the piece's new position 
 * @return if spot is empty
 */
public boolean isClearHelper(char o, int i) {
	String str = o + "" + i;
	
	if(chess.board.get(str).getvalue().equals("##") || chess.board.get(str).getvalue().equals("  ")) { 
		//System.out.println("Box is empty");
		return true;
	}
	
	return false;
}



}
