package com.juber.termjchess.util;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.model.board.BaseCell;
import com.juber.termjchess.model.board.BlackCell;
import com.juber.termjchess.model.board.WhiteCell;

public class TestUtils {
    public static BaseCell createCell(String name) {
        int row = BaseCell.getRowFromName(name);
        int col = BaseCell.getColFromName(name);
        try {
            BlackCell c = new BlackCell(row, col);
            return c;

        } catch (InvalidBoardCellPosition e) {
            try {
                WhiteCell c = new WhiteCell(row, col);
                return c;

            } catch (InvalidBoardCellPosition e2) {
                return null;
            }
        }

    }
}
