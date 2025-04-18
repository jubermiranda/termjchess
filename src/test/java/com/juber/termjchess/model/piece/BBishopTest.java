package com.juber.termjchess.model.piece;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BBishopTest {
  BlackCell cell = new BlackCell(7,5);

  @Test
  void testIsWB() {
    BBishop bishop = new BBishop(cell);
    assertTrue(bishop.isB());
    assertFalse(bishop.isW());
  }
}



