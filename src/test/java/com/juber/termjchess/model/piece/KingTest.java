package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;
import com.juber.termjchess.util.TestUtils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class KingTest {
  BaseCell cell;

  @BeforeEach
  public void setUp(){
    try {
      cell = new BlackCell(0, 4);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating cell");
    }
  }

  @Test
  void testValidCanMoveTo() {
    King king;

    king = new WKing(cell);
    validQueenMoves(king);

    king = new BKing(cell);
    validQueenMoves(king);
  }

  @Test
  void testInvalidCanMoveTo(){
    King king;

    king = new WKing(cell);
    invalidQueenMoves(king);

    king = new BKing(cell);
    invalidQueenMoves(king);
  }

  private void validQueenMoves(King king){
    ArrayList<String> validMoves = king.getValidMoves();
    assertTrue(validMoves.size() > 0);

    for(String c: validMoves){
      assertTrue(king.canMoveTo(TestUtils.createCell(c)));
    }
  }

  private void invalidQueenMoves(King king){
    BaseCell invalidMove;

    try {
      invalidMove = new BlackCell(cell.getRow() + 3,cell.getCol());
      assertFalse(king.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(cell.getRow(),cell.getCol() + 3);
      assertFalse(king.canMoveTo(invalidMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }
  }
}
