package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

public class WPawn extends Pawn{
  public WPawn(BaseCell pos) {
    super(pos);
  }

  @Override
  public boolean canMoveTo(BaseCell dst){
    if(
        this.position.isEquals(dst) || 
        BaseCell.colDistance(this.position, dst) != 0 ||
        dst.getRow() - this.position.getRow() <= 0
      ){
      return false;
    }

    return (
        (this.has_moved && dst.getRow() - this.position.getRow() == 1) ||
        (!this.has_moved && dst.getRow() - this.position.getRow() <= 2 )
    );
  }

  @Override 
  public boolean isW(){
    return true;
  }

  @Override 
  public boolean isB(){
    return false;
  }
}

