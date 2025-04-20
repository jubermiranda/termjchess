package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class RookTest {
  BlackCell bCell;
  WhiteCell wCell;

  @BeforeEach
  public void setUp() {
    try {
      bCell = new BlackCell(0, 0);
      wCell = new WhiteCell(0, 7);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating bishops cells");
    }
  }

  @Test
  void testValidCanMoveTo() {
    Rook rook;

    rook = new WRook(bCell);
    validRookMoves(rook);

    rook= new BRook(bCell);
    validRookMoves(rook);
  }

  @Test
  void testInvalidCanMoveTo(){
    Rook rook;

    rook = new WRook(bCell);
    invalidRookMoves(rook);

    rook= new BRook(bCell);
    invalidRookMoves(rook);
  }

  private void validRookMoves(Rook rook) {
    ArrayList<String> validMoves = rook.getValidMoves();
    assertTrue(validMoves.size() > 0);

    for (String c : validMoves) {
      assertTrue(rook.canMoveTo(BaseCell.createCell(c)));
    }
  }

  @Test
  void testRookTrace(){
    int row = 0, col = 0;
    BaseCell initialPos = BaseCell.createCell(row, col);
    Rook rook = new BRook(initialPos);

    BaseCell newPos = BaseCell.createCell(row + 1, col);
    assertEquals(0, rook.getTrace(newPos).size());

    newPos = BaseCell.createCell(row, col + 3);
    assertEquals(2, rook.getTrace(newPos).size());
    newPos = BaseCell.createCell(row + 7, col);
    assertEquals(6, rook.getTrace(newPos).size());
  }

  private void invalidRookMoves(Rook rook) {
    BaseCell invalidMove;

    try {
      invalidMove = new BlackCell(1,1);
      assertFalse(rook.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(1,2);
      assertFalse(rook.canMoveTo(invalidMove));
      invalidMove = new BlackCell(5,5);
      assertFalse(rook.canMoveTo(invalidMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }
  }
}
