package com.juber.termjchess.model.board;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WhiteCellTest {
  @Test
  void testIsWB() {
    try {
      WhiteCell cell = new WhiteCell(0,1);

      assertTrue(cell.isW());
      assertFalse(cell.isB());

    } catch(InvalidBoardCellPosition e){
      assertTrue(false);
    }
  }
}

