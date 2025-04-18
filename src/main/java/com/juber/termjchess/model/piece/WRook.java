package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.exception.IllegalChessMovementException;

public class WRook extends BasePiece {
  public WRook(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo(BaseCell dst) {
    return (!this.position.isEquals(dst)) &&
        (this.position.isSameCol(dst) || this.position.isSameRow(dst));
  }

  @Override
  public void moveTo(BaseCell dst) throws IllegalChessMovementException {
    if (this.canMoveTo(dst))
      this.position = dst;
    else
      throw new IllegalChessMovementException("cant move to this position");
  }

  @Override
  public boolean isW() {
    return true;
  }

  @Override
  public boolean isB() {
    return !this.isW();
  }

}
