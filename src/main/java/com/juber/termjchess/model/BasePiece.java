package com.juber.termjchess.model;

import com.juber.termjchess.exception.IllegalChessMovementException;

public abstract class BasePiece {
    private BoardCell pos;
    protected char[][] sprite;
    protected boolean alive;

    public void move(BoardCell dst) throws IllegalChessMovementException {
        if (canMoveTo(dst))
            this.pos = dst;
        else
            throw new IllegalChessMovementException(this.IllegalMoveMessage(dst));
    }

    public boolean isAlive() {
        return this.alive;
    }

    protected abstract boolean canMoveTo(BoardCell dst);

    protected abstract String IllegalMoveMessage(BoardCell bad_dst);

}
