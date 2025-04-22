package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class BasePiece {
  protected BaseCell position;

  public abstract boolean canMoveTo(BaseCell dst);

  public abstract void moveTo(BaseCell dst) throws IllegalChessMovementException;

  public abstract boolean isW();

  public abstract boolean isB();

  public abstract ArrayList<String> getValidMoves();

  public abstract ArrayList<String> getTrace(BaseCell dst);

  public boolean isSameType(BasePiece other){
    return (this.isW() == other.isW());
  }

  public boolean isAlive(){
    return (this.position != null);
  }

  public void kill(){
    this.position = null;
  }

  public String getPositionName(){
    if(this.position != null)
      return this.position.cellName();
    return "";
  }

}
