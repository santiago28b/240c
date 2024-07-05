package chess;

import java.util.Collection;
import java.util.HashSet;

public class KnightMovesCalculator extends PieceMovesCalculator{
  @Override
  public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
    int[][] KnightDirections ={
            {2,1},
            {1,2},
            {-1,2},
            {-2,1},
            {-2,-1},
            {-1,-2},
            {1,-2},
            {2,-1},
    };
    HashSet<ChessMove>validMoves = new HashSet<>();
    int row =myPosition.getRow();;
    int col =myPosition.getColumn();

    for(int i =0; i<KnightDirections.length; i++){

        int newRow=row + KnightDirections[i][0];
        int newCol=col + KnightDirections[i][1];
        ChessPosition newPosition=new ChessPosition(newRow, newCol);
        if (isValidMove(newPosition)) {
          if (board.getPiece(newPosition)==null) {
            validMoves.add(new ChessMove(myPosition, newPosition, null));
          } else if (board.getPiece(myPosition).getTeamColor()!= board.getPiece(newPosition).getTeamColor()) {
            validMoves.add(new ChessMove(myPosition, newPosition, null));
          }

        }

    }

    return validMoves;
  }

  boolean isValidMove(ChessPosition position){
    if(position.getRow()>=1 && position.getRow() <=8){
      if(position.getColumn()>=1 && position.getColumn()<=8){
        return true;
      }
    }
    return false;
  }
}
