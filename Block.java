/*
 * Name : Alex Yang
 * PennKey : ayang015
 * Recitation : 210
 * 
 * Execution: N/A, as Block.java is an object class 
 *
 * This class creates the blocks to be used on the 2048 board
 */

public class Block {
    // real x and y positions for pennDraw
    private double xPos, yPos;
    // positions of the block on the board array 
    private int arrayRow, arrayCol;
    // actual numerical value of block
    private int blockValue;
    
    /*
     * Description: Constructor function for block
     * Input: integer arrayRow, integer arrayCol, integer block value
     * Output: Instance of block with declared fields
     */
    public Block(int arrayRow, int arrayCol, int value) {
        this.arrayRow = arrayRow;
        this.arrayCol = arrayCol;
        
        // calculate the initial x and y positions based off board array position
        this.xPos = 0.135 + arrayCol * 0.242;
        this.yPos = 0.861 - arrayRow * 0.242;
        
        this.blockValue = value;
    }
    
    /*
     * Description: draws a single blocks
     * Input: nothing
     * Output: nothing, but helps draw block
     */
    public void draw() {
        int value = blockValue;
        
        // determine color based on value 
        if (value == 2) {
            PennDraw.setPenColor(PennDraw.GRAY);
        }
        else if (value == 4) {
            PennDraw.setPenColor(PennDraw.DARK_GRAY);
        }
        else if (value == 8) {
            PennDraw.setPenColor(PennDraw.BOOK_LIGHT_BLUE);
        }
        else if (value == 16) {
            PennDraw.setPenColor(209, 127, 194);
        }
        else if (value == 32) {
            PennDraw.setPenColor(101, 165, 108);
        }
        else if (value == 64) {
            PennDraw.setPenColor(244, 201, 149);
        }
        else if (value == 128) {
            PennDraw.setPenColor(201, 124, 122);
        }
        else if (value == 256) {
            PennDraw.setPenColor(111, 144, 183);
        }
        else if (value == 512) {
            PennDraw.setPenColor(PennDraw.BLACK);
        }
        else if (value == 1024) {
            PennDraw.setPenColor(165, 121, 198);
        }
        else if (value == 2048) {
            PennDraw.setPenColor(237, 215, 144);
        }
        
        // updated grid x and y positions
        xPos = 0.135 + arrayCol * 0.242;
        yPos = 0.861 - arrayRow * 0.242;
        
        // draw the square
        PennDraw.filledSquare(xPos, yPos, 0.11);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.setFontSize(70);
        
        // set font sizes based on value
        if (value > 100) {
            PennDraw.setFontSize(60);
        }
        
        if (value > 1000) {
            PennDraw.setFontSize(45);
        }
        
        // this was added in because it is hard to see white text on yellow
        if (value == 2048) {
            PennDraw.setPenColor(PennDraw.BLACK);
        }
        
        // add in the number value to the drawing
        PennDraw.text(xPos, yPos, "" + value);
    }
    
    /*
     * Description: getter function for block value 
     * Input: nothing
     * Output: int block value
     */
    public int getBlockValue() {
        return blockValue;
    }
    
    /*
     * Description: setter function for array row
     * Input: int row value 
     * Output: nothing, but modifies arrayRow 
     */
    public void setRowValue(int input) {
        arrayRow = input;
    }
    
    /*
     * Description: setter function for array column
     * Input: int column value 
     * Output: nothing, but modifies arrayCol 
     */
    public void setColValue(int input) {
        arrayCol = input;
    }
}