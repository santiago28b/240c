package chess;

import java.util.HashSet;

public class RookMovesCalculator {

  public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition position){
    int [][] rookMoves = {{1,0},{0,1},{-1,0},{0,-1}};

    return MoveCalculator.relativeMoves(position,rookMoves,board,false);
  }

}
