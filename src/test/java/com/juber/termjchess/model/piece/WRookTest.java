package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class WRookTest {
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
    WRook rook = new WRook(bCell);
    BaseCell validMove;

    try {

      validMove = new WhiteCell(1,0);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new BlackCell(4,0);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new WhiteCell(7,0);
      assertTrue(rook.canMoveTo(validMove));

      validMove = new WhiteCell(0,1);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new BlackCell(0,4);
      assertTrue(rook.canMoveTo(validMove));
      validMove = new WhiteCell(0,7);
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















