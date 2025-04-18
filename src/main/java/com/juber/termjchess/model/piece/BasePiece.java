package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.exception.IllegalChessMovementException;

public abstract class BasePiece {
  protected BaseCell position;

  public abstract boolean canMoveTo(BaseCell dst);

  public abstract void moveTo(BaseCell dst) throws IllegalChessMovementException;

  public abstract boolean isW();

  public abstract boolean isB();

  protected boolean isAlive(){
    return (this.position != null);
  }

  public String getPositionName(){
    if(this.position != null)
      return this.position.cellName();
    return "";
  }

  protected void kill(){
    this.position = null;
  }
}
