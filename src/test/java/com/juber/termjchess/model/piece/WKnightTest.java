package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class WKnightTest {
  @Test
  void testValidCanMoveTo() {
    try {
      WhiteCell e4Cell = new WhiteCell(3, 4);
      WKnight knight = new WKnight(e4Cell);

      ArrayList<BaseCell> validMoves = validMovesFrom(e4Cell);

      for(BaseCell c: validMoves){
        assertTrue(knight.canMoveTo(c));
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

  private ArrayList<BaseCell> validMovesFrom(BaseCell src) {
    int row = src.getRow();
    int col = src.getCol();
    ArrayList<BaseCell> result = new ArrayList<BaseCell>();
    if (row + 2 < 8) {
      if (col + 1 < 8)
        result.add(this.createCell(row + 2, col + 1));
      if (col - 1 >= 0)
        result.add(this.createCell(row + 2, col - 1));
    }
    if (row - 2 >= 0) {
      if (col + 1 < 8)
        result.add(this.createCell(row - 2, col + 1));
      if (col - 1 >= 0)
        result.add(this.createCell(row - 2, col - 1));
    }
    if (col + 2 < 8) {
      if (row + 1 < 8)
        result.add(this.createCell(row + 1, col + 2));
      if (row - 1 >= 0)
        result.add(this.createCell(row - 1, col + 2));
    }
    if (col - 2 >= 0) {
      if (row + 1 < 8)
        result.add(this.createCell(row + 1, col - 2));
      if (row - 1 >= 0)
        result.add(this.createCell(row - 1, col - 2));
    }

    return result;
  }

  private BaseCell createCell(int row, int col) {
    try {
      BlackCell c = new BlackCell(row, col);
      return c;

    } catch (InvalidBoardCellPosition e) {
      try {
        WhiteCell c = new WhiteCell(row, col);
        return c;

      } catch (InvalidBoardCellPosition e2) {
        return null;
      }
    }
  }
}
