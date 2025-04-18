package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class BBishop extends WBishop {
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

}
