package com.juber.termjchess.model.piece;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.exception.IllegalChessMovementException;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class KingTest {
  BaseCell cell;

  @BeforeEach
  public void setUp(){
    try {
      cell = new BlackCell(0, 4);
    } catch (InvalidBoardCellPosition e) {
      fail("unexpected error creating cell");
    }
  }

  @Test
  void testValidCanMoveTo() {
    King king;

    king = new WKing(cell);
    validKingMoves(king);

    king = new BKing(cell);
    validKingMoves(king);
  }

  @Test
  void testInvalidCanMoveTo(){
    King king;

    king = new WKing(cell);
    invalidKingMoves(king);

    king = new BKing(cell);
    invalidKingMoves(king);
  }

  // testa o roque (moveimento envolvendo a torre e o rei)
  // e1 e e8
  @Test
  void testCastling(){
    King king;
    Rook rook;

    // testa roque com pecas brancas
    try {
      //minor clastling
      king = new WKing(BaseCell.createCell("e1"));
      rook = new WRook(BaseCell.createCell("h1"));
      king.castling(rook);

      //major clastling
      king = new WKing(BaseCell.createCell("e1"));
      rook = new WRook(BaseCell.createCell("a1"));
      king.castling(rook);

    } catch(IllegalChessMovementException e){
      fail("error while tryin castling king and rook: " + e.getMessage());
    }

    // testa roque com pecas pretas
    try {
      //minor clastling
      king = new BKing(BaseCell.createCell("e8"));
      rook = new BRook(BaseCell.createCell("h8"));
      king.castling(rook);

      //major clastling
      king = new WKing(BaseCell.createCell("e8"));
      rook = new WRook(BaseCell.createCell("a8"));
      king.castling(rook);

    } catch(IllegalChessMovementException e){
      fail("error while tryin castling king and rook");
    }
  }

  // se estiver em um dos cantos, apenas 3 movimentos
  @Test
  void testKingHasTreeValidMovesFromCorner(){
    King king;

    king = new WKing(BaseCell.createCell(0,0));
    assertEquals(king.getValidMoves().size(), 3);

    king = new WKing(BaseCell.createCell(0,7));
    assertEquals(king.getValidMoves().size(), 3);

    king = new WKing(BaseCell.createCell(7,0));
    assertEquals(king.getValidMoves().size(), 3);

    king = new WKing(BaseCell.createCell(7,7));
    assertEquals(king.getValidMoves().size(), 3);
  }

  // se estiver perto de uma borda (mas nao em um canto)
  // tem 5 movimentos possiveis
  @Test
  void testKingHasFiveValidMovesFromBorder(){
    King king;

    king = new WKing(BaseCell.createCell(0,4));
    assertEquals(king.getValidMoves().size(), 5);

    king = new WKing(BaseCell.createCell(2,0));
    assertEquals(king.getValidMoves().size(), 5);

    king = new WKing(BaseCell.createCell(7,5));
    assertEquals(king.getValidMoves().size(), 5);

    king = new WKing(BaseCell.createCell(3,7));
    assertEquals(king.getValidMoves().size(), 5);
  }

  // tem oito movimentos possiveis se nao estiver em nenhuma borda
  @Test
  void testKingHasEightValidMovesIfNotInBorder(){
    King king;

    king = new WKing(BaseCell.createCell(4,2));
    assertEquals(king.getValidMoves().size(), 8);
    king = new WKing(BaseCell.createCell(2,4));
    assertEquals(king.getValidMoves().size(), 8);
  }


  private void validKingMoves(King king){
    ArrayList<String> validMoves = king.getValidMoves();
    assertTrue(validMoves.size() > 0);

    for(String c: validMoves){
      assertTrue(king.canMoveTo(BaseCell.createCell(c)));
    }
  }

  private void invalidKingMoves(King king){
    BaseCell invalidMove;

    try {
      invalidMove = new BlackCell(2, 4);
      assertFalse(king.canMoveTo(invalidMove));
      invalidMove = new WhiteCell(0, 7);
      assertFalse(king.canMoveTo(invalidMove));

    } catch (InvalidBoardCellPosition e){
      fail("Invalid black cell position");
    }
  }
}
