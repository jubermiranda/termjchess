package com.juber.termjchess.model.board;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
  static int STD_CHESS_BOARD_SIZE = 64;

  @Test
  void testBoardSizeAfterCreation() {
    try {
      Board b = new Board();
      assertEquals(b.getBoardSize(), STD_CHESS_BOARD_SIZE);
    } catch( Exception e ){
      fail("unexpected exception...:" + e.getMessage());
    }
  }
}


