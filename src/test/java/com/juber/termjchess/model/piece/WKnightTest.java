package com.juber.termjchess.model.piece;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.WhiteCell;

public class WKnightTest {
  @Test
  void testIsWB() {
    try {
      BKnight knight = new BKnight(new WhiteCell(3, 4));

      assertTrue(knight.isB());
      assertFalse(knight.isW());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }
}

