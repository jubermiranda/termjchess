package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class WBishopTest {
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

  // cria bispo branco nas duas casas iniciais
  @Test
  void testIsWB() {
    WBishop bishop;

    bishop = new WBishop(bCell);
    assertTrue(bishop.isW());
    assertFalse(bishop.isB());

    bishop = new WBishop(wCell);
    assertTrue(bishop.isW());
    assertFalse(bishop.isB());
  }

  @Test
  void testValidCanMoveTo(){
    WBishop bishop = new WBishop(bCell);
    BaseCell validMove;

    try {

      validMove = new BlackCell(1,1);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(2,0);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(1,3);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(2,4);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(3,5);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(4,6);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(5,7);
      assertTrue(bishop.canMoveTo(validMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }
  }

  @Test
  void testInvalidCanMoveTo(){
    BBishop bishop = new BBishop(bCell);
    BaseCell invalidMove;

    try {

      invalidMove = new BlackCell(2,2);
      assertFalse(bishop.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(2,3);
      assertFalse(bishop.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(7,0);
      assertFalse(bishop.canMoveTo(invalidMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }

  }

  @Test
  void testCantMoveToCrrPosition(){
    BBishop bishop = new BBishop(bCell);
    assertFalse(bishop.canMoveTo(bCell));
  }
}

