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
      fail("Invalid cell position");
    }
  }

  @Test
  void testValidAndInvalidPositions() {
    boolean shouldThrows = true;
    WhiteCell cell;

    for(int i=0; i < 8; i++){
      for(int j=0; j < 8; j++){
        try {
          cell = new WhiteCell(i, j);
        } catch (InvalidBoardCellPosition e){
          if(!shouldThrows)
            fail("unexpected exception ");
        }
        shouldThrows  = !shouldThrows;
      }
      shouldThrows  = !shouldThrows;
    }
  }
}

