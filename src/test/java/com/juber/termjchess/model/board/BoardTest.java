package com.juber.termjchess.model.board;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class BoardTest {
  static int STD_CHESS_BOARD_SIZE = 64;
  static int STD_CHESS_PIECES_AMOUNT = 32;
  static int STD_CHESS_B_PIECES_AMOUNT = 16;
  static int STD_CHESS_W_PIECES_AMOUNT = 16;

  // testa se o tabuleiro tem 64 casas apos criar o obj
  @Test
  void testBoardSizeAfterCreation() {
    try {
      Board b = new Board();
      assertEquals(b.getBoardSize(), STD_CHESS_BOARD_SIZE);
    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  // testa se o tabuleiro tem 32 peças (16 brancas e 16 pretas)
  @Test
  void testPiecesAfterCreation() {
    try {

      Board b = new Board();
      assertEquals(STD_CHESS_PIECES_AMOUNT, b.getTotalPiecesAmount());
      assertEquals(STD_CHESS_B_PIECES_AMOUNT, b.getBlackPiecesAmount());
      assertEquals(STD_CHESS_W_PIECES_AMOUNT, b.getWhitePiecesAmount());

    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  // testa as casas que começam vazias
  // {a-h}{5-6} 
  @Test
  void testEmptyCellsAfterCreation(){
    try {

      Board b = new Board();

      for(char c = 'a'; c <= 'h'; c++){
        for(int row = 3; row <= 6; row++){
          String cell = c + Integer.toString(row);
          assertEquals("", b.whatsOnCell(cell));
        }
      }

    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  // testa a posicao initial dos peoes
  @Test
  void testPawnsPositionAfterCreation() {
    try {

      Board b = new Board();

      Set<String> validPositions = this.pawnValidPos();
      for (String cell : validPositions) {
        String pieceOnCell = b.whatsOnCell(cell);
        assertTrue(pieceOnCell == "w_pawn" || pieceOnCell == "b_pawn");
      }

    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  // similar ao teste dos peoes, mas para as torres
  @Test
  void testRooksPositionAfterCreation() {
    try {

      Board b = new Board();

      Set<String> validPositions = this.rookValidPos();
      for (String cell : validPositions) {
        String pieceOnCell = b.whatsOnCell(cell);
        assertTrue(pieceOnCell == "w_rook" || pieceOnCell == "b_rook");
      }

    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  @Test
  void testKnightsPositionAfterCreation() {
    try {

      Board b = new Board();

      Set<String> validPositions = this.knightValidPos();
      for (String cell : validPositions) {
        String pieceOnCell = b.whatsOnCell(cell);
        assertTrue(pieceOnCell == "w_knight" || pieceOnCell == "b_knight");
      }

    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  @Test
  void testBishopsPositionAfterCreation() {
    try {

      Board b = new Board();

      Set<String> validPositions = this.bishopsValidPos();
      for (String cell : validPositions) {
        String pieceOnCell = b.whatsOnCell(cell);
        assertTrue(pieceOnCell == "w_bishop" || pieceOnCell == "b_bishop");
      }

    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  @Test
  void testQueensPositionAfterCreation() {
    try {

      Board b = new Board();
      assertEquals("w_queen", b.whatsOnCell("d1"));
      assertEquals("b_queen", b.whatsOnCell("d8"));

    } catch (Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  @Test
  void testKingsPositionAfterCreation() {
    try {

      Board b = new Board();
      assertEquals("w_king", b.whatsOnCell("e1"));
      assertEquals("b_king", b.whatsOnCell("e8"));

    } catch (

    Exception e) {
      fail("unexpected exception...:" + e.getMessage());
    }
  }

  // auxiliar functions
  private Set<String> pawnValidPos() {
    Set<String> valids = new HashSet<>();
    // white pawns
    valids.add("a2");
    valids.add("b2");
    valids.add("c2");
    valids.add("d2");
    valids.add("e2");
    valids.add("f2");
    valids.add("g2");
    valids.add("h2");

    // black pawns
    valids.add("a7");
    valids.add("b7");
    valids.add("c7");
    valids.add("d7");
    valids.add("e7");
    valids.add("f7");
    valids.add("g7");
    valids.add("h7");

    return valids;
  }

  private Set<String> rookValidPos() {
    Set<String> valids = new HashSet<>();
    // white rook
    valids.add("a1");
    valids.add("h1");

    // black rook
    valids.add("a8");
    valids.add("h8");

    return valids;
  }

  private Set<String> knightValidPos() {
    Set<String> valids = new HashSet<>();
    // white knight
    valids.add("b1");
    valids.add("g1");

    // black knight
    valids.add("b8");
    valids.add("g8");

    return valids;
  }

  private Set<String> bishopsValidPos() {
    Set<String> valids = new HashSet<>();
    // white bish
    valids.add("c1");
    valids.add("f1");

    // black bish
    valids.add("c8");
    valids.add("f8");

    return valids;
  }
}
