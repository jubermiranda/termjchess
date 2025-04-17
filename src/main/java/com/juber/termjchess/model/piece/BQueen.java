package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class BQueen extends BasePiece{
  public BQueen() {
    this.alive = true;
  }

  @Override
  public boolean canMoveTo (BaseCell src, BaseCell dst){
    return false;
  }

  @Override 
  public boolean isW(){
    return false;
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

