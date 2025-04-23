package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class Pawn extends BasePiece{
  protected boolean has_moved;

  public Pawn(BaseCell pos) {
    this.position = pos;
    this.has_moved = false;
  }

  @Override
  public void moveTo(BaseCell dst) throws IllegalChessMovementException{
    if(this.canMoveTo(dst)){
      if(!this.has_moved)
        this.has_moved = true;
      this.position = dst;
    } else{
      String msg = "Cant move Pawn from " + this.position.cellName();
      msg += " to " + dst.cellName() + ".";
      throw new IllegalChessMovementException(msg);
    }
  }

  @Override
  public ArrayList<String> getValidMoves(){
    ArrayList<String> result = new ArrayList<>();

    int row = this.position.getRow();
    int col = this.position.getCol();

    if(this.has_moved){
      if(row < 7){
        result.add(BaseCell.cellName(row+1, col));
      }
    } else {
      if(row < 7)
        result.add(BaseCell.cellName(row+1, col));
      if(row < 6)
        result.add(BaseCell.cellName(row+2, col));
    }

    return result;
  }

  public boolean canCapture(BaseCell cell){
    int inc = (this.isW())?-1:1;
    
    int rowDiff = this.position.getRow() - cell.getRow();
    int colDiff = this.position.getCol() - cell.getCol();
    rowDiff = rowDiff * inc;

    boolean result = (rowDiff == 1 && Math.abs(colDiff) == 1);

    return result;
  }

}

