package Piece;

import Chess66.chess;

public class King extends Piece {

	public boolean hasMoved = false;
	//public boolean performCastle = true;
	
	public King(String value, int color) {
		super(value, color);
	}
	public boolean LegalMove(String curr, String newpos, int color, boolean enpass) {
		int currRow = curr.charAt(1) - '0';
		int newRow = newpos.charAt(1) - '0';
		char currCol = curr.charAt(0);
		char newCol = newpos.charAt(0);
		Piece rookCurPos = null;
		
		// check for white king castlingå
		if ((!this.hasMoved) && (color == 1) && (!chess.whiteInCheck)) {
			// check for castling to the right
			if((newpos.equalsIgnoreCase("g1")) && (!isOccupied("f1")) && (!isOccupied("g1")) 
					&& (chess.board.get("h1").getvalue().equalsIgnoreCase("wR"))
					&& (!chess.board.get("h1").hasMoved)
			) {
				// perform castling move by moving Rook, King will move with normal move process
				this.hasMoved = true;
				rookCurPos = chess.board.get("h1");
				rookCurPos.move("h1", "f1", '0', 1);
				rookCurPos.hasMoved = true;
				return true;
			}
			
			// check for castling to the left
			if((newpos.equalsIgnoreCase("c1")) && (!isOccupied("d1")) && (!isOccupied("c1")) && (!isOccupied("b1")) 
					&& (chess.board.get("a1").getvalue().equalsIgnoreCase("wR"))
					&& (!chess.board.get("a1").hasMoved)
			) {
				// perform castling move by moving Rook, King will move with normal move process
				this.hasMoved = true;
				rookCurPos = chess.board.get("a1");
				rookCurPos.move("a1", "d1", '0', 1);
				rookCurPos.hasMoved = true;
				return true;
			}			
		}
		
		// check for black king castling
		if ((!this.hasMoved) && (color == 0) && (!chess.blackInCheck)) {
			// check for castling to the right
			if((newpos.equalsIgnoreCase("g8")) && (!isOccupied("f8")) && (!isOccupied("g8")) 
					&& (chess.board.get("h8").getvalue().equalsIgnoreCase("bR"))
					&& (!chess.board.get("h8").hasMoved)
			) {
				// perform castling move by moving Rook, King will move with normal move process
				this.hasMoved = true;
				rookCurPos = chess.board.get("h8");
				rookCurPos.move("h8", "f8", '0', 1);
				rookCurPos.hasMoved = true;
				return true;
			}
			
			// check for castling to the left
			if((newpos.equalsIgnoreCase("c8")) && (!isOccupied("d8")) && (!isOccupied("c8")) && (!isOccupied("b8")) 
					&& (chess.board.get("a8").getvalue().equalsIgnoreCase("bR"))
					&& (!chess.board.get("a8").hasMoved)
			) {
				// perform castling move by moving Rook, King will move with normal move process
				this.hasMoved = true;
				rookCurPos = chess.board.get("a8");
				rookCurPos.move("a8", "d8", '0', 1);
				rookCurPos.hasMoved = true;
				return true;
			}			
		}		
		
		if( (Math.abs(currCol-newCol)==0)&& (Math.abs(currRow-newRow)==1) || (Math.abs(currCol-newCol)==1)&& (Math.abs(currRow-newRow)==0)|| (Math.abs(currCol-newCol)==1)&& (Math.abs(currRow-newRow)==1) && ((Math.abs(currCol-newCol)) == (Math.abs(currRow-newRow)) ))  {
			Piece newspot = chess.board.get(newpos);
			
			// same color piece so capture not permitted
			if((isOccupied(newpos)== true) &&(newspot.getColor() == color) ) {
				return false;
			} else {
				// king has moved successfully so castling no longer permitted
				this.hasMoved = true;
				//kingCanCastle = false;
				return true;
			}
		}
		return false;
		
	}
	
	public boolean clearPath(String curr, String newpos) {
		return false;
	}
}
