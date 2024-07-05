package chess;

import java.util.Collection;
import java.util.HashSet;

public class PawnMovesCalculator extends PieceMovesCalculator {
  @Override
  public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
    switch (board.getPiece(myPosition).getTeamColor()) {
      case WHITE -> {
        return new PawnMovesCalculator().WhiteMoves(board, myPosition);
      }
      case BLACK -> {
        return new PawnMovesCalculator().BlackMoves(board, myPosition);
      }
    }
    return null;
  }

  private Collection<ChessMove> BlackMoves(ChessBoard board, ChessPosition myPosition) {
    int[][] blackPawnDirections = {
            {-1,0},
            {-1,-1},
            {-1,1}
    };

    HashSet<ChessMove>validMoves = new HashSet<>();

    int row =myPosition.getRow();
    int col =myPosition.getColumn();
    int promotionRow = 1;
    int startingRow = 7;

    int newRow = row + blackPawnDirections[0][0];
    int newCol = col + blackPawnDirections[0][1];
    ChessPosition newPosition = new ChessPosition(newRow,newCol);
    if(isValidMove(newPosition)){
      if(board.getPiece(newPosition)==  null){
        if(newRow == promotionRow){
          addPromotion(validMoves,myPosition,newPosition);
        }else{
          validMoves.add(new ChessMove(myPosition,newPosition,null));
        }
      }
    }
    if(row==startingRow){
      newRow = row-2;
      newCol = col;
      newPosition = new ChessPosition(newRow,newCol);
      ChessPosition intermediate = new ChessPosition(row-1,newCol);
      if(isValidMove(newPosition)){
        if(board.getPiece(intermediate)== null&& board.getPiece(newPosition)== null){
          validMoves.add(new ChessMove(myPosition,newPosition,null));
        }
      }
    }

    for(int i = 1; i<blackPawnDirections.length; i++){
      newRow = row + blackPawnDirections[i][0];
      newCol = col + blackPawnDirections[i][1];
      newPosition = new ChessPosition(newRow,newCol);
      if(isValidMove(newPosition)&& board.getPiece(newPosition)!= null){
        if(board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()){
          if(newRow == promotionRow){
            addPromotion(validMoves,myPosition,newPosition);
          }else{
            validMoves.add(new ChessMove(myPosition,newPosition,null));
          }
        }
      }
    }

    return validMoves;
  }
  private Collection<ChessMove> WhiteMoves(ChessBoard board, ChessPosition myPosition) {
    int[][] PawnDirections={
            {1, 0},
            {1, 1},
            {1, -1},
    };

    HashSet<ChessMove> validMoves=new HashSet<>();
    int row=myPosition.getRow();
    int col=myPosition.getColumn();
    int startingRow=2;
    int promotionRow=8;

    int newRow=row + PawnDirections[0][0];
    int newCol=col + PawnDirections[0][1];
    ChessPosition newPosition=new ChessPosition(newRow, newCol);
    if (isValidMove(newPosition)) {
      if (board.getPiece(newPosition)==null) {
        if (newRow==promotionRow) {
          addPromotion(validMoves, myPosition, newPosition);
        } else {
          validMoves.add(new ChessMove(myPosition, newPosition, null));
        }
      }
    }
    //double step
    if (row==startingRow) {
      newRow=row + 2;
      newCol=col;
      ChessPosition intermediatePosition = new ChessPosition(row+1,col);
      newPosition = new ChessPosition(newRow,newCol);
      if(isValidMove(newPosition) && board.getPiece(intermediatePosition)== null && board.getPiece(newPosition)== null){
        validMoves.add(new ChessMove(myPosition,newPosition,null));
      }
    }

    for(int i = 1; i < PawnDirections.length; i++){
      newRow = row + PawnDirections[i][0];
      newCol = col + PawnDirections[i][1];
      newPosition = new ChessPosition(newRow,newCol);
      if(isValidMove(newPosition)){
        if(board.getPiece(newPosition)!= null && board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()){
          if(newRow == promotionRow){
            addPromotion(validMoves,myPosition,newPosition);
          }else{
            validMoves.add(new ChessMove(myPosition,newPosition,null));
          }
        }
      }

    }
    return validMoves;
  }
  private void addPromotion (Collection<ChessMove>validMoves, ChessPosition start, ChessPosition end){
    validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.QUEEN));
    validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.BISHOP));
    validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.ROOK));
    validMoves.add(new ChessMove(start, end, ChessPiece.PieceType.KNIGHT));
  }

  boolean isValidMove(ChessPosition position) {
    if (position.getRow()>=1 && position.getRow()<=8) {
      if (position.getColumn()>=1 && position.getColumn()<=8) {
        return true;
      }
    }
    return false;
  }
}


