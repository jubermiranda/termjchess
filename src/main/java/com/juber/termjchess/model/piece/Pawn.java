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
    if(!this.position.isEquals(dst))
      return false;

    boolean result = ((
          BaseCell.colDistance(this.position, dst) == 0
      ) && (
         (this.has_moved && BaseCell.rowDistance(this.position, dst) == 1)||
         (!this.has_moved && BaseCell.rowDistance(this.position, dst) <= 2)
      )
    );

    return result;
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

