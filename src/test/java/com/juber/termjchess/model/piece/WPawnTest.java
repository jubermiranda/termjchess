package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WPawnTest {
  @Test
  void testIsWB() {
    try {
      WPawn pawn = new WPawn(new WhiteCell(1, 0));

      assertTrue(pawn.isW());
      assertFalse(pawn.isB());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }

  //apenas uma unica ocasiao que trace sera nao vazio:
  //primeiro movimento do peao, a 2 casas a frente
  @Test
  void testPawnTrace(){
    BaseCell initialPos = BaseCell.createCell("a2");
    WPawn pawn = new WPawn(initialPos);

    BaseCell newPos = BaseCell.createCell("a3");
    assertEquals(0, pawn.getTrace(newPos).size());

    newPos = BaseCell.createCell("a4");
    assertEquals(1, pawn.getTrace(newPos).size());
  }
}







