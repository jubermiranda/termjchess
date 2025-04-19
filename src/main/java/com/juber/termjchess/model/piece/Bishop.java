package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class Bishop extends BasePiece {
  public Bishop(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo(BaseCell dst) {
    return (!this.position.isEquals(dst)) &&
        this.position.isSameDiagonal(dst);
  }

  @Override
  public void moveTo(BaseCell dst) throws IllegalChessMovementException {
    if (this.canMoveTo(dst))
      this.position = dst;
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
        if(col + i < 8){
          tryNext = true;
          result.add(BaseCell.cellName(row+i, col+i));
        }
        if(col - i >= 0){
          tryNext = true;
          result.add(BaseCell.cellName(row+i, col-i));
        }
      }
      if(row - i >= 0){
        if(col + i < 8){
          tryNext = true;
          result.add(BaseCell.cellName(row-i, col+i));
        }
        if(col - i >= 0){
          tryNext = true;
          result.add(BaseCell.cellName(row-i, col-i));
        }
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
      int newRow = row;
      int newCol = col;
      if(position == "TOP_RIGHT"){
        newRow += i;
        newCol += i;
      } else if(position == "TOP_LEFT"){
        newRow += i;
        newCol -= i;
      } else if(position == "BOT_RIGHT"){
        newCol -= i;
        newCol += i;
      } else {
        newCol -= i;
        newCol -= i;
      }

      cell = BaseCell.createCell(newRow, newCol);
      if(!cell.isEquals(dst))
        result.add(cell.cellName());
    } while (!cell.isEquals(dst) && i <= 8);

    return result;
  }
}
