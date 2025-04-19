package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class King extends BasePiece{
  public King(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo (BaseCell dst){
    return (
        !this.position.isEquals(dst)
    ) && (
        BaseCell.rowDistance(this.position, dst) <= 1 && 
        BaseCell.colDistance(this.position, dst) <= 1 
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
  public ArrayList<String> getValidMoves(){
    ArrayList<String> result = new ArrayList<>();

    int row = this.position.getRow();
    int col = this.position.getCol();
    if(row + 1 < 8){
      result.add(BaseCell.cellName(row+1, col));
      if(col + 1 < 8)
        result.add(BaseCell.cellName(row+1, col+1));
      if(col - 1 >= 0)
        result.add(BaseCell.cellName(row+1, col-1));
    }
    if(row - 1 >= 0){
      result.add(BaseCell.cellName(row-1, col));
      if(col + 1 < 8)
        result.add(BaseCell.cellName(row-1, col+1));
      if(col - 1 >= 0)
        result.add(BaseCell.cellName(row-1, col-1));
    }
    if(col + 1 < 8)
      result.add(BaseCell.cellName(row, col+1));
    if(col - 1 >= 0)
      result.add(BaseCell.cellName(row, col-1));

    return result;
  }
}
