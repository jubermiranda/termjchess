package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class WQueen extends BasePiece{
  public WQueen(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo (BaseCell dst){
    return false;
  }

  @Override 
  public boolean isW(){
    return true;
  }

  @Override 
  public boolean isB(){
    return !this.isW();
  }

}

