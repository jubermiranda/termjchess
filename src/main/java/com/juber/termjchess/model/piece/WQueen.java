package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class BQueen extends WQueen{
  public BQueen(BaseCell pos) {
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

