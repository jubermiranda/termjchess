package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BPawnTest {
  @Test
  void testIsWB() {
    try {
      BPawn pawn = new BPawn(new BlackCell(6, 0));

      assertTrue(pawn.isB());
      assertFalse(pawn.isW());

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }

  //apenas uma unica ocasiao que trace sera nao vazio:
  //primeiro movimento do peao, a 2 casas a frente
  @Test
  void testPawnTrace(){
    BaseCell initialPos = BaseCell.createCell("a7");
    BPawn pawn = new BPawn(initialPos);

    BaseCell newPos = BaseCell.createCell("a6");
    assertEquals(0, pawn.getTrace(newPos).size());

    newPos = BaseCell.createCell("a5");
    assertEquals(1, pawn.getTrace(newPos).size());
  }
}







