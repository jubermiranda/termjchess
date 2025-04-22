package com.juber.termjchess.util;

import com.juber.termjchess.model.BoxChar;
import com.juber.termjchess.model.board.BaseCell;

import java.lang.IllegalArgumentException;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class TermPrinter {

  private int boardSize;
  private int cellSize;
  private int cellSpacing;

  private int bStartRow;
  private int bStartCol;

  private Set<String> hintCells;

  public TermPrinter(int cellSize, int cellSpacing, int boardStartR, int boardStartC) {
    this.cellSize = cellSize + 2*cellSpacing;
    this.cellSpacing = cellSpacing;
    this.boardSize = this.calcBoardSize();
    this.bStartRow = boardStartR;
    this.bStartCol = boardStartC;
    this.hintCells = new HashSet<String>();
  }

  public int getBoardSize(){
    return this.boardSize;
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

  public void updateHints(ArrayList<String>cells){
    this.hintCells.clear();
    for(String s: cells){
      this.hintCells.add(s);
    }
  }

  public void updateHints(){
    this.hintCells.clear();
  }

  public void drawPiece(
      char[][]sprite,
      int cellRow,
      int cellCol,
      char[][]out

  ) throws IllegalArgumentException{

    if(cellRow < 0 || cellRow > 7)
      throw new IllegalArgumentException("invalid cell row");
    if(cellCol < 0 || cellCol > 7)
      throw new IllegalArgumentException("invalid cell col");
    if(!this.checkPieceSprite(sprite))
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

  public static void clearFrame(
      char[][] out,
      int startRow,
      int startCol,
      int endRow,
      int endCol
  ) {
    for(int i=startRow; i < endRow; i++){
      for(int j=startCol; j < endCol; j++){
        out[i][j] = ' ';
      }
    }
  }

  public static void drawSprite(char[][]sprite, int startRow, int startCol, char[][]out){
    if(sprite.length + startRow > out.length)
      return;

    for(int i=0; i < sprite.length; i++){
      for(int j=0; j < sprite[i].length; j++){
        out[startRow + i][startCol + j] = sprite[i][j];
      }
    }
  }

  public static void drawBox(char[][]out, int sRow, int sCol, int eRow, int eCol){
    out[sRow][sCol] = BoxChar.TL_CORNER;
    out[sRow][eCol] = BoxChar.TR_CORNER;
    out[eRow][sCol] = BoxChar.BL_CORNER;
    out[eRow][eCol] = BoxChar.BR_CORNER;
    for(int j=sCol + 1; j < eCol; j++){
      out[sRow][sCol+j] = BoxChar.H_LINE;
      out[eRow][sCol+j] = BoxChar.H_LINE;
    }
    for(int j=sRow + 1; j < eRow; j++){
      out[sRow+j][sCol] = BoxChar.V_LINE;
      out[sRow+j][eCol] = BoxChar.V_LINE;
    }
  }

  public void printBuffer(char[][] buffer){
    for(int i=0; i < buffer.length; i++){
      for(int j=0; j < buffer[i].length; j++){
        // String color = getApropriateColor(i, j);
        // String bg = getApropriateBg(i, j);
        // System.out.print(color);
        // System.out.print(bg);
        System.out.print(buffer[i][j]);
      }
      System.out.println();
    }
    //System.out.print(BoxChar.RESET);
    //this.updateHints();
  }

  public static void moveCursor(int row, int col) {
    System.out.print("\033[" + row + ";" + col + "H");
  }

  public static void clearScreen(){
    System.out.print("\033[2J");
    System.out.flush();
  }

  public static void clearLine(){
    System.out.print("\033[2K");
  }


  public static void printProgressBar(){
    int totalWidth = 100;
    clearLine();

    String h_line = String.valueOf(BoxChar.H_LINE);
    String block = String.valueOf(BoxChar.BLOCK);

    System.out.printf("%s%s%s%s%s%n", 
        BoxChar.PURPLE, 
        BoxChar.TL_CORNER,
        h_line.repeat(totalWidth + 2),
        BoxChar.TR_CORNER,
        BoxChar.RESET
    );

    Random random = new Random();

    for (int progress = 0; progress <= 100; progress++) {
      int filled = progress;
      int empty = totalWidth - progress;

      String color;
      if (progress < 33) {
          color = BoxChar.BLUE;
      } else if (progress < 66) {
          color = BoxChar.RED;
      } else {
          color = BoxChar.PURPLE;
      }

      clearLine();

      System.out.printf("%s│%s%s%s%s │ %.2f%%%s%n",
            BoxChar.PURPLE,
            color, block.repeat(filled),
            " ".repeat(empty),
            BoxChar.PURPLE,
            (float) progress,
            BoxChar.RESET
      );

      if (progress == 100) {
        System.out.printf("%s%s%s%s%s%n", 
            BoxChar.PURPLE,
            BoxChar.BL_CORNER,
            h_line.repeat(totalWidth + 2),
            BoxChar.BR_CORNER,
            BoxChar.RESET
        );
      } else {
        clearLine();
        System.out.printf("\033[2A%s%s%s%s%s%n", 
            BoxChar.PURPLE,
            BoxChar.TL_CORNER,
            h_line.repeat(totalWidth + 2),
            BoxChar.TR_CORNER,
            BoxChar.RESET
        );
      }
      int minSleep = 1 + (int)(0.1 * progress);
      int sleepTime = random.nextInt(minSleep + 1) + minSleep;
      try {
          Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          System.err.println("Progress interrupted: " + e.getMessage());
          break;
      }
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

  private boolean checkPieceSprite(char[][]sprite){
    if(sprite.length+(this.cellSpacing*2) > this.cellSize)
      return false;
    for(int i=0; i < sprite.length; i++){
      if(sprite[i].length + (this.cellSpacing*2) > this.cellSize)
        return false;
    }
    return true;
  }

  private String getApropriateColor(int i, int j){
    String result = "";
    if(i > this.bStartRow && j > bStartCol){
      String cellName = getCellNameRelative(i, j);

      if(cellName.equals("BORDER")){
        // custom border color can be impl here
        return "";
      }else if(cellName.equals("HINT")){
        return "";
      } else if(cellName.equals("")){
        return "";
      } else {
        // chose cell foreground color
        BaseCell cell = BaseCell.createCell(cellName);
        if(cell == null)
          return "";
        result = (cell.isW())?BoxChar.BLACK:BoxChar.PURPLE;
      }
    }
    return result;
  }

  private String getApropriateBg(int i, int j){
    String result = "";
    if(i > this.bStartRow && j > bStartCol){
      String cellName = getCellNameRelative(i, j);
      boolean toHint = this.hintCells.contains(cellName);

      if(cellName.equals("BORDER")){

        // custom border background color can be impl here
        return "";

      }else if(cellName.equals("HINT")){

        if(toHint)
          return BoxChar.YELLOW;
        else
          return "";

      } else if(cellName.equals("")){
        return "";

      } else {
        // chose cell background color
        BaseCell cell = BaseCell.createCell(cellName);
        if(cell == null)
          return "";
        result = (cell.isW())?BoxChar.BG_PURPLE:BoxChar.BG_BLACK;
      }
    }
    return result;

  }

  private String getCellNameRelative(int i, int j){
    int row = i / (this.cellSize + 2*this.cellSpacing + 1);
    int col = i / (this.cellSize + 2*this.cellSpacing + 1);
    if(row < 7 && col < 7){
      int r_row = i % (this.cellSize + 2*this.cellSpacing + 1);
      int r_col = i % (this.cellSize + 2*this.cellSpacing + 1);
      if(r_row == 0 || r_col == 0)
        return "BORDER";
      if(
          (r_row <= this.cellSpacing || r_col <= this.cellSpacing) ||
          (r_row > this.cellSpacing + this.cellSize ||r_col > this.cellSpacing + this.cellSize)
      ){
        return "HINT";
      }

      return BaseCell.cellName(row, col);
    }

    return "";
  }

}
