package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WQueenTest {
  @Test
  void testIsWB() {
    try {
      WQueen queen = new WQueen(new WhiteCell(1, 0));

      assertTrue(queen.isW());
      assertFalse(queen.isB());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }
}








