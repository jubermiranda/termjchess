package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class BKing extends King{
  public BKing(BaseCell pos) {
    super(pos);
  }

  @Override 
  public boolean isW(){
    return false;
  }

  @Override 
  public boolean isB(){
    return true;
  }

  @Override
  public String getName(){
    return "Black King";
  }

}

