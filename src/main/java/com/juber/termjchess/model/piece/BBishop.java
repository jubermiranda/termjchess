package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class BBishop extends Bishop {
  public BBishop(BaseCell pos) {
    super(pos);
  }

  @Override
  public boolean isW() {
    return false;
  }

  @Override
  public boolean isB() {
    return true;
  }

  @Override
  public String getName(){
    return "Black Bishop";
  }

}
