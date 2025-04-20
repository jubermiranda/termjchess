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
    if(!this.canMoveTo(dst))
      return result;
    String position = BaseCell.relativePos(this.position, dst);

    BaseCell cell;
    int row = this.position.getRow();
    int col = this.position.getCol();
    int aux = 0;
    do{
      if(position.equals("TOP"))
        row = row + 1;
      else if(position.equals("BOT"))
        row = row - 1;
      else if(position.equals("RIGHT"))
        col = col + 1;
      else if(position.equals("LEFT"))
        col = col - 1;
      else 
        break;

      cell = BaseCell.createCell(row, col);
      if(!cell.isEquals(dst))
        result.add(cell.cellName());
      aux++;
    } while(!cell.isEquals(dst) && aux < 8);

    return result;
  }

  public boolean has_moved(){
    return this.has_moved;
  }
}
