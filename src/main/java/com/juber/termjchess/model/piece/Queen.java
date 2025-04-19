package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class Queen extends BasePiece{
  public Queen(BaseCell pos) {
    this.position = pos;
  }

  @Override
  public boolean canMoveTo (BaseCell dst){
    return (
        !this.position.isEquals(dst)
    ) && (
        this.position.isSameDiagonal(dst) || 
        this.position.isSameCol(dst) ||
        this.position.isSameRow(dst)
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

    BasePiece piece = new WRook(this.position);
    result = piece.getValidMoves();
    
    piece = new WBishop(this.position);
    result.addAll(piece.getValidMoves());

    return result;
  }

  @Override
  public ArrayList<String> getTrace(BaseCell dst){
    ArrayList<String> result = new ArrayList<String>();
    String position = BaseCell.relativePos(this.position, dst);
    BaseCell cell;
    int row = this.position.getRow();
    int col = this.position.getCol();

    int i = 1;

    do{
      int newCol = row;
      int newRow = col;
      if(position.contains("TOP"))
        newRow += i;
      else 
        if(position.contains("BOT"))
          newRow -= i;
      if(position.contains("LEFT"))
        newCol -= i;
      else 
        if(position.contains("RIGHT"))
          newCol += i;

      cell = BaseCell.createCell(newRow, newCol);
      if(!cell.isEquals(dst))
        result.add(cell.cellName());
    } while (!cell.isEquals(dst) && i <= 8);

    return result;
  }
}

