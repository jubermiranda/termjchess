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
    ArrayList<String> result;
    BasePiece piece;

    piece = new WRook(this.position);
    result = piece.getTrace(dst);
    if(result.size() != 0)
      return result;

    piece = new WBishop(this.position);
    result = piece.getTrace(dst);
    return result;
  }
}

