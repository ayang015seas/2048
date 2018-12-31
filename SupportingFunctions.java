/*
 * Name : Alex Yang
 * PennKey : ayang015
 * Recitation : 210
 * 
 * Execution: N/A, as this is a library of static methods
 *
 * This library of static functions is useful for shifting, combining, and 
 * checking the Block[][] array in Board.java
 * 
 * The reason this library exists is to further promote encapsulation and 
 * make sure that the Board.java function doesn't become too long 
 */

public class SupportingFunctions {
    /*
     * Description: these four following functions provide public 
     *              interfaces for private shift functions in all 4 directions
     * Input: Block[][] array, int row
     * Output: nothing, but results in a row being shifted in designated direction
     */
    
    public static void shiftLeftHelper(Block[][] array, int row) {
        shiftLeftHelperFunction(array, row);
    }
    public static void shiftRightHelper(Block[][] array, int row) {
        shiftRightHelperFunction(array, row);
    }
    public static void shiftUpHelper(Block[][] array, int row) {
        shiftUpHelperFunction(array, row);
    }
    public static void shiftDownHelper(Block[][] array, int row) {
        shiftDownHelperFunction(array, row);
    }
    
    /*
     * Description: these four following functions provide public 
     *              interfaces for private combine functions in all 4 directions
     * Input: Block[][] array, int row
     * Output: nothing, but results in a row being combined in designated direction
     */
    
    public static void combineLeftHelper(Block[][] array, int row) {
        combineLeftHelperFunction(array, row);
    }
    public static void combineRightHelper(Block[][] array, int row) {
        combineRightHelperFunction(array, row);
    }
    public static void combineUpHelper(Block[][] array, int row) {
        combineUpHelperFunction(array, row);
    }
    public static void combineDownHelper(Block[][] array, int row) {
        combineDownHelperFunction(array, row);
    }
    
    
    // privately encapsulated functions for shifting
    
    /*
     * Description: private encapsulated function for shifting left
     * Input: Block[][] array, int row
     * Output: nothing, but results in a row being shifted left
     */
    private static void shiftLeftHelperFunction(Block[][] array, int row) {
        for (int i = 0; i < 3; i++) {
            // count empty spaces 
            int counter = 0;
            for (int j = i; j < 3; j++) {
                if (array[row][j] == null) {
                    counter += 1; 
                }
                else {
                    break;
                }
            }
            // 
            if (counter > 0) {
                // shift it by number of empty spaces
                array[row][i] = array[row][counter + i];
                if (array[row][i] != null) {
                    // update the row and col attributes of the block
                    array[row][i].setRowValue(row);
                    array[row][i].setColValue(i);
                }
                // make sure old grid reference is null
                array[row][counter + i] = null;
            }
        }
    }
    
    /*
     * Description: private encapsulated function for shifting right
     * Input: Block[][] array, int row
     * Output: nothing, but results in a row being shifted right
     */
    
    private static void shiftRightHelperFunction(Block[][] array, int row) {
        for (int i = 3; i > 0; i--) {
            int counter = 0;
            // count empty spaces 
            for (int j = i; j > 0; j--) {
                if (array[row][j] == null) {
                    counter += 1; 
                }
                else {
                    break;
                }
            }
            
            if (counter > 0) {
                array[row][i] = array[row][i - counter];
                // shift it by number of empty spaces
                if (array[row][i] != null) {
                    // update the row and col attributes of the block
                    array[row][i].setRowValue(row);
                    array[row][i].setColValue(i);
                }
                // make sure old grid reference is null
                array[row][i - counter] = null;
            }
        }
    }
    
    /*
     * Description: private encapsulated function for shifting up
     * Input: Block[][] array, int column
     * Output: nothing, but results in a column being shifted up
     */
    private static void shiftUpHelperFunction(Block[][] array, int col) {
        for (int i = 0; i < 3; i++) {
            int counter = 0;
            // count empty spaces
            for (int j = i; j < 3; j++) {
                if (array[j][col] == null) {
                    counter += 1; 
                }
                else {
                    break;
                }
            }
            
            if (counter > 0) {
                // shift it by number of empty spaces
                array[i][col] = array[i + counter][col];
                if (array[i][col] != null) {
                    // update the row and col attributes of the block
                    array[i][col].setRowValue(i);
                    array[i][col].setColValue(col);
                }
                // make sure old grid reference is null
                array[i + counter][col] = null;
            }
        }
    }
    /*
     * Description: private encapsulated function for shifting down
     * Input: Block[][] array, int column
     * Output: nothing, but results in a column being shifted down
     */
    private static void shiftDownHelperFunction(Block[][] array, int col) {
        for (int i = 3; i > 0; i--) {
            int counter = 0;
            // count empty spaces
            for (int j = i; j > 0; j--) {
                if (array[j][col] == null) {
                    counter += 1; 
                }
                else {
                    break;
                }
            }
            
            if (counter > 0) {
                array[i][col] = array[i - counter][col];
                // shift it by number of empty spaces
                if (array[i][col] != null) {
                    // update the row and col attributes of the block
                    array[i][col].setRowValue(i);
                    array[i][col].setColValue(col);
                }
                // make sure old grid reference is null
                array[i - counter][col] = null;
            }
        }
    }

    // privately encapsulated combine functions
    
    /*
     * Description: private encapsulated function for combining left
     * Input: Block[][] array, int row
     * Output: nothing, but results in a row being combined left
     */
    private static void combineLeftHelperFunction(Block[][] array, int row) {
        // shift everything to make handling easier
        SupportingFunctions.shiftLeftHelper(array, row);
        
        // if theres no blocks or 1 block, do nothing   
        int num = blocksInRow(array, row);
        if (num == 0 || num == 1) {
            return;
        }
        
        // iterate through and combine if it is the same
        for (int i = 0; i < num; i++) {
            if (i == 3) {
                return;
            }
            if (array[row][i + 1] == null) {
                return;
            }
            else if (!isSame(array[row][i], array[row][i + 1])) {
                continue;
            }
            else if (isSame(array[row][i], array[row][i + 1])) {
                // get new block value
                int temp = array[row][i].getBlockValue() + 
                    array[row][i + 1].getBlockValue();
                // create new block
                Block combined = new Block(row, i, temp);
                // delete both old blocks
                array[row][i] = combined;
                array[row][i + 1] = null;
                i = i + 1;
            }
        }
    }
    
    /*
     * Description: private encapsulated function for combining right
     * Input: Block[][] array, int row
     * Output: nothing, but results in a row being combined right
     */
    private static void combineRightHelperFunction(Block[][] array, int row) {
        shiftRightHelper(array, row);
        // shift everything to make handling easier
        
        // if theres no blocks or 1 block, do nothing   
        int num = blocksInRow(array, row);
        if (num == 0 || num == 1) {
            return;
        }
        
        // iterate through and combine if it is the same
        for (int i = 3; i > i - num; i--) {
            if (i == 0 || i == -1) {
                return;
            }
            if (array[row][i] == null || array[row][i - 1] == null) {
                return;
            }
            else if (!isSame(array[row][i], array[row][i - 1])) {
                continue;
            }
            else if (isSame(array[row][i], array[row][i - 1])) {
                // get new block value
                int temp = array[row][i].getBlockValue() + 
                    array[row][i - 1].getBlockValue();
                // create new block
                Block combined = new Block(row, i, temp);
                // delete both old blocks
                array[row][i] = combined;
                array[row][i - 1] = null;
                i = i - 1;
            }
        }
    }
    
    /*
     * Description: private encapsulated function for combining up
     * Input: Block[][] array, int column
     * Output: nothing, but results in a column being combined up
     */
    private static void combineUpHelperFunction(Block[][] array, int col) {
        // shift everything to make handling easier
        shiftUpHelper(array, col);
        
        // if theres no blocks or 1 block, do nothing   
        int num = blocksInCol(array, col);
        if (num == 0 || num == 1) {
            return;
        }
        // iterate through and combine if it is the same
        for (int i = 0; i < num; i++) {
            if (i == 3) {
                return;
            }
            if (array[i + 1][col] == null) {
                return;
            }
            else if (!isSame(array[i][col], array[i + 1][col])) {
                continue;
            }
            else if (isSame(array[i][col], array[i + 1][col])) {
                // get new block value
                int temp = array[i][col].getBlockValue() + 
                    array[i + 1][col].getBlockValue();
                // create new block
                Block combined = new Block(i, col, temp);
                // delete old blocks
                array[i][col] = combined;
                array[i + 1][col] = null;
                i = i + 1;
            }
        }
    }
    /*
     * Description: private encapsulated function for combining down
     * Input: Block[][] array, int column
     * Output: nothing, but results in a column being combined down
     */
    private static void combineDownHelperFunction(Block[][] array, int col) {
        // shift everything to make handling easier
        shiftDownHelper(array, col);
        
        // if theres no blocks or 1 block, do nothing   
        int num = blocksInCol(array, col);
        if (num == 0 || num == 1) {
            return;
        }
        
        // iterate through and combine if it is the same
        for (int i = 3; i > i - num; i--) {
            if (i == 0 || i == -1) {
                return;
            }
            
            if (array[i - 1][col] == null) {
                return;
            }
            else if (!isSame(array[i][col], array[i - 1][col])) {
                continue;
            }
            else if (isSame(array[i][col], array[i - 1][col])) {
                // get new block value
                int temp = array[i][col].getBlockValue() + 
                    array[i - 1][col].getBlockValue();
                // create new block
                Block combined = new Block(i, col, temp);
                // delete both old blocks
                array[i][col] = combined;
                array[i - 1][col] = null;
                i = i - 1;
            }
        }
    }
    
    /*
     * Description: helper function for checking if two blocks have same value
     * Input: Block first, Block second
     * Output: boolean will be true if they are the same value, if not it will
     *         be false
     */
    private static boolean isSame(Block first, Block second) {
        if (first.getBlockValue() == second.getBlockValue()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /*
     * Description: shift helper function for checking number of blocks in a row
     * Input: Block[][] array, int row
     * Output: int blocks in a row 
     */
    private static int blocksInRow(Block[][] array, int row) {
        int counter = 0;
        // if it isn't null, there is a block there
        for (int i = 0; i < 4; i++) {
            if (array[row][i] != null) {
                counter += 1;
            }
        }
        return counter;
    }
    
    /*
     * Description: shift helper function for checking number of blocks in a col
     * Input: Block[][] array, int col
     * Output: int blocks in a col 
     */
    private static int blocksInCol(Block[][] array, int col) {
        int counter = 0;
        // if it isn't null, there is a block there
        for (int i = 0; i < 4; i++) {
            if (array[i][col] != null) {
                counter += 1;
            }
        }
        return counter;
    }
    
    // check if anything can be shifted in a column 
    public static boolean canShiftColumn(Block[][] array) {
        // assume nothing can be shifted
        boolean canShift = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                // if any two blocks in a row can be shifted, then game is not over
                if (array[j][i].getBlockValue() == 
                    array[j + 1][i].getBlockValue()) {
                    canShift = true;
                }
            }
        }
        return canShift;
    }
    
    // check if anything can be shifted in a row 
    public static boolean canShiftRow(Block[][] array) {
        // assume nothing can be shifted
        boolean canShift = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                // if any two blocks in a row can be shifted, then game is not over
                if (array[i][j].getBlockValue() == 
                    array[i][j + 1].getBlockValue()) {
                    canShift = true;
                }
            }
        }
        return canShift;
    }
    
}