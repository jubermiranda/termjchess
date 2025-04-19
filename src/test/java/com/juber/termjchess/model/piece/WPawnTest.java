package com.juber.termjchess.model.piece;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WPawnTest {
  @Test
  void testIsWB() {
    try {
      WPawn pawn = new WPawn(new WhiteCell(1, 0));

      assertTrue(pawn.isB());
      assertFalse(pawn.isW());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }
}







