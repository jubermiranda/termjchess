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

  // testa as possiveis validas e invalidas casas pretas.
  // no xadrez elas alternam. entao usamos um boolean shoudThrow
  // para indiquar quando seria esperado a excessao.
  // seguindo metodo escolhido para percorrer (2 fors incrementais)
  // como a ultima casa de uma linha é igual a primeira da proxima
  // precisamos desinverter shouldThrow no fim do for mais interno
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

  // testa o nome das casas
  // (esta funcao testa apenas alguns)
  @Test
  void testCellNames(){
    try {

      BlackCell bc;
      WhiteCell wc;

      bc = new BlackCell(0,0);
      assertEquals("a1", bc.cellName());
      wc = new WhiteCell(0,7);
      assertEquals("h1", wc.cellName());

      bc = new BlackCell(1,1);
      assertEquals("b2", bc.cellName());
      wc = new WhiteCell(1,6);
      assertEquals("g2", wc.cellName());

      bc = new BlackCell(2,2);
      assertEquals("c3", bc.cellName());
      wc = new WhiteCell(2,5);
      assertEquals("f3", wc.cellName());

      bc = new BlackCell(3,3);
      assertEquals("d4", bc.cellName());
      wc = new WhiteCell(3,4);
      assertEquals("e4", wc.cellName());

    } catch (InvalidBoardCellPosition e){
      fail("Invalid cell position");
    }
  }
}
