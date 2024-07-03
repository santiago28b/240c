package chess;

import java.util.HashSet;

public class BishopMoveCalculator {
  public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition position){
    int [][] relativeMoves = {{1,1},{1,-1},{-1,1},{-1,-1}};

    return MoveCalculator.relativeMoves(position,relativeMoves,board,false);
  }
}
