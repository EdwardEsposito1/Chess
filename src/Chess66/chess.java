package Chess66;

import java.util.HashMap;

import Piece.*;
import java.util.*;

/*
 *
 * @author Edward Esposito
 *
 */

public class chess {
	public static HashMap<String, Piece> board = new HashMap<String, Piece>(70);
	public static boolean whiteInCheck = false;
	public static boolean blackInCheck = false;
	
/*Checks if a spot on the board is supposed to be a black spot. 
 * @param col the column in the board 
 * @param row the row in the board 
 * @return returns false if white spot and true otherwise(black spot)
 * 
 * 
 * 
 */
public static boolean BlackSpot(char col, int row) {
		
		if((row == 1 || row == 3 || row == 5 || row == 7) && (col == 'b' || col == 'd' || col == 'f' || col == 'h')) {
			return false;
		}
		else if((row == 2 || row == 4 || row == 6 || row == 8) && (col == 'a' || col == 'c' || col == 'e' || col == 'g')) {
			return false;
		}
		return true;
	}
	
/*Prints the first board. Takes no parameters. 
 * no return type. 
 * 
 * 
 */
	public static void initboard() {
		
		for(char col = 'a'; col <= 'h'; col++) {
			for(int row = 1; row <= 8; row++) {
				
				String spot = Character.toString(col) + Integer.toString(row);
				
				if(BlackSpot(col, row)) {
					board.put(spot, new Empty("##", 0));
				}
				else {
					board.put(spot, new Empty("  ", 1));
				}
				
				if(spot.equals("a1") || spot.equals("h1")) {
					board.put(spot, new Rook("wR", 1));
					
				} else if (spot.equals("b1")||spot.equals("g1")) {
					board.put(spot, new Knight("wN", 1));
					
				}else if(spot.equals("c1")||spot.equals("f1")) {
					board.put(spot, new Bishop("wB", 1));
					
				}else if(spot.equals("d1")) {
					board.put(spot, new Queen("wQ", 1));
					
				}else if(spot.equals("e1")) {
					board.put(spot, new King("wK", 1));
					
				}
				
				
				
				if(spot.equals("a8") || spot.equals("h8")) {
					board.put(spot, new Rook("bR", 0));
					
				} else if (spot.equals("b8")||spot.equals("g8")) {
					board.put(spot, new Knight("bN", 0));
					
				}else if(spot.equals("c8")||spot.equals("f8")) {
					board.put(spot, new Bishop("bB", 0));
					
				}else if(spot.equals("d8")) {
					board.put(spot, new Queen("bQ", 0));
					
				}else if(spot.equals("e8")) {
					board.put(spot, new King("bK", 0));
					
				}
				
				
				
				if(row == 7) {
					board.put(spot, new Pawn("bp", 0));
				}
				if(row==2) {
					board.put(spot, new Pawn("wp", 1));
				}
			}
		}
	}
	
/*Prints the board.
 * no return type. 
 * 
 * 
 */
	public static void printboard() {
		for(int num = 8; num >= 1; num--) {
			for(char alpha = 'a'; alpha <= 'h'; alpha++) {
				String filerank = Character.toString(alpha) + Integer.toString(num);
				String piece_at_index = board.get(filerank).getvalue();
				System.out.print(piece_at_index + " ");
			}
			System.out.println(num);
		}
		System.out.println(" a" + "  b" + "  c" + "  d" + "  e" + "  f" + "  g" + "  h");
		System.out.println();

	}
	
	public static void main(String[] args) { 
		initboard();
		printboard();
		String wholestr, oldPos, newPos, kingPos, pawn_promo;
		String enpassSpot = "0";
		
		Piece piece_oldPos, piece_newPos;
		char promopiece = 0;
		boolean whiteTurn = true;
		boolean blackTurn = false; 
		boolean isDraw = false;
		boolean enpass = false; 
		
		
		String[] inputstr_as_arr = new String[3];
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		
		
		
		while(true) {
			
			if (whiteTurn) {
				if(whiteInCheck) {
					System.out.println("white king in Check");
				}
				
			} else {
				if(blackInCheck) {
					System.out.println("black king in Check");
				}
			}
			
			
			if(whiteTurn ==true) {
				System.out.println("White's Move");
			}else {
				System.out.println("Black's Move");
			}
			wholestr = sc.nextLine();
		
			
			//Check for resign
			if(wholestr.equals("resign")) {
				win_by_resign(whiteTurn, blackTurn);
				break;
			}

			inputstr_as_arr = wholestr.split(" ");

			
			//Check for pawn promotion or draw
			if(inputstr_as_arr.length > 2) {
				pawn_promo = inputstr_as_arr[2];
				promopiece = pawn_promo.charAt(0);
				//System.out.println("Promo to: " + promopiece);
				
				//Check for draw 
				if(pawn_promo.equals("draw?")) {
					isDraw = true; 
					System.out.println("draw");
					break;
				}
				
			}
			

			oldPos = inputstr_as_arr[0];
			newPos = inputstr_as_arr[1];
		
			if(!(chess.board.containsKey(newPos)) || !(chess.board.containsKey(oldPos))) {
				System.out.println("Illegal Move Try Again");
				continue;
			}
		
		
			piece_oldPos = board.get(oldPos);
			piece_newPos = board.get(newPos);
			int color = piece_oldPos.getColor();
			int newRow = newPos.charAt(1) - '0';
			char currCol = oldPos.charAt(0);
			char newCol = newPos.charAt(0);
		
			if((newRow > 8) || (newRow <1)) {
				System.out.println("Illegal Move Try Again");
				continue;
			}
			
			if(!(chess.board.containsKey(newPos))) {
				System.out.println("Illegal Move Try Again");
				continue;
			}
		

		
		if((color == 0)&&(whiteTurn == true)){
			System.out.println("Illegal Move Try Againn");
			continue;
		} else if( (color == 1)&&(blackTurn == true) ){
			System.out.println("Illegal Move Try Again");
			continue;
		}
		
		
		//check if promotion is valid 
		
				
			
		if((color ==1 && newRow ==8) || (color == 0 && newRow ==1)){
					if(promopiece ==  0) {
						System.out.println("NO promo type");
						promopiece = 'q';
		}else {
						promopiece = Character.toLowerCase(promopiece);
						
		}
			System.out.println(promopiece);
					
				}else if(!(promopiece ==0)){
					System.out.println("Invalid Move Try Again");
					promopiece = 0;
					continue;
					
				}
			//Enpassant Logic 
		if(piece_oldPos instanceof Pawn) {
			//System.out.println("Pawn");
			if((enpass == true) && ((color == 1 && newRow ==6)|| (color == 0 && newRow ==3))&&(Math.abs(currCol-newCol)==1)) {
				//System.out.println("enpassant: " + enpass);
				if(!(newPos.equals(enpassSpot))) {
					System.out.println(newPos);
					System.out.println(enpassSpot);
					System.out.println("Invalid Move Try Again");
					continue;
				}
				if(piece_oldPos.LegalMove(oldPos, newPos, color, enpass)) {
					piece_oldPos.move(oldPos, newPos, promopiece, color);
					int row = newPos.charAt(1) -'0';
					int rowBelow = row-1;
					int rowAbove = row+1;
					char rowBel = (char)(rowBelow+'0');
					char rowAbv = (char)(rowAbove+'0');
					if(color ==1) {
						String enpassed = newPos.charAt(0) + "" + rowBel;
						if(chess.BlackSpot(newPos.charAt(0), rowBel)) {
							chess.board.put(enpassed, new Empty("##", 0));
						}else {
							chess.board.put(enpassed, new Empty("  ", 1));
						}
					}else {
						String enpassed = newPos.charAt(0) + "" + rowAbv;
						System.out.println(enpassed);
						if(chess.BlackSpot(newPos.charAt(0), rowBel)) {
							chess.board.put(enpassed, new Empty("##", 0));
						}else {
							chess.board.put(enpassed, new Empty("  ", 1));
						}
					}

					printboard();
					if(whiteTurn == true) {
						whiteTurn = false;
						blackTurn = true;
					}else {
						whiteTurn = true;
						blackTurn = false;
					}
				}
				enpass = false;
				continue;
				
			}else {
				System.out.println("last move enpassant: " + enpass);
				enpass = isEnpassant(oldPos, newPos, color);
				System.out.println("enpassant: " + enpass);
				if(enpass) {
					int row = newPos.charAt(1) -'0';
					if(color ==1) {
					
						char rowbel2 = (char)(3+'0');
						enpassSpot = newPos.charAt(0) + "" + rowbel2;
						System.out.println("Spot:" +  enpassSpot);
					}else {
						
						char rowabv2 = (char)(6+'0');
						enpassSpot = newPos.charAt(0) + "" + rowabv2;
						System.out.println("Spot:" +  enpassSpot);
					}
					
				}
			}
		//End of Enpassant Logic 
			
		}
		System.out.println("enpassant: " + enpass);
		//System.out.println(color);
		if (piece_oldPos.LegalMove(oldPos, newPos, color, enpass)== false) {
			System.out.println("Illegal Move Try Again");
			continue;
		}
		
		
		piece_oldPos.move(oldPos, newPos, promopiece, color);
		printboard();
		
		if (whiteInCheck) {
			if (seeIfInCheck("w")) {
				System.out.println("Checkmate");
	    		System.out.println("Black wins");
	    		System.exit(1);
			} else {
				whiteInCheck = false;
			}
		} 
		
		if (blackInCheck) {
			if (seeIfInCheck("b")) {
				System.out.println("Checkmate");
	    		System.out.println("White wins");
	    		System.exit(1);
			} else {
				blackInCheck = false;
			}
		}
		
		whiteInCheck = seeIfInCheck("w");
		blackInCheck = seeIfInCheck("b");
		
		if(whiteTurn == true) {
			whiteTurn = false;
			blackTurn = true;
		}else {
			whiteTurn = true;
			blackTurn = false;
		}

	}
		
	
	
		

}
	
/*Checks to see who the winner of the game is when a player resigns.
 * @param white boolean, true if white resigned on their turn
 * @param black  boolean, true if black resigned on their turn
 * no return type. prints winner
 * 
 * 
 */	
	public static void win_by_resign(boolean white, boolean black) {
		if(white == true) {
			System.out.println("Black Wins ");
		}else {
			System.out.println("White Wins");
		}
		
	}
	
/* A check for if a pawn moves itself to a position in which it can be captured Enpassant.
 * Will check for the right row and that the pawn has moved the initial 2 spaces 
 * @param curr, the pawn's current position before moving 
 * @param newpos, the pawns new position after moving 
 * @param color the pawns color, 0 for black 1 for white
 * @return whether enpassant is valid on the next players move
 * 
 * 
 */
	public static boolean isEnpassant(String curr, String newpos, int color) {
		int currRow = curr.charAt(1) - '0';
		int newRow = newpos.charAt(1) - '0';
		char currCol = curr.charAt(0);
		char newCol = newpos.charAt(0); 
		boolean isEnpass_valid = false;
		if(((color==1 && newRow ==4) || (color ==0 && newRow ==5)) && (Math.abs(newRow -currRow) == 2)) {
			isEnpass_valid = true;
			System.out.println("Can Enpass: " + isEnpass_valid);
			return true;
		}else {
			return false;
		}
	}
	
	
/** A check to see if the king is in check. 
 * Will check kings color and see whether that king is in check
 * @param colorValue the kings color 
 * @return whether or not the king is in check
 * 
 * 
 */
	public static boolean seeIfInCheck(String colorValue) {
		//System.out.println("Check for: " + colorValue);
		String kingPosition = null;
		
		if (colorValue.equalsIgnoreCase("w") == true) {
			// get the black kings location
			for(int num = 8; num >= 1; num--) {
				for(char alpha = 'a'; alpha <= 'h'; alpha++) {
					if(board.get(Character.toString(alpha) + Integer.toString(num)).getvalue() == "wK") {
						kingPosition = Character.toString(alpha) + Integer.toString(num);
						//System.out.println("White King at: " + kingPosition);
					}
				}
			}
			// see if any black piece can move there
			for(int num = 8; num >= 1; num--) {
				for(char alpha = 'a'; alpha <= 'h'; alpha++) {
					if(board.get(Character.toString(alpha) + Integer.toString(num)).getvalue().substring(0,1).equalsIgnoreCase("b")) {
						//System.out.println("Black piece: " + board.get(Character.toString(alpha) + Integer.toString(num)).getvalue()
						//		 + " at " + Character.toString(alpha) + Integer.toString(num));

						//check if black piece has valid move and clear path to white king
						if(board.get(Character.toString(alpha) + Integer.toString(num)).LegalMove(Character.toString(alpha) + Integer.toString(num), kingPosition, 0, false)) {

							//System.out.println("Black piece: " + board.get(Character.toString(alpha) + Integer.toString(num)).getvalue()
							//		 + " at " + Character.toString(alpha) + Integer.toString(num) + " has king in check");
												
							return true;
						}
												
					}

					
				}
			}
			
		} else {
			// get the white kings location 
			for(int num = 8; num >= 1; num--) {
				for(char alpha = 'a'; alpha <= 'h'; alpha++) {
					if(board.get(Character.toString(alpha) + Integer.toString(num)).getvalue() == "bK")  {
						kingPosition = Character.toString(alpha) + Integer.toString(num);
						//System.out.println("Black King at: " + kingPosition);
					}
				}
			}
			// see if any white piece can move there
			for(int num = 8; num >= 1; num--) {
				for(char alpha = 'a'; alpha <= 'h'; alpha++) {
					if(board.get(Character.toString(alpha) + Integer.toString(num)).getvalue().substring(0,1).equalsIgnoreCase("w")) {
						//System.out.println("White piece: " + board.get(Character.toString(alpha) + Integer.toString(num)).getvalue()
						//		 + " at " + Character.toString(alpha) + Integer.toString(num));

						//check if black piece has valid move and clear path to white king
						if(board.get(Character.toString(alpha) + Integer.toString(num)).LegalMove(Character.toString(alpha) + Integer.toString(num), kingPosition, 1, false)) {

							/*System.out.println("White piece: " + board.get(Character.toString(alpha) + Integer.toString(num)).getvalue()
									 + " at " + Character.toString(alpha) + Integer.toString(num) + " has king in check");
								*/
							return true;
						}
												
					}

					
				}
			}
		}

		
		return false;
	}
	
	

}




