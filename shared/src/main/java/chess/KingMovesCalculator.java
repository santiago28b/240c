package chess;

import java.util.Collection;
import java.util.HashSet;

public class KingMovesCalculator implements MoveCalculator{


  public  static  HashSet<ChessMove> getMoves(ChessBoard board, ChessPosition currPosition) {
    int[][] kingMoves = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}};
    return MoveCalculator.relativeMoves(currPosition, kingMoves, board,true);

  }

}
