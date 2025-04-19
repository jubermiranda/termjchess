package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class Pawn extends BasePiece{
  private boolean has_moved;

  public Pawn(BaseCell pos) {
    this.position = pos;
    this.has_moved = false;
  }

  @Override
  public boolean canMoveTo (BaseCell dst){
    return false;
  }

  @Override
  public void moveTo(BaseCell dst) throws IllegalChessMovementException{
    if(this.canMoveTo(dst)){
      if(!this.has_moved)
        this.has_moved = true;
      this.position = dst;
    }
    else
      throw new IllegalChessMovementException("cant move to this position");
  }

  @Override
  public ArrayList<String> getValidMoves(){
    return new ArrayList<>();
  }

}

