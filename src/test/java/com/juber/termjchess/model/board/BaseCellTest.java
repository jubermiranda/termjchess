package com.juber.termjchess.model.board;

import com.juber.termjchess.exception.InvalidBoardCellPosition;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaseCellTest {
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

