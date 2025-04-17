package com.juber.termjchess.model.board;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackCellTest {
  @Test
  void testIsWB() {
    try {
      BlackCell cell = new BlackCell(0,0);

      assertTrue(cell.isB());
      assertFalse(cell.isW());
    } catch(InvalidBoardCellPosition e){
      assertTrue(false);
    }
  }
}
