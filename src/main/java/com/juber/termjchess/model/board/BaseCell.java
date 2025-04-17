package com.juber.termjchess.model;

public abstract class BaseCell {
  protected int row;
  protected int col;

  protected abstract boolean isW();

  protected abstract boolean isB();

  public String cellName() {
    char c = (char) ((int) 'a' + this.col);
    String result = c + Integer.toString(this.row + 1);

    return result;
  }

}
