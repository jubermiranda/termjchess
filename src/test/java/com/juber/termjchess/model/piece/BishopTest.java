package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;
import com.juber.termjchess.util.TestUtils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

public class BishopTest {
  BlackCell bCell;
  WhiteCell wCell;

  @BeforeEach
  public void setUp() {
    try {
      wCell = new WhiteCell(0, 5);
      bCell = new BlackCell(0, 2);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating bishops cells");
    }
  }

  @Test
  void testValidBishopMoves() {
    WBishop wb = new WBishop(bCell);
    validBishopMoves(wb);

    BBishop bb = new BBishop(bCell);
    validBishopMoves(bb);
  }

  @Test
  void testInvalidCanMoveTo() {
    WBishop wb = new WBishop(bCell);
    invalidBishopMoves(wb);

    BBishop bb = new BBishop(bCell);
    invalidBishopMoves(bb);
  }

  @Test
  void testCantMoveToSamePosition() {
    BBishop bb = new BBishop(bCell);
    assertFalse(bb.canMoveTo(bCell));

    WBishop wb = new WBishop(bCell);
    assertFalse(wb.canMoveTo(bCell));
  }

  private void validBishopMoves(Bishop bishop) {
    ArrayList<String> validMoves = bishop.getValidMoves();
    assertTrue(validMoves.size() > 0);

    for (String c : validMoves) {
      assertTrue(bishop.canMoveTo(TestUtils.createCell(c)));
    }
  }

  private void invalidBishopMoves(Bishop bishop) {
    BaseCell invalidMove;
    try {

      invalidMove = new BlackCell(2, 2);
      assertFalse(bishop.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(2, 3);
      assertFalse(bishop.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(7, 0);
      assertFalse(bishop.canMoveTo(invalidMove));

    } catch (InvalidBoardCellPosition e) {
      fail("Invalid black cell position");
    }
  }
}
