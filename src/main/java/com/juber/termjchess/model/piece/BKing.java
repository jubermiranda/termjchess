package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.exception.IllegalChessMovementException;

public class BKing extends BasePiece{
  public BKing(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo (BaseCell dst){
    return false;
  }

  @Override
  public void moveTo(BaseCell dst) throws IllegalChessMovementException{
    if(this.canMoveTo(dst))
      this.position = dst;
    else
      throw new IllegalChessMovementException("cant move to this position");
  }

  @Override 
  public boolean isW(){
    return false;
  }

  @Override 
  public boolean isB(){
    return !this.isW();
  }

}

