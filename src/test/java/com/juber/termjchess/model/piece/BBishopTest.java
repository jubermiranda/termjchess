package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class BBishopTest {
  BlackCell bCell;
  WhiteCell wCell;

  @BeforeEach
  public void setUp() {
    try {
      bCell = new BlackCell(7, 5);
      wCell = new WhiteCell(7, 2);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating bishops cells");
    }
  }

  // cria bispo preto nas duas casas iniciais
  @Test
  void testIsWB() {
    BBishop bishop;

    bishop = new BBishop(bCell);
    assertTrue(bishop.isB());
    assertFalse(bishop.isW());

    bishop = new BBishop(wCell);
    assertTrue(bishop.isB());
    assertFalse(bishop.isW());
  }

  @Test
  void testValidCanMoveTo(){
    BBishop bishop = new BBishop(bCell);
    BaseCell validMove;

    try {

      validMove = new BlackCell(6,4);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(6,6);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(5,3);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(5,7);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(4,2);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(3,1);
      assertTrue(bishop.canMoveTo(validMove));
      validMove = new BlackCell(2,0);
      assertTrue(bishop.canMoveTo(validMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }
  }

  @Test
  void testInvalidCanMoveTo(){
    BBishop bishop = new BBishop(bCell);
    BlackCell invalidMove;

    try {

      invalidMove = new BlackCell(6,2);
      assertFalse(bishop.canMoveTo(invalidMove));
      invalidMove = new BlackCell(6,0);
      assertFalse(bishop.canMoveTo(invalidMove));
      invalidMove = new BlackCell(5,5);
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
