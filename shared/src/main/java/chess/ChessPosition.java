package chess;

import java.util.Objects;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private int row;
    private int column;

    public ChessPosition(int row, int col) {
        this.row = row;
        this.column = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;//comparing to itself
        if (o==null || getClass()!=o.getClass()) return false; //checking if object is null or if the class is not the same class the object return false
        ChessPosition that = (ChessPosition) o; //creating object called "that" and casting it to ChessPosition.
        return row==that.row && column==that.column; //once it is casted it can compare its values such as row and col
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "ChessPosition{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

}
