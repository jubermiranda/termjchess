package com.juber.termjchess.model.board;

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

}
