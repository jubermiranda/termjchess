package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BPawnTest {
  @Test
  void testIsWB() {
    try {
      BPawn pawn = new BPawn(new BlackCell(6, 0));

      assertTrue(pawn.isB());
      assertFalse(pawn.isW());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }
}







