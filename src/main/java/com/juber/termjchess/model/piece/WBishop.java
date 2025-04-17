package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class WBishop extends BasePiece{
  public WBishop() {
    this.alive = true;
  }

  @Override
  public boolean canMoveTo (BaseCell src, BaseCell dst){
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

  @Override 
  public char[][] sprite(){
    return null;
  }

}

