package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public abstract class BasePiece {
  protected boolean alive;

  public abstract boolean canMoveTo(BaseCell src, BaseCell dst);

  public abstract boolean isW();

  public abstract boolean isB();

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
