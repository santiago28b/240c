package chess;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPosition;

import java.util.Collection;

public abstract class PieceMovesCalculator {

  public abstract Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition);

}
