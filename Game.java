/*
 * Name : Alex Yang
 * PennKey : ayang015
 * Recitation : 210
 * 
 * Execution: java Game
 *
 * This class runs the 2048 game until the user reaches either a lose state 
 * or a win state 
 */

public class Game {
    public static void main(String[] args) {
        // set canvas size
        PennDraw.setCanvasSize(600, 600);
        PennDraw.picture(0.5, 0.5, "Grid.png", 600, 600);

        // set up initial grid configuration 
        Board grid = new Board();
        grid.insertRandom();
        grid.insertRandom();
        
        PennDraw.enableAnimation(30);
        while (true) {
            PennDraw.picture(0.5, 0.5, "Grid.png", 600, 600);
            // draw the board
            grid.drawBoard();
            // update the block number
            grid.updateBlockNumber();
            // check to see if 2048 is there (modifies winstate boolean)  
            grid.checkWin();
            
            // if the winstate has been achieved display the winstate
            if (grid.getWinState()) {
                PennDraw.disableAnimation();
                grid.displayWin();
                break;
            }
            
            // if the lose state has been achieved display the losestate
            if (grid.isFull()) {
                // canShift modifies the losestate boolean  
                grid.canShift();
                if (grid.getLoseState()) {
                    PennDraw.disableAnimation();
                    grid.displayLose();
                    break;
                }
            }
            // take in the input and update the positions of everything
            if (PennDraw.hasNextKeyTyped()) {
                grid.updatePosition(PennDraw.nextKeyTyped());
            }
            // advance animation
            PennDraw.advance();
        }
    }
}