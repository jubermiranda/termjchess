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

public class PawnTest {
  BaseCell cell;

  @BeforeEach
  public void setUp() {
    try {
      cell = new BlackCell(1, 3);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating bishops cells");
    }
  }

  @Test
  void testTemplate() {
    assertTrue(true);
  }
}













