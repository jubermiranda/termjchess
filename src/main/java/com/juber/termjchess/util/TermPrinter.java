package com.juber.termjchess.util;

import com.juber.termjchess.model.BoxChar;

import java.lang.IllegalArgumentException;

public class TermPrinter {

  private int boardSize;
  private int cellSize;
  private int cellSpacing;

  private int bStartRow;
  private int bStartCol;

  public TermPrinter(int cellSize, int cellSpacing, int boardStartR, int boardStartC) {
    this.cellSize = cellSize + 2*cellSpacing;
    this.cellSpacing = cellSpacing;
    this.boardSize = this.calcBoardSize();
    this.bStartRow = boardStartR;
    this.bStartCol = boardStartC;
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

  public void printBoard(char[][] out) {
    try {
      this.checkMinimunSize(out);
    } catch (IllegalArgumentException e) {
      // Drawn nothing
      return;
    }
    int endRow = this.bStartRow + boardSize - 1;
    int endCol = this.bStartCol + boardSize - 1;

    this.drawnSprite0(this.bStartRow, this.bStartCol, out);
    this.drawnSprite1(this.bStartRow, this.bStartCol, out);
    this.drawnSprite2(this.bStartRow, this.bStartCol, out);
    this.drawnSprite3(this.bStartRow, this.bStartCol, out);
  }

  public void drawSprite(char[][]sprite, int cellRow, int cellCol, char[][]out) throws IllegalArgumentException{
    if(cellRow < 0 || cellRow > 7)
      throw new IllegalArgumentException("invalid cell row");
    if(cellCol < 0 || cellCol > 7)
      throw new IllegalArgumentException("invalid cell col");
    if(!this.checkSprite(sprite))
      throw new IllegalArgumentException("sprite greather than cell size");

    int startRow = (cellRow * (this.cellSize + 1) + 1) + this.bStartRow + this.cellSpacing;
    int startCol = (cellCol * (this.cellSize + 1) + 1) + this.bStartCol + this.cellSpacing;

    int row = startRow;
    for(int i = 0; i < sprite.length; i++){
      int col = startCol;
      for(int j=0; j < sprite[i].length; j++){
        out[row][col++] = sprite[i][j];
      }
      row++;
    }
  }

  //
  //
  //
  //
  // private ----------------------------------------

  private void drawnSprite0(int row, int col, char[][]out){
    int endRow = row + this.cellSize + 1;
    int endCol = col + this.cellSize + 1;

    for(int i=1; i < endRow; i++){
      out[row][col+i] = BoxChar.H_LINE;
      out[endRow][col+i] = BoxChar.H_LINE;

      out[row + i][col] = BoxChar.V_LINE;
      out[row + i][endCol] = BoxChar.V_LINE;
    }
    out[row][col] = BoxChar.TL_CORNER;
    out[row][endCol] = BoxChar.T_TEE;
    out[endRow][col] = BoxChar.L_TEE;
    out[endRow][endCol] = BoxChar.CROSS;
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

  private void checkMinimunSize(char[][]out) throws IllegalArgumentException{
    if(out == null)
      throw new IllegalArgumentException("null output");
    int row = this.bStartRow;
    int col = this.bStartCol;
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

  private boolean checkSprite(char[][]sprite){
    if(sprite.length+(this.cellSpacing*2) > this.cellSize)
      return false;
    for(int i=0; i < sprite.length; i++){
      if(sprite[i].length + (this.cellSpacing*2) > this.cellSize)
        return false;
    }
    return true;
  }
}
