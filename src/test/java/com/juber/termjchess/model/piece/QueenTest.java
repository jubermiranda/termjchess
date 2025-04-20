package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.exception.IllegalChessMovementException;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

public class QueenTest {
  BaseCell cell;

  @BeforeEach
  public void setUp() {
    try {
      cell = new WhiteCell(3, 4);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating cell");
    }
  }

  @Test
  void testValidCanMoveTo() {
    Queen queen;

    queen = new WQueen(cell);
    validQueenMoves(queen);

    queen = new BQueen(cell);
    validQueenMoves(queen);
  }

  @Test
  void testInvalidCanMoveTo(){
    Queen queen;

    queen = new BQueen(cell);
    invalidQueenMoves(queen);

    queen = new WQueen(cell);
    invalidQueenMoves(queen);

  }

  @Test
  void testQueenMovements(){
    Queen queen = new WQueen(BaseCell.createCell(0,0));

    try {
      //try horizontal move
      queen.moveTo(BaseCell.createCell(0, 2));
      //try vertical move
      queen.moveTo(BaseCell.createCell(1, 2));
      //try diagonal move
      queen.moveTo(BaseCell.createCell(2, 1));
    } catch (IllegalChessMovementException e){
      fail("error while moving queen");
    }
  }

  @Test
  void testQueenTrace(){
    int row = 0, col = 0;
    BaseCell initialPos = BaseCell.createCell(row, col);
    Queen queen = new WQueen(initialPos);

    BaseCell newPos = BaseCell.createCell(row + 1, col);
    assertEquals(0, queen.getTrace(newPos).size());

    // left
    newPos = BaseCell.createCell(row, col + 2);
    assertEquals(1, queen.getTrace(newPos).size());
    // top-right diagonal
    newPos = BaseCell.createCell(row + 4, col + 4);
    assertEquals(3, queen.getTrace(newPos).size());

    queen = new WQueen(BaseCell.createCell(7,7));
    // right
    newPos = BaseCell.createCell(7, 7 - 4);
    assertEquals(3, queen.getTrace(newPos).size());
    // bot-left diagonal
    newPos = BaseCell.createCell(0, 0);
    assertEquals(6, queen.getTrace(newPos).size());
  }

  private void validQueenMoves(Queen queen){
    ArrayList<String> validMoves = queen.getValidMoves();
    assertTrue(validMoves.size() > 0);

    for(String c: validMoves){
      assertTrue(queen.canMoveTo(BaseCell.createCell(c)));
    }
  }

  private void invalidQueenMoves(Queen queen){
    BaseCell invalidMove;

    try {
      invalidMove = new BlackCell(5,3);
      assertFalse(queen.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(4,7);
      assertFalse(queen.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(1,0);
      assertFalse(queen.canMoveTo(invalidMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }
  }
}
