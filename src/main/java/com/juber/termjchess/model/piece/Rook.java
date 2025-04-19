package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class Rook extends BasePiece {
  private boolean has_moved;

  public Rook(BaseCell pos) {
    this.position = pos;
    this.has_moved = false;
  }

  @Override
  public boolean canMoveTo(BaseCell dst) {
    return (!this.position.isEquals(dst)) &&
        (this.position.isSameCol(dst) || this.position.isSameRow(dst));
  }

  @Override
  public void moveTo(BaseCell dst) throws IllegalChessMovementException {
    if (this.canMoveTo(dst)){
      this.position = dst;
      if(!this.has_moved)
        this.has_moved = true;
    }
    else
      throw new IllegalChessMovementException("cant move to this position");
  }

  @Override
  public ArrayList<String> getValidMoves(){
    ArrayList<String> result = new ArrayList<>();

    int row = this.position.getRow();
    int col = this.position.getCol();
    int i = 1;
    boolean tryNext;
    do {
      tryNext = false;

      if(row + i < 8){
        tryNext = true;
        result.add(BaseCell.cellName(row+i, col));
      }
      if(row - i >= 0){
        tryNext = true;
        result.add(BaseCell.cellName(row-i, col));
      }
      if(col + i < 8){
        tryNext = true;
        result.add(BaseCell.cellName(row, col+i));
      }
      if(col - i >= 0){
        tryNext = true;
        result.add(BaseCell.cellName(row, col-i));
      }

      i++;
    } while(tryNext);

    return result;
  }

  @Override
  public ArrayList<String> getTrace(BaseCell dst){
    ArrayList<String> result = new ArrayList<String>();
    if(this.position.isEquals(dst))
      return result;
    String position = BaseCell.relativePos(this.position, dst);
    if(position == "")
      return result;

    BaseCell cell;
    int row = this.position.getRow();
    int col = this.position.getCol();
    int i = 1;
    do{
      int newRow;
      int newCol;
      if(position == "TOP")
        newRow += i;
      else if(position == "BOT")
        newRow -= i;
      else if(position == "LEFT")
        newCol -= i;
      else if(position == "RIGHT")
        newCol += i;

      cell = BaseCell.createCell(newRow, newCol);
      if(!cell.isEquals(dst))
        result.add(cell.cellName());
    } while (!cell.isEquals(dst) && i <= 8);

    return result;
  }

  public boolean has_moved(){
    return this.has_moved;
  }
}
