package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class Knight extends BasePiece{
  public Knight(BaseCell pos) {
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
  public ArrayList<String> getValidMoves(){
    int row = this.position.getRow();
    int col = this.position.getCol();
    ArrayList<String> result = new ArrayList<String>();

    if (row + 2 < 8) {
      if (col + 1 < 8)
        result.add(BaseCell.cellName(row + 2, col + 1));
      if (col - 1 >= 0)
        result.add(BaseCell.cellName(row + 2, col - 1));
    }
    if (row - 2 >= 0) {
      if (col + 1 < 8)
        result.add(BaseCell.cellName(row - 2, col + 1));
      if (col - 1 >= 0)
        result.add(BaseCell.cellName(row - 2, col - 1));
    }
    if (col + 2 < 8) {
      if (row + 1 < 8)
        result.add(BaseCell.cellName(row + 1, col + 2));
      if (row - 1 >= 0)
        result.add(BaseCell.cellName(row - 1, col + 2));
    }
    if (col - 2 >= 0) {
      if (row + 1 < 8)
        result.add(BaseCell.cellName(row + 1, col - 2));
      if (row - 1 >= 0)
        result.add(BaseCell.cellName(row - 1, col - 2));
    }

    return result;
  }

  @Override
  public ArrayList<String> getTrace(BaseCell dst){
    return new ArrayList<>();
  }
}

