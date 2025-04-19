package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BKingTest {
  @Test
  void testIsWB() {
    try {
      BlackCell bCell = new BlackCell(7, 5);
      BKing king;

      king = new BKing(bCell);
      assertTrue(king.isB());
      assertFalse(king.isW());

    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating cell");
    }
  }
}




