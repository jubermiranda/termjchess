package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class BPawn extends WPawn{
  public BPawn(BaseCell pos) {
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
}

