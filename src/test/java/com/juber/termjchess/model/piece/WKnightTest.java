package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import com.juber.termjchess.util.TestUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class WKnightTest {
  @Test
  void testValidCanMoveTo() {
    try {
      WhiteCell e4Cell = new WhiteCell(3, 4);
      WKnight knight = new WKnight(e4Cell);

      ArrayList<String> validMoves = knight.getValidMoves();

      for(String c: validMoves){
        assertTrue(knight.canMoveTo(TestUtils.createCell(c)));
      }

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }

  @Test
  void testInvalidCanMoveTo() {
    try {
      WhiteCell e4Cell = new WhiteCell(3, 4);
      WKnight knight = new WKnight(e4Cell);

      assertFalse(knight.canMoveTo(new WhiteCell(4,5)));
      assertFalse(knight.canMoveTo(new BlackCell(3,5)));
      assertFalse(knight.canMoveTo(new WhiteCell(0,7)));
      assertFalse(knight.canMoveTo(new BlackCell(7,7)));

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }

}
