package chess;

import java.util.HashSet;

public class QueenMovesCalculator {

  public static HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition position){
    int[][] queenMoves = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}};
    return MoveCalculator.relativeMoves(position,queenMoves,board,false);
  }
}
