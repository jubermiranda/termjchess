package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
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

  // cria bispo preto nas duas casas iniciais
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
}

