package com.juber.termjchess.util;

import com.juber.termjchess.model.BoxChar;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.service.ChessSpriteXProvider;

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

  public TermPrinter(int cellSize, int cellSpacing) {
    this.cellSize = cellSize + 2*cellSpacing;
    this.cellSpacing = cellSpacing;
    this.boardSize = this.calcBoardSize();
    this.bStartRow = 6;
    this.bStartCol = 9;
    this.hintCells = new HashSet<String>();
  }

  public int[] getFrameSize(){
    int []result = new int[2];
    result[0] =  this.boardSize + 6;
    result[1] =  this.boardSize + 9 + 100;

    return result;
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

    this.drawnBoardSprite0(this.bStartRow, this.bStartCol, out);
    this.drawnBoardSprite1(this.bStartRow, this.bStartCol, out);
    this.drawnBoardSprite2(this.bStartRow, this.bStartCol, out);
    this.drawnBoardSprite3(this.bStartRow, this.bStartCol, out);
    this.drawnLabelSprites(out);
    this.drawnRightPanel(out);
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

    for(int j = sCol + 1; j < eCol; j++){
      out[sRow][j] = BoxChar.H_LINE;
      out[eRow][j] = BoxChar.H_LINE;
    }
    for(int j = sRow + 1; j < eRow; j++){
      out[j][sCol] = BoxChar.V_LINE;
      out[j][eCol] = BoxChar.V_LINE;
    }
  }

  public static void printBuffer(char[][] buffer){
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


  public static void printProgressBar() {
    final int totalWidth = 100;
    final Random random = new Random();
    final String block = String.valueOf(BoxChar.BLOCK);
    final String hLine = String.valueOf(BoxChar.H_LINE);
    final int spacingLeft = 21;
    final int spacingTop = 8;

    // spacing top
    System.out.printf("%s", "\n".repeat(spacingTop));

    // Top border
    System.out.printf("%s%s%s%s%s%s%n",
            BoxChar.PURPLE,
            " ".repeat(spacingLeft),
            BoxChar.TL_CORNER,
            hLine.repeat(totalWidth + 2),
            BoxChar.TR_CORNER,
            BoxChar.RESET
    );

    // Middle line (initial)
    System.out.printf("%s%s│ %s%s│ 0.00%%%s%n",
            BoxChar.PURPLE,
            " ".repeat(spacingLeft),
            " ".repeat(totalWidth),
            BoxChar.PURPLE,
            BoxChar.RESET
    );

    // Bottom border
    System.out.printf("%s%s%s%s%s%s%n",
            BoxChar.PURPLE,
            " ".repeat(spacingLeft),
            BoxChar.BL_CORNER,
            hLine.repeat(totalWidth + 2),
            BoxChar.BR_CORNER,
            BoxChar.RESET
    );

    for (int progress = 0; progress <= 100; progress+= 10) {
        int filled = progress * totalWidth / 100;
        int empty = totalWidth - filled;

        String color = progress < 33 ? BoxChar.BLUE :
                       progress < 66 ? BoxChar.RED : BoxChar.GREEN;

        // Move cursor up 2 lines to reach the progress bar line
        System.out.print("\033[2A");

        // Rewrite progress line
        System.out.printf("%s%s│ %s%s%s%s│ %6.2f%%%s%n",
                BoxChar.PURPLE,
                " ".repeat(spacingLeft),
                color, block.repeat(filled),
                BoxChar.RESET + " ".repeat(empty),
                BoxChar.PURPLE,
                (float) progress,
                BoxChar.RESET
        );

        System.out.print("\033[1B");
        System.out.flush();

        int minSleep = 100 + (int)(0.1 * progress);
        int sleepTime = random.nextInt(minSleep + 99) + minSleep;
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

  private void drawnBoardSprite0(int row, int col, char[][]out){
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

  private void drawnBoardSprite1(int row, int col, char[][]out){
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

  private void drawnBoardSprite2(int row, int col, char[][]out){
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

  private void drawnBoardSprite3(int row, int col, char[][]out){
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

  private void drawnLabelSprites(char[][] out){
    int startRow;
    int startCol;
    char[][] sprite;

    for(int i = 0; i < 8; i++){
      sprite = ChessSpriteXProvider.LabelSprite('r', i);
      startRow = 12 + (i*(this.cellSize + 1 ));
      startCol = 0;
      this.drawSprite(sprite, startRow, startCol, out);

      sprite = ChessSpriteXProvider.LabelSprite('c', i);
      startRow = 0;
      startCol = 12 + (i*(this.cellSize + 1 ));
      this.drawSprite(sprite, startRow, startCol, out);
    }
  }

  private void drawnRightPanel(char[][]out){
    int panelStartRow = 0;
    int panelStartCol = this.boardSize + 12;
    this.drawSprite(
        ChessSpriteXProvider.LogoSprite,
        panelStartRow + 8,
        panelStartCol + 20,
        out
    );
    TermPrinter.drawnVLine(panelStartRow, panelStartCol, this.boardSize + 6, out);
  }

  private static void drawnVLine(int startRow, int startCol, int size, char[][]out){
    for(int i=0; i < size; i++){
      if(out.length > i+startRow){
        out[startRow+i][startCol] = BoxChar.V_LINE;
      }
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
    int total = 8*(this.cellSize +  1) + 1;
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
