package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class WKnight extends Knight{
  public WKnight(BaseCell pos) {
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

