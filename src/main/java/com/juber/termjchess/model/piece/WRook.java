package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class WRook extends WRook{
  public BRook(BaseCell pos) {
    super(pos);
  }

  @Override 
  public boolean isW(){
    return true;
  }

  @Override 
  public boolean isB(){
    return false;
  }
}
