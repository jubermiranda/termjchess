package com.juber.termjchess.model.board;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackCellTest {
  @Test
  void testInvalidPositionThrowsException() {
    assertThrows(InvalidBoardCellPosition.class, new BlackCell(-1, -1));
  }

  @Test
  void testIsWB() {
    BlackCell cell = new BlackCell(0,0);

    assertTrue(cell.isB());
    assertFalse(cell.isW());
  }
}
