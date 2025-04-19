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
    cell = BaseCell.createCell(1, 3);
    if(cell == null)
      fail("error creating cell");
  }

  @Test
  void testPawnCanMoveOnceCellForward(){
    Pawn pawn = new WPawn(cell);
    // cria nova casa uma linha a cima
    BaseCell newCell = createCell(cell.getRow + 1, cell.getCol);
    assertTrue(pawn.canMoveTo(newCell));
  }

  @Test
  void testPawnCanMoveTwoCellsIfFirstMove() {
    Pawn pawn = new WPawn(cell);

    // cria nova casa duas linhas para cima
    BaseCell newCell = createCell(cell.getRow + 2, cell.getCol);
    assertTrue(pawn.canMoveTo(newCell));
  }

  @Test 
  void testPawnCanMoveOneOrTwoCellsFirstMove(){
    Pawn pawn = new WPawn(cell);
    
    // verifica se o peao tem duas opcoes de movimento
    assertEquals(pawn.getValidMoves().size(), 2);

    try {
      pawn.moveTo(cell.getRow() + 1, cell.getCol());
    } catch (IllegalChessMovementException e){
      fail("unexpected error moving pawn");
    }

    // verifica se tem apenas uma opcao de movimento apos o primeiro
    assertEquals(pawn.getValidMoves().size(), 1);
  }

  @Test
  void testPawnCanNOTmoveDiagonal(){
    Pawn pawn = new WPawn(cell);
    BaseCell newCell;
    // diagonal direita
    newCell = createCell(cell.getRow + 1, cell.getCol + 1);
    assertThrows(IllegalChessMovementException.class, ()->{
      pawn.moveTo(newCell);
    });

    // diagonal esquerda
    newCell = createCell(cell.getRow + 1, cell.getCol - 1);
    assertThrows(IllegalChessMovementException.class, ()->{
      pawn.moveTo(newCell);
    });
  }

  @Test
  void testPawnCanNOTmoveLeftRight(){
    Pawn pawn = new WPawn(cell);
    BaseCell newCell;

    // direita
    newCell = createCell(cell.getRow, cell.getCol + 1);
    assertThrows(IllegalChessMovementException.class, ()->{
      pawn.moveTo(newCell);
    });

    // esquerda
    newCell = createCell(cell.getRow, cell.getCol - 1);
    assertThrows(IllegalChessMovementException.class, ()->{
      pawn.moveTo(newCell);
    });
  }

  // testa se o peao nao pode mais se mover duas casas para frente
  // depois de ter feito o primeiro movimento
  @Test
  void testPawnCanNOTmoveTwoCellsForwardAfterFirstMove(){
    Pawn pawn = new WPawn(cell);

    // faz o primeiro movimento
    BaseCell newCell = createCell(cell.getRow() + 1, cell.getCol());
    try {

      pawn.moveTo(newCell);

    } catch(IllegalChessMovementException e){
      fail("unexpected error moving pawn");
    }

    //tenta mover duas casas para frente apos ja ter feito o primeiro movimento
    newCell = createCell(newCell.getRow() + 2, newCell.getCol());
    assertThrows(IllegalChessMovementException.class, ()->{
      pawn.moveTo(newCell);
    });

  }
}
