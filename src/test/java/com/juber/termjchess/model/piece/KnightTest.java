package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class KnightTest {
  @Test
  void testValidCanMoveTo() {
    try {
      WhiteCell e4Cell = new WhiteCell(3, 4);
      Knight knight;

      knight = new WKnight(e4Cell);
      validKnightMoves(knight);

      knight = new BKnight(e4Cell);
      validKnightMoves(knight);

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }

  @Test
  void testInvalidCanMoveTo() {
    try {
      WhiteCell e4Cell = new WhiteCell(3, 4);
      Knight knight;

      knight = new WKnight(e4Cell);
      invalidKnightMoves(knight);

      knight = new BKnight(e4Cell);
      invalidKnightMoves(knight);

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }

  // quando esta em um dos cantos tem apenas 2 movimentos possiveis
  @Test
  void testKnightHasTwoValidMovesFromCorner(){
    Knight knight;

    Knight = new WKnight(BaseCell.createCell(0,0));
    assertEquals(knight.getValidMoves(), 2);
    Knight = new WKnight(BaseCell.createCell(0,7));
    assertEquals(knight.getValidMoves(), 2);
    Knight = new WKnight(BaseCell.createCell(7,0));
    assertEquals(knight.getValidMoves(), 2);
    Knight = new WKnight(BaseCell.createCell(7,7));
    assertEquals(knight.getValidMoves(), 2);
  }

  // se estiver na borda (mas nao em um canto)
  // 4 movimentos possiveis
  @Test
  void testKnightHasFourValidMovesFromBorder(){
    Knight knight;

    Knight = new WKnight(BaseCell.createCell(0,4));
    assertEquals(knight.getValidMoves(), 4);
    Knight = new WKnight(BaseCell.createCell(7,5));
    assertEquals(knight.getValidMoves(), 4);
  }

  @Test
  void testKnightHasEightValidMovesIfNotInBorder(){
    Knight knight;

    Knight = new WKnight(BaseCell.createCell(2,4));
    assertEquals(knight.getValidMoves(), 8);
    Knight = new WKnight(BaseCell.createCell(5,5));
    assertEquals(knight.getValidMoves(), 8);
  }

  private void validKnightMoves(Knight knight) {
    ArrayList<String> validMoves = knight.getValidMoves();

    for(String c: validMoves){
      assertTrue(knight.canMoveTo(BaseCell.createCell(c)));
    }
  }

  private void invalidKnightMoves(Knight knight){
    try {
      assertFalse(knight.canMoveTo(new WhiteCell(4,5)));
      assertFalse(knight.canMoveTo(new BlackCell(3,5)));
      assertFalse(knight.canMoveTo(new WhiteCell(0,7)));
      assertFalse(knight.canMoveTo(new BlackCell(7,7)));

    } catch (InvalidBoardCellPosition e){
      fail("unexpected error creating cells");
    }
  }
}
