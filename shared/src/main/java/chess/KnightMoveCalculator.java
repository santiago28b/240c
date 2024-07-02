package chess;

import java.util.HashSet;

public class KnightMoveCalculator {

  public static HashSet<ChessMove> getMoves(ChessBoard board,ChessPosition position){
    int[][] knightMoves = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

      return MoveCalculator.staticMove(position,knightMoves,board);
  }

}
