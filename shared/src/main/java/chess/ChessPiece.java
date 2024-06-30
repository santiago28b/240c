package chess;

import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor color;
    private PieceType piece;


    public ChessPiece(ChessGame.TeamColor pieceColor, PieceType type) {
        this.color = pieceColor;
        this.piece = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return piece;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public String toString() {
         switch (piece){
            case KING:
                return color == ChessGame.TeamColor.WHITE ? "K" : "k";
             case QUEEN:
                 return color == ChessGame.TeamColor.WHITE ? "Q" : "q";
             case BISHOP:
                 return color == ChessGame.TeamColor.WHITE ? "B" : "b";
             case KNIGHT:
                 return color == ChessGame.TeamColor.WHITE ? "N" : "n";
             case ROOK:
                 return color == ChessGame.TeamColor.WHITE ? "R" : "r";
             case PAWN:
                 return color == ChessGame.TeamColor.WHITE ? "P" : "p";
        }
        return "0";
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        ChessPiece that=(ChessPiece) o;
        return color==that.color && piece==that.piece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, piece);
    }
}
