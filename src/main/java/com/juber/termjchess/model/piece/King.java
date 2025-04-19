package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class King extends BasePiece{
  private boolean has_moved;

  public King(BaseCell pos) {
    this.position = pos;
    this.has_moved = false;
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
    if(this.canMoveTo(dst)){
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

  @Override
  public ArrayList<String> getTrace(BaseCell dst){
    return new ArrayList<>();
  }

  public void castling(Rook rook) throws IllegalChessMovementException{
    assert(rook.isB() == this.isB());

    if(this.has_moved || rook.has_moved())
      throw new IllegalChessMovementException("cant castle if kink or rook has moved");

    int rookCol = BaseCell.getColFromName(rook.getPositionName());
    int distance = this.position.getCol() - rookCol;
    if(distance != 4 && distance != -3)
      throw new IllegalChessMovementException("invalid initial position");

    BaseCell newKingPos;
    BaseCell newRookPos;
      
    if(distance == 4){
      newKingPos = BaseCell.createCell(this.position.getRow(), this.position.getCol() - 2);
      newRookPos = BaseCell.createCell(newKingPos.getRow(), newKingPos.getCol() + 1);
      try {
        rook.moveTo(newRookPos);
        this.position = newKingPos;
        return;

      } catch (IllegalChessMovementException e){
        throw new IllegalChessMovementException("error while mving rook");
      }
    }

    newKingPos = BaseCell.createCell(this.position.getRow(), this.position.getCol() + 2);
    newRookPos = BaseCell.createCell(newKingPos.getRow(), newKingPos.getCol() - 1);
    try {
      rook.moveTo(newRookPos);
      this.position = newKingPos;

    } catch (IllegalChessMovementException e){
      throw new IllegalChessMovementException("error while mving rook");
    }
  }

  public boolean has_moved(){
    return this.has_moved;
  }
}
