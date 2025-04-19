package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class WPawn extends Pawn{
  public WPawn(BaseCell pos) {
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

