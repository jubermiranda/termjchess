package com.juber.termjchess.model.piece;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WQueenTest {
  @Test
  void testIsWB() {
    try {
      WQueen queen = new WQueen(new WhiteCell(1, 0));

      assertTrue(queen.isB());
      assertFalse(queen.isW());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }
}








