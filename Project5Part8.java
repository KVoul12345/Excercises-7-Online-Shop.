package project5part8;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Project5Part8 {

	static String[] board;  // board boxes .
	static String turn; // X/O.
       
        
    public static void printBoard(){ // PRINT BOARD TABLE.
    System.out.println("/---|---|---\\"); // Top Layer.
    System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |"); // Side 1 Top.
    System.out.println("|-----------|"); // First middle line.
    System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |"); // Side 2 Middle.
    System.out.println("|-----------|"); // Second middle line.
    System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |"); // Side 3 Bot.
    System.out.println("/---|---|---\\"); // Bottom Layer.
	}
        
// METHOD CHECK WIN CONDITIONS:
    public static String checkWinner() { // Checks if a player has won
        for (int i=0; i<8; i++){
            String line = null;
            switch (i) {
                case 0:
                line = board[0] + board[1] + board[2]; // First line
                break;
                case 1:
                line = board[3] + board[4] + board[5]; // Second line.
                break;
                case 2: 
                line = board[6] + board[7] + board[8]; // Third line.
                break;
                case 3:
                line = board[0] + board[3] + board[6]; // First Row.
                break;
                case 4:
                line = board[1] + board[4] + board[7]; // Second Row.
                break;
                case 5:
                line = board[2] + board[5] + board[8]; // Third Row.
                break;
                case 6:
                line = board[0] + board[4] + board[8]; // Left to Right Corner.
                break;
                case 7:
                line = board[2] + board[4] + board[6]; // Right to left Corner.
                break;
            }
            if (line.equals("XXX")){// Win Condition check as X.
                return "X";
            } 
            else if (line.equals("OOO")){ // Win Condition check as O.
                return "O";
            }
        }
        for (int i = 0; i < 9; i++) { // Switch condition check.
            if (Arrays.asList(board).contains(String.valueOf(i+1))) {
                break;
            }
            else if (i == 8)  // Draw Condition.
                return "draw";
	}
	System.out.println( "Where to place " + turn + " in:"); // Ask for X/O Position.
        return null;
    }
    
// METHOD : Numbers on the board.    
    static void populateEmptyBoard() { // Sets the board with numbers at positions 1-9.
	for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a+1); 
	}
    }


// METHOD : MAIN
    public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    board = new String[9]; // Board Field Positions.
    turn = "X"; // X/O player move.
    String winner = null; // Win Condition.
    populateEmptyBoard(); // Fill the board.
    System.out.println("Lets play TicTacToe.");
    printBoard(); // Print Board.
    System.out.println("X's will play first. Enter a slot number to place X in:");
     
     while(winner == null) { // check if input is valid.
        int numInput;
	try {
            numInput = in.nextInt(); // Read Input.
            if(!(numInput > 0 && numInput <= 9)) { // 0<input<=9
                System.out.println("Invalid input; re-enter slot number:");
		continue;
            }
	}
        catch (InputMismatchException e) { // Exception Catch.
            System.out.println("Invalid input; re-enter slot number:");
            continue;
        }
        if(board[numInput-1].equals(String.valueOf(numInput))) { //Change board position with player mark.
            board[numInput-1] = turn; //  // Makes the change.
            if(turn.equals("X")) { // Change Player Turn.
		turn = "O";
            } 
            else{
		turn = "X"; // Change Player turn back.
		}
		printBoard(); // print Board.
                winner = checkWinner(); // Check if Win Conditions are met.
	} 
        else{
            System.out.println("Slot already taken; re-enter slot number:"); // Invalid position Message.
            continue;
            }
    }
    if (winner.equalsIgnoreCase("draw")) { // Draw check.
        System.out.println("Draw!");  // Draw message
	} 
        else {
            System.out.println(winner + "won!"); // Win Message.
	}
    }
}

