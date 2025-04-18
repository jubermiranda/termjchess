package com.juber.termjchess.model.piece;


import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BRookTest {
  BlackCell bCell;
  WhiteCell wCell;

  @BeforeEach
  public void setUp() {
    try {
      wCell = new WhiteCell(7, 0);
      bCell = new BlackCell(7, 7);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating bishops cells");
    }
  }

  @Test
  void testValidCanMoveTo() {
    BRook rook = new BRook(wCell);
    BaseCell validMove;

    try {

      validMove = new BlackCell(6,0);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new WhiteCell(3,0);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new BlackCell(0,0);
      assertTrue(rook.canMoveTo(validMove));

      validMove = new BlackCell(7,1);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new WhiteCell(7,4);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new BlackCell(7,7);
      assertTrue(rook.canMoveTo(validMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }
  }

  @Test
  void testInvalidCanMoveTo(){
    WRook rook = new WRook(bCell);
    BaseCell invalidMove;

    try {

      invalidMove = new BlackCell(6,6);
      assertFalse(rook.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(3,4);
      assertFalse(rook.canMoveTo(invalidMove));
      invalidMove = new BlackCell(0,0);
      assertFalse(rook.canMoveTo(invalidMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }

  }
}
















