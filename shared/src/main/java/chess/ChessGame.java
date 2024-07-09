package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor teamTurn;
    private ChessBoard board;

    public ChessGame() {
        ChessBoard board = new ChessBoard();
        teamTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return  teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
       if( board.getPiece(startPosition) == null){
           return null;
       }
       //filter moves so have actual moves that do not put the king in danger.

       return board.getPiece(startPosition).pieceMoves(board,startPosition);
       //second
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
        //implemet if of team turn switch here.
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
       ChessPosition kingPosition = findKing(teamColor);
     //  Collection<Collection<ChessMove>> validMoves = new HashSet<>();

       ChessPosition pieceOppositePosition = null;
       for(int row = 1; row <= 8; row++){
           for (int col = 1; col <= 8; col++){
              ChessPosition possible = new ChessPosition(row,col);
              if(board.getPiece(possible)!= null){
                  if(board.getPiece(possible).getTeamColor() != board.getPiece(kingPosition).getTeamColor()){
                      pieceOppositePosition = new ChessPosition(row,col);
                      ChessPiece opposite = new ChessPiece(board.getPiece(pieceOppositePosition).getTeamColor(),board.getPiece(pieceOppositePosition).getPieceType());
                      //validMoves.add(opposite.pieceMoves(board,pieceOppositePosition));
                      Collection<ChessMove> validMoves = opposite.pieceMoves(board,pieceOppositePosition);
                      for (ChessMove move : validMoves){
                            if(move.getEndPosition().equals(kingPosition)){
                                return true;
                            }
                      }
                  }
              }
           }
       }
       return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
        //third
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
        //forth suggestion.
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
       this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }

    private ChessPosition findKing(TeamColor teamColor){
        ChessPosition kingPosition = null;
        for(int row = 1; row <= 8; row++){
            for(int col = 1; col<=8; col++){
                ChessPosition possible = new ChessPosition(row,col);
                ChessPiece piece = board.getPiece(possible);
                if(board.getPiece(possible) != null){
                    if(piece.getTeamColor().equals(teamColor) && piece.getPieceType().equals(ChessPiece.PieceType.KING)){
                        kingPosition = possible;
                        return kingPosition;
                    }
                }
            }
        }
        return null;
    }
}
