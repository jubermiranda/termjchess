package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public abstract class BasePiece {
  protected boolean alive;

  protected abstract boolean canMoveTo(BaseCell src, BaseCell dst);

  protected abstract boolean isW();

  protected abstract boolean isB();

  protected abstract char[][] sprite();

  protected boolean isAlive(){
    return this.alive;
  }

  protected void kill(){
    this.alive = false;
  }
  protected void revive(){
    this.alive = true;
  }
}
