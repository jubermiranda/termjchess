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
      fail("Invalid cell position");
    }
  }

  // testamos as possiveis validas e invalidas casas pretas.
  // no xadrez elas alternam.
  // porem da ultima coluna de uma linha para a primeira coluna da proxima sao iguais
  // por isso invertemos de novo shouldThrows entre os fors
  // para 'desinverter o valor de novo'
  @Test
  void testValidAndInvalidPositions() {
    boolean shouldThrows = false;
    BlackCell cell;

    for(int i=0; i < 8; i++){
      for(int j=0; j < 8; j++){
        try {
          cell = new BlackCell(i, j);
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
