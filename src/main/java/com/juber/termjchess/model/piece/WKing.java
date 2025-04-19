package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class WKing extends King{
  public WKing(BaseCell pos) {
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

