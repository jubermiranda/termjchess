package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BQueenTest {
  @Test
  void testIsWB() {
    try {
      BQueen queen = new BQueen(new BlackCell(7, 3));

      assertTrue(queen.isB());
      assertFalse(queen.isW());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }
}








