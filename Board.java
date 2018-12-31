/*
 * Name : Alex Yang
 * PennKey : ayang015
 * Recitation : 210
 * 
 * Execution: N/A, as Board.java is an object class 
 *
 * This class creates the 4x4 board for 2048, and contains the methods necessary
 * to shift tiles around the board and combine the tiles
 */

public class Board {
    // array of blocks keep track of block position
    private Block[][] array;
    // tracks how many moves player has made
    private int moveCounter;
    // tracks number of blocks currently on board
    private int blockNumber;
    // both loseState and winState start as false
    private boolean loseState;
    private boolean winState;
    
    /*
     * Description: Constructor function for board
     * Input: N/A
     * Output: Instance of board with declared fields
     */
    public Board() {
        this.moveCounter = 0;
        this.blockNumber = 0;
        array = new Block[4][4];
        this.loseState = false;
        this.winState = false;
    }
    
    /*
     * Description: updates board based on user input
     * Input: char input 
     * Output: nothing, but updates positions of blocks
     */
    public void updatePosition(char input) {
        // parallel arrays representing initial x and y position of all tiles
        int[] initialColPositions = blockColCoords();
        int[] initialRowPositions = blockRowCoords();
        
        // combine and shift blocks 
        combineChooser(input);
        shiftChooser(input);
        
        // parallel arrays representing final x and y position of all tiles
        int[] finalColPositions = blockColCoords();
        int[] finalRowPositions = blockRowCoords();
        
        // if final and initial positions don't change, do nothing
        if (compareCoords(initialColPositions, finalColPositions) && 
            compareCoords(initialRowPositions, finalRowPositions)) {
            return;
        }
        // if something has moved, update moveCounter and insert new block
        else if (!isFull()) {
            if (!loseState) {
                insertRandom();
            }
            moveTracker();
            updateBlockNumber();
        }
    }
    
    /*
     * Description: displays losestate screen
     * Input: nothing 
     * Output: nothing, but displays losestate and movenumber
     */
    public void displayLose() {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(50);
        PennDraw.text(0.5, 0.5, "You Sux!");
        PennDraw.text(0.5, 0.4, "You moved " + moveCounter + " times");
    }
    
    /*
     * Description: displays winstate screen
     * Input: nothing 
     * Output: nothing, but displays winstate and movenumber
     */
    public void displayWin() {
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setFontSize(50);
        PennDraw.text(0.5, 0.5, "You Win!");
        PennDraw.text(0.5, 0.4, "You moved " + moveCounter + " times");
    }
    
    /*
     * Description: checks to see if player has won
     * Input: nothing 
     * Output: nothing, but modifies winstate boolean if it is achieved 
     */
    public void checkWin() {
        // if there is a block with value 2048, then player has won
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] != null && array[i][j].getBlockValue() == 2048) {
                    winState = true;
                }
            }
        }
    }
    
    /*
     * Description: draws all blocks
     * Input: nothing 
     * Output: nothing, but draws all blocks 
     */
    public void drawBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] != null) {
                    array[i][j].draw();
                }
            }
        }
    }
    
    /*
     * Description: gets winstate boolean
     * Input: nothing 
     * Output: returns winstate boolean 
     */
    public boolean getWinState() {
        return winState;
    }
    
    /*
     * Description: gets losestate boolean
     * Input: nothing 
     * Output: returns losestate boolean 
     */
    public boolean getLoseState() {
        return loseState;
    }
    
    
    /*
     * Description: gets moveCounter
     * Input: nothing 
     * Output: returns int moveCounter
     */
    public int getMoveCounter() {
        return moveCounter;
    }
    
    /*
     * Description: determines if the grid is full
     * Input: nothing 
     * Output: boolean for if it is full or not
     */
    public boolean isFull() {
        if (blockNumber == 16) {
            return true;
        }
        else {
            return false;
        }
    }
    
   
    /*
     * Description: determines if the grid is full
     * Input: nothing 
     * Output: boolean for if it is full or not
     */
    public void moveTracker() {
        moveCounter += 1;
    }
    
   
    /*
     * Description: determines how many blocks are currently on the board
     * Input: nothing 
     * Output: nothing, but updates the blocknumber 
     */
    public void updateBlockNumber() {
        // accumulator variable keeps track of number of blocks
        int temp = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] != null) {
                    temp += 1;
                }
            }
        }
        blockNumber = temp;
    }
    
    /*
     * Description: determines if anything can be shifted to determine if player
     *              has lost
     * Input: nothing 
     * Output: nothing, but updates loseState if nothing can be shifted
     */
    public void canShift() {
        // accesses canShift in SupportingFunctions 
        if (SupportingFunctions.canShiftColumn(array) || 
            SupportingFunctions.canShiftRow(array)) {
            return;
        }
        // if nothing can be shifted, player has lost  
        else {
            loseState = true;
            return;
        }
    }
    
    /*
     * Description: determines which direction to shift 
     * Input: char input 
     * Output: nothing, but shifts in direction according to input
     */
    public void shiftChooser(char input) {
        // the for loops exist to shift for every row/column of the array
        if (input == 'w') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.shiftUpHelper(array, i);
            }
        }
        else if (input == 'a') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.shiftLeftHelper(array, i);
            }
        }
        else if (input == 'd') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.shiftRightHelper(array, i);
            }
        }
        else if (input == 's') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.shiftDownHelper(array, i);
            }
        }
    }
    
    /*
     * Description: determines which direction to combine in 
     * Input: char input 
     * Output: nothing, but combines in direction according to input
     */
    public void combineChooser(char input) {
        // the for loops exist to combine for every row/column of the array
        if (input == 'w') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.combineUpHelper(array, i);
            }
        }
        else if (input == 'a') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.combineLeftHelper(array, i);
            }
        }
        else if (input == 'd') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.combineRightHelper(array, i);
            }
        }
        else if (input == 's') {
            for (int i = 0; i < 4; i++) {
                SupportingFunctions.combineDownHelper(array, i);
            }
        }
    }

    /*
     * Description: public interface for inserting a new block randomly
     * Input: N/A
     * Output: nothing, but inserts a new block
     */
    public void insertRandom() {
        insertRandomFunction();
    }
    
    /*
     * Description: finds all unoccupied row positions on array 
     * Input: N/A
     * Output: int[] array with unoccupied row positions
     */
    private int[] randomRow() {
        // get number of empty spots on board and create array
        int emptySpots = emptySpots();
        int[] rowArray = new int[emptySpots];
        
        // insert row number into the array when the array spot is null
        int rowTracker = 0;
        for (int row = 0; row < 4; row++) {
            for (int column = 0;  column < 4; column++) {
                if (array[row][column] == null) {
                    rowArray[rowTracker] = row;
                    rowTracker += 1;
                }
            }
        }
        return rowArray;
    }
    
    /*
     * Description: finds all unoccupied column positions on array 
     * Input: N/A
     * Output: int[] array with unoccupied column positions
     */
    private int[] randomCol() {
        // get number of empty spots on board and create array
        int emptySpots = emptySpots();
        int[] colArray = new int[emptySpots];
        
        // insert column number into the array when the array spot is null
        int colTracker = 0;
        for (int row = 0; row < 4; row++) {
            for (int column = 0;  column < 4; column++) {
                if (array[row][column] == null) {
                    colArray[colTracker] = column;
                    colTracker += 1;
                }
            }
        }
        return colArray;
    }
    
    /*
     * Description: finds how many empty spots are on array
     * Input: nothing
     * Output: int number of all empty spots on board
     */
    private int emptySpots() {
        int emptySpots = 0;
        for (int row = 0; row < 4; row++) {
            for (int column = 0;  column < 4; column++) {
                if (array[row][column] == null) {
                    emptySpots += 1;
                }
            }
        }
        return emptySpots;
    }
    
    /*
     * Description: private function for inserting a random block onto the board
     * Input: nothing
     * Output: nothing, but inserts a random block
     */
    private void insertRandomFunction() {
        if (isFull()) {
            throw new RuntimeException("Grid is completely full");
        }
        
        int emptySpots = emptySpots();
        int arrayIndex = (int) (Math.random() * emptySpots);
        
        // parallel array representing the possible empty spaces 
        int[] rowArray = randomRow();
        int[] colArray = randomCol(); 
        
        // user math.random to choose a random empty space on the board
        int rowIndex = rowArray[arrayIndex];
        int colIndex = colArray[arrayIndex];
        
        // randomly choose either 2 or 4 for new block value
        int blockValue;
        if (Math.random() < 0.5) {
            blockValue = 2;
        }
        else {
            blockValue = 4;
        }
        
        // insert the new block into the array 
        array[rowIndex][colIndex] = new Block(rowIndex, colIndex, blockValue);
        blockNumber += 1;
    }
    
    
    /*
     * Description: puts the array/grid into string format
     * Input: nothing
     * Output: String that represents the grid 
     */
    public String toString() {
        String out = "[ ";
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (array[row][column] != null) {
                    out += "__" + array[row][column].getBlockValue() + ", ";
                }
                else {
                    out += "null, ";
                }
            }
            out += '\n';
        }
        out += "]";
        return out;
    }
    
    
    // These following functions help determine if something has moved 
    
    /*
     * Description: gets an array with column positions of all blocks
     * Input: nothing
     * Output: int[] array with column positions for every block
     */
    private int[] blockColCoords() {
        int[] colCoords = new int[blockNumber];
        int coordTracker = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] != null) {
                    colCoords[coordTracker] = j;
                    coordTracker += 1;
                }
            }
        }
        return colCoords;
    }
    
    /*
     * Description: gets an array with row positions of all blocks
     * Input: nothing
     * Output: int[] array with row positions for every block
     */
    private int[] blockRowCoords() {
        int[] rowCoords = new int[blockNumber];
        int coordTracker = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j] != null) {
                    rowCoords[coordTracker] = i;
                    coordTracker += 1;
                }
            }
        }
        return rowCoords;
    }
    
    /*
     * Description: compares 2 int arrays to determine if they are the same
     * Input: 2 int arrays of the same length
     * Output: if arrays are the same, then returns boolean true. If they 
     *         are different, returns false.
     */
    private static boolean compareCoords(int[] first, int[] last) {
        int length = first.length;
        boolean isSame = true;
        for (int i = 0; i < length; i++) {
            if (first[i] != last[i]) {
                isSame = false;
            }
        }
        return isSame;
    }
    
    // main function for testing grid methods
    public static void main(String[] args) {
        Board test = new Board();
        for (int i = 0; i < 16; i++) {
            test.insertRandom();
        }
        String out = test.toString();
        System.out.println(out);
        String outer = test.toString();
        // System.out.println(outer);
    }
}