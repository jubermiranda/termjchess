package com.juber.termjchess.model;

import com.juber.termjchess.exception.InvalidBoardCellPosition;

public class BlackCell extends BaseCell {

  public BlackCell(int row, int col) throws InvalidBoardCellPosition {
    this.row = row;
    this.col = col;

    if (row < 8 && col < 8) {
      if (row % 2 == 0) {
        if (col % 2 == 0)
          return;
      } else {
        if (col % 2 != 0)
          return;
      }
    }
    throw new InvalidBoardCellPosition();
  }

  @Override
  public boolean isW() {
    return false;
  }

  @Override
  public boolean isB() {
    return !this.isW();
  }

}
