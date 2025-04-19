package com.juber.termjchess.model.piece;


import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BRookTest {

  @Test
  public void testIsWB(){
    try {
      BRook rook = new BRook(new BlackCell(7,7));
      assertTrue(rook.isB());
      assertFalse(rook.isW());
    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cell");
    }
  }

}
















