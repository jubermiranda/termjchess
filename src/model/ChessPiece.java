package model;

public abstract class ChessPiece {
    private BoardPosition pos;
    protected char[][] sprite;
    protected boolean alive;

    public void move(BoardPosition dst) throws IllegalChessMovementException {
        if (canMoveTo(dst))
            this.pos = dst;
        else
            throw new IllegalChessMovementException(this.IllegalMoveMessage(dst));
    }

    public boolean isAlive() {
        return this.alive;
    }

    protected abstract boolean canMoveTo(BoardPosition dst);

    protected abstract String IllegalMoveMessage(BoardPosition bad_dst);

}
