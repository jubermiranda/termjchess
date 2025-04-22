package com.juber.termjchess.model.board;

import com.juber.termjchess.exception.InvalidBoardCellPosition;

public abstract class BaseCell {
  protected int row;
  protected int col;

  public abstract boolean isW();

  public abstract boolean isB();

  public String cellName() {
    char c = (char) ((int) 'a' + this.col);
    String result = c + Integer.toString(this.row + 1);

    return result;
  }

  public static String cellName(int row, int col){
    if(row < 0 || row > 7 || col < 0 || col > 7)
      return "";

    char c = (char) ((int) 'a' + col);
    String result = c + Integer.toString(row + 1);

    return result;
  }

  public boolean isSameType(BaseCell other){
    return ((this.isW() && other.isW()) || (this.isB() && other.isB()));
  }

  public boolean isSameDiagonal(BaseCell other){
    return (Math.abs(this.row - other.row) == Math.abs(this.col - other.col));
  }

  public boolean isSameRow(BaseCell other){
    return (this.row == other.row);
  }

  public boolean isSameCol(BaseCell other){
    return (this.col == other.col);
  }

  public boolean isEquals(BaseCell other){
    return (this.row == other.row && this.col == other.col);
  }

  public int getRow(){
    return this.row;
  }

  public int getCol(){
    return this.col;
  }

  public static boolean isValidPosition(String pos){
    int row = getRowFromName(pos);
    int col = getColFromName(pos);
    return (row >= 0 || row <= 7 || col >= 0 || col <= 7);
  }

  public static int getColFromName(String name){
    return name.charAt(0) - 'a';
  }
  public static int getRowFromName(String name){
    return name.charAt(1) - '0' - 1;
  }

  public static int rowDistance(BaseCell src, BaseCell dst){
    return (Math.abs(src.row - dst.row));
  }
  public static int colDistance(BaseCell src, BaseCell dst){
    return (Math.abs(src.col - dst.col));
  }

  public static String relativePos(BaseCell a, BaseCell b){
    if(a.isEquals(b))
      return "";
    if( !a.isSameCol(b) && !a.isSameRow(b) && !a.isSameDiagonal(b))
      return "";

    String result = "";

    if(b.getRow() - a.getRow() > 0)
      result = "TOP";
    else if(b.getRow() - a.getRow() < 0)
      result = "BOT";

    if(b.getCol() - a.getCol() > 0)
      result += (result == "")?"RIGHT":"_RIGHT";
    else if(b.getCol() - a.getCol() < 0)
      result += (result == "")?"LEFT":"_LEFT";

    return result;
  }

  public static BaseCell createCell(int row, int col){
    BaseCell c;
    try {
      c = new BlackCell(row, col);

    } catch (InvalidBoardCellPosition e){
      try {
        c = new WhiteCell(row, col);

      } catch (InvalidBoardCellPosition e2){
        c = null;
      }
    } 

    return c;
  }

  public static BaseCell createCell(String name){
    int row = BaseCell.getRowFromName(name);
    int col = BaseCell.getColFromName(name);
    return BaseCell.createCell(row, col);
  }
}
