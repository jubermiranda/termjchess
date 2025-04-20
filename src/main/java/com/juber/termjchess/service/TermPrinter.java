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
    this.boardSize = this.calcBoardSize();
  }

  public int getBoardSize(){
    return this.boardSize;
  }

  public static void clearFrame(char[][] out, int startRow, int startCol, int endRow, int endCol) {
    for(int i=startRow; i < endRow; i++){
      for(int j=startCol; j < endCol; j++){
        out[i][j] = ' ';
      }
    }
  }

  public void printBoard(char[][] out, int startRow, int startCol) {
    try {
      this.checkMinimunSize(out,startRow, startCol);
    } catch (IllegalArgumentException e) {
      // Drawn nothing
      return;
    }
    int endRow = startRow + boardSize - 1;
    int endCol = startCol + boardSize - 1;

    this.drawnSprite0(startRow, startCol, out);
    this.drawnSprite1(startRow, startCol, out);
    this.drawnSprite2(startRow, startCol, out);
    this.drawnSprite3(startRow, startCol, out);
  }

  private void drawnSprite0(int row, int col, char[][]out){
    int endRow = row + this.cellSize + 1;
    int endCol = col + this.cellSize + 1;
    out[row][col] = BoxChar.TL_CORNER;
    out[row][endCol] = BoxChar.T_TEE;
    out[endRow][col] = BoxChar.L_TEE;
    out[endRow][endCol] = BoxChar.CROSS;

    for(int i=1; i < endRow; i++){
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
      int endRow = startRow + cellSize + 1;
      int endCol = startCol + cellSize;

      if(i < 6){
        out[startRow][endCol] = BoxChar.T_TEE;
        out[endRow][endCol] = BoxChar.CROSS;
      } else{
        out[startRow][endCol] = BoxChar.TR_CORNER;
        out[endRow][endCol] = BoxChar.R_TEE;
      }
      for(int j = 0; j < cellSize; j++){
        out[startRow][startCol+ j] = BoxChar.H_LINE;
        out[endRow][startCol+ j] = BoxChar.H_LINE;
        out[startRow + j + 1][endCol] = BoxChar.V_LINE;
      }
      startCol = startCol + cellSize + 1;
    }
  }

  private void drawnSprite2(int row, int col, char[][]out){
    int startRow = row + cellSize + 2;
    int startCol = col;

    for(int i = 0; i < 7; i++){
      int endRow = startRow + cellSize;
      int endCol = startCol + cellSize + 1;

      if(i < 6){
        out[endRow][startCol] = BoxChar.L_TEE;
        out[endRow][endCol] = BoxChar.CROSS;
      } else{
        out[endRow][startCol] = BoxChar.BL_CORNER;
        out[endRow][endCol] = BoxChar.B_TEE;
      }
      for(int j = 0; j < cellSize; j++){
        out[startRow + j][startCol] = BoxChar.V_LINE;
        out[startRow + j][endCol] = BoxChar.V_LINE;
        out[endRow][startCol +j +1] = BoxChar.H_LINE;
      }
      startRow = startRow + cellSize + 1;
    }
  }

  private void drawnSprite3(int row, int col, char[][]out){
    int startRow = row + cellSize + 2;
    int startCol = col + cellSize + 2;
    for(int i=0; i < 7; i++){
      for(int j=0; j < 7; j++){
        int endRow = startRow + cellSize;
        int endCol = startCol + cellSize;
        if(j < 6){

          if(i < 6)
            out[endRow][endCol] = BoxChar.CROSS;
          else
            out[endRow][endCol] = BoxChar.B_TEE;

        }else{

          if(i < 6)
            out[endRow][endCol] = BoxChar.R_TEE;
          else
            out[endRow][endCol] = BoxChar.BR_CORNER;

        }
        for(int k = 0; k < cellSize; k++){
          out[endRow][startCol+k] = BoxChar.H_LINE;
          out[startRow + k][endCol] = BoxChar.V_LINE;
        }
        startCol = startCol + cellSize + 1;
      }
      startCol = col + cellSize + 2;
      startRow = startRow + cellSize + 1;
    }
  }

  private void checkMinimunSize(char[][]out, int row, int col) throws IllegalArgumentException{
    if(out == null)
      throw new IllegalArgumentException("null output");
    if(out.length < this.boardSize + row)
      throw new IllegalArgumentException("out lines less than expected");
    for(int i=0; i < this.boardSize; i++){
      if(out[row + i].length < this.boardSize + col)
        throw new IllegalArgumentException("out columns less than expected");
    }
  }

  private int calcBoardSize(){
    int total = 8*(this.cellSize + 2*this.cellSpacing + 1) + 1;
    return total;
  }
}
