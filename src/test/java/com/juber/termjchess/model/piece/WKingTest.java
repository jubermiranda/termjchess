package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WKingTest {
  @Test
  void testIsWB() {
    try {
      BlackCell bCell = new BlackCell(7, 5);
      WKing king;

      king = new WKing(bCell);
      assertTrue(king.isW());
      assertFalse(king.isB());

    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating cell");
    }
  }
}




