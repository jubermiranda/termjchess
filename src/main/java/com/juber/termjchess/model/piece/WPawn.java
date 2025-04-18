package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.exception.IllegalChessMovementException;

public class WPawn extends BasePiece{
  public WPawn(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo (BaseCell dst){
    return false;
  }

  @Override
  public void moveTo(BaseCell dst) throws IllegalChessMovementException{
    return;
  }

  @Override 
  public boolean isW(){
    return true;
  }

  @Override 
  public boolean isB(){
    return !this.isW();
  }

}

