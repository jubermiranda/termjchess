package com.juber.termjchess.service;

import com.juber.termjchess.model.BoxChar;

import java.lang.IllegalArgumentException;

public class TermPrinter {

  private int boardSize;
  private int cellSize;
  private int cellSpacing;

  public TermPrinter(int cellSize, int cellSpacing) {
    this.cellSize = cellSize;
    this.cellSpacing = cellSpacing;
    this.calcBoardSize();
  }

  public void printBoard(char[][] out, int startRow, int startCol) {
    try {
      this.checkMinimunSize(out);
    } catch (IllegalArgumentException e) {
      // Drawn nothing
      return;
    }
    int endRow = startRow + boardSize - 1;
    int endCol = startCol + boardSize - 1;

    this.drawnSprite0(startRow, startCol, out);
    this.drawnSprite1(startRow, startCol, out);
    this.drawnSprite2(startRow, startCol, out);
  }

  private void drawnSprite0(int row, int col, char[][]out){
    int endRow = row + cellSize + 2;
    int endCol = col + cellSize + 2;
    out[row][col] = BoxChar.TL_CORNER;
    out[row][endCol] = BoxChar.TR_CORNER;
    out[endRow][col] = BoxChar.BL_CORNER;
    out[endRow][endCol] = BoxChar.BR_CORNER;

    for(int i=1; i < endRow-1; i++){
      out[row][col+i] = BoxChar.H_LINE;
      out[endRow][col+i] = BoxChar.H_LINE;

      out[row + i][col] = BoxChar.V_LINE;
      out[row + i][endCol] = BoxChar.V_LINE;
    }
  }

  private void drawnSprite1(int row, int col, char[][]out){
    int startRow = row;
    int startCol = col + cellSize + 2;

    for(int i = 0; i < 7; i++){
      int endRow = startRow + cellSize;
      int endCol = startCol + cellSize;

      if(i < 6){
        out[startRow][endCol] = BoxChar.T_TEE;
        out[endRow][endCol] = BoxChar.CROSS;
      } else{
        out[startRow][endCol] = BoxChar.TR_CORNER;
        out[endRow][endCol] = BoxChar.R_TEE;
      }
      for(int j = 0; j < cellSize; j++){
        out[startRow][startCol+ i] = BoxChar.H_LINE;
        out[endRow][startCol+ i] = BoxChar.H_LINE;
        out[startRow + i][endCol] = BoxChar.H_LINE;
      }
      startCol = startCol + cellSize + 1;
    }
  }

  private void drawnSprite2(int row, int col, char[][]out){
    int startRow = row + cellSize + 2;
    int startCol = col;

    for(int i = 0; i < 7; i++){
      int endRow = startRow + cellSize;
      int endCol = startCol + cellSize;

      if(i < 6){
        out[endRow][startCol] = BoxChar.L_TEE;
        out[endRow][endCol] = BoxChar.CROSS;
      } else{
        out[endRow][startCol] = BoxChar.BL_CORNER;
        out[endRow][endCol] = BoxChar.B_TEE;
      }
      for(int j = 0; j < cellSize; j++){
        out[startRow + i][startCol] = BoxChar.V_LINE;
        out[startRow + i][endCol] = BoxChar.V_LINE;
        out[endRow][startCol +i +1] = BoxChar.H_LINE;
      }
    }
  }
}
