package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class BRook extends Rook{
  public BRook(BaseCell pos) {
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
    return "Black Rook";
  }
}
