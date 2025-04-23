package com.juber.termjchess.model.piece;

import com.juber.termjchess.model.board.BaseCell;

import java.util.ArrayList;

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
        (this.has_moved && (dst.getRow() - this.position.getRow() == 1)) ||
        (!this.has_moved && (dst.getRow() - this.position.getRow() <= 2))
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

  @Override
  public String getName(){
    return "White Pawn";
  }

  @Override 
  public ArrayList<String> getTrace(BaseCell dst){
    ArrayList<String> result = new ArrayList<String>();
    if(!this.canMoveTo(dst))
      return result;

    if(!this.has_moved){
      if(BaseCell.rowDistance(this.position, dst) == 2){
        BaseCell cell = BaseCell.createCell(
            this.position.getRow()+1, 
            this.position.getCol()
        ); 
        result.add(cell.cellName());
      }
    }

    return result;
  }    
}

