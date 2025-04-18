package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
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
}
