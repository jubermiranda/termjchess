package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;
import com.juber.termjchess.util.TestUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BBishopTest {

  @Test
  void testIsWB() {
    try {
      BlackCell bCell = new BlackCell(7, 5);
      BBishop bishop;

      bishop = new BBishop(bCell);
      assertTrue(bishop.isB());
      assertFalse(bishop.isW());

    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating cell");
    }
  }
  
}
