package chess;

import java.util.Collection;
import java.util.HashSet;

public interface MoveCalculator {


  static HashSet<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPosition) {

    return null; // it returns null because it won't be implemented here, rather in the concrete classess that implement this interface

  }


  static HashSet<ChessMove> staticMove(ChessPosition current, int[][] possiblePosition, ChessBoard board) {
    HashSet<ChessMove> newMoves=new HashSet<>(8); // this method is mostly for the knight since it has a fixed move unlike other pieces
    int currentRow=current.getRow();
    int currentCol=current.getColumn();

    ChessGame.TeamColor currentPieceColor=board.getColorOfPieceinSquare(current);

    for (int[] possibleMove : possiblePosition) {
      ChessPosition possible=new ChessPosition(currentRow + possibleMove[0], currentCol + possibleMove[1]);
      if (MoveCalculator.isValid(possible) && board.getColorOfPieceinSquare(possible)!=currentPieceColor) {
        newMoves.add(new ChessMove(current, possible, null));
      }
    }
    return newMoves;
  }


  static boolean isValid(ChessPosition position) {

    if (position.getRow()<1 || position.getRow()>8) {
      return false;
    }
    if (position.getColumn()<1 || position.getColumn()>8) {
      return false;
    }
    return true;
  }

  static HashSet<ChessMove> relativeMoves(ChessPosition position, int[][] possibleDirections, ChessBoard board) {
    HashSet<ChessMove> possibleMoves=new HashSet<>(27); // this is the max amount of mmoves that a queen can have
    int currentRow=position.getRow();
    int currentCol=position.getColumn();
    ChessGame.TeamColor currentPieceColor=board.getColorOfPieceinSquare(position);

    for (int[] direction : possibleDirections) {
      boolean blocking=false;
      int i=1;
      while (!blocking) {
        ChessPosition possiblePos=new ChessPosition(currentRow + direction[0] * i, currentCol + direction[1] * i);
        if (!MoveCalculator.isValid(possiblePos)) {
          blocking=true;//invalid square changes to true
        } else {
          ChessPiece piece = board.getPiece(possiblePos);
          if(piece == null){
            possibleMoves.add(new ChessMove(position,possiblePos,null)); //this is when the square is empty
          }
          else if(piece.getTeamColor() != currentPieceColor){
            possibleMoves.add(new ChessMove(position,possiblePos,null)); // the square is occupied by an enemy piece
            blocking = true;
          } else{
            blocking = true;
          }
        }
        i++;
      } //end of while

    } // end of for loop
    return possibleMoves;
  }
}
