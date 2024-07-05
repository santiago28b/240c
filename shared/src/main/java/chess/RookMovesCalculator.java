package chess;
import chess.*;

import java.util.Collection;
import java.util.HashSet;

public class RookMovesCalculator extends PieceMovesCalculator{
  @Override
  public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

    int[][] RookDirections={
            {1,0},//up
            {-1,0},//down
            {0,1},//right
            {0,-1},//left
    };
    HashSet<ChessMove> validMoves = new HashSet<>();
    int row =myPosition.getRow();;
    int col =myPosition.getColumn();
    for(int i =0; i<RookDirections.length; i++){
      boolean obstructed = false;
      int j =1;

      while(!obstructed){
        int newRow = row + RookDirections[i][0]*j;
        int newCol = col + RookDirections[i][1]*j;
        ChessPosition newPosition = new ChessPosition(newRow,newCol);
        if(isValidMove(newPosition)){
          if(board.getPiece(newPosition)== null){
            validMoves.add(new ChessMove(myPosition,newPosition,null));
            j++;
          } else if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()){
            validMoves.add((new ChessMove(myPosition,newPosition,null)));
            obstructed = true;
          }
          else{
            obstructed = true;
          }

        }else{
          obstructed = true;
        }
      }
    }

    return validMoves;
  }


  boolean isValidMove(ChessPosition position){
    if(position.getRow()>=1 && position.getRow()<=8){
      if(position.getColumn()>=1 && position.getColumn()<=8){
        return true;
      }
    }
    return false;
  }
}




