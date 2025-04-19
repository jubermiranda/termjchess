package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
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
