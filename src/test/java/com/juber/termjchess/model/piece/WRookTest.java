package com.juber.termjchess.model.piece;


import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class WRookTest {

  @Test
  public void testIsWB(){
    try {
      WRook rook = new WRook(new BlackCell(7,7));
      assertTrue(rook.isW());
      assertFalse(rook.isB());
    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cell");
    }
  }

}
















