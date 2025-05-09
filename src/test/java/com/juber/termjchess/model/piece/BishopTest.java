package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

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

  @Test
  void testBishopTrace(){
    int row = 0, col = 0;
    BaseCell initialPos = BaseCell.createCell(row, col);
    Bishop bishop = new BBishop(initialPos);

    // trace eh vazio a apenas uma casa de distancia
    BaseCell newPos = BaseCell.createCell(row + 1, col + 1);
    assertEquals(0, bishop.getTrace(newPos).size());

    // trace contem 1 casa, se o destino eh duas casas de distancia
    // e assim por diante
    newPos = BaseCell.createCell(row + 2, col + 2);
    assertEquals(1, bishop.getTrace(newPos).size());
    newPos = BaseCell.createCell(row + 3, col + 3);
    assertEquals(2, bishop.getTrace(newPos).size());
    newPos = BaseCell.createCell(row + 4, col + 4);
    assertEquals(3, bishop.getTrace(newPos).size());
    newPos = BaseCell.createCell(row + 7, col + 7);
    assertEquals(6, bishop.getTrace(newPos).size());
  }

  private void validBishopMoves(Bishop bishop) {
    ArrayList<String> validMoves = bishop.getValidMoves();
    assertTrue(validMoves.size() > 0);

    for (String c : validMoves) {
      assertTrue(bishop.canMoveTo(BaseCell.createCell(c)));
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
