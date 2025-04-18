package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.exception.IllegalChessMovementException;

public class BKnight extends BasePiece{
  public BKnight(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo (BaseCell dst){
    return (
        (Math.abs(this.position.getRow() - dst.getRow()) == 2) && 
        (Math.abs(this.position.getCol() - dst.getCol()) == 1)
    ) || (
        (Math.abs(this.position.getCol() - dst.getCol()) == 2) && 
        (Math.abs(this.position.getRow() - dst.getRow()) == 1)
    );
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

