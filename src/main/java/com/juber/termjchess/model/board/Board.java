package com.juber.termjchess.model.board;

import com.juber.termjchess.exception.InvalidBoardCellPosition;
import com.juber.termjchess.exception.IllegalChessMovementException;

import com.juber.termjchess.model.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

  private ArrayList<BaseCell> boardCells;
  private ArrayList<BasePiece> pieces;
  private Map<String, BasePiece> piecesOnBoard;
  private boolean turn_0;
  private boolean gameOver;

  public Board() throws Exception {
    try {

      this.createBoard();
      this.createPieces();
      this.setupBoard();

    } catch (InvalidBoardCellPosition e){
      throw new Exception("error creating board cells");
    }
  }

  public boolean gameOver(){
    return this.gameOver;
  }

  public void move(String src, String dst, boolean perform) throws IllegalArgumentException, IllegalChessMovementException{
    if(!(BaseCell.isValidPosition(src)) || !(BaseCell.isValidPosition(dst)))
      throw new IllegalArgumentException("invalid position");
    if(!this.piecesOnBoard.containsKey(src))
      throw new IllegalChessMovementException("no piece on src");

    BasePiece srcPiece = this.piecesOnBoard.get(src);
    BasePiece dstPiece = this.piecesOnBoard.get(dst);
    boolean toCapture = false;
    // check turn
    if(
        perform &&
        (srcPiece.isW() && !(this.turn_0)) ||
        (srcPiece.isB() && this.turn_0)
    ){
      throw new IllegalChessMovementException("cant perfor this move in this turn");
    }

    if(!(srcPiece.canMoveTo(BaseCell.createCell(dst)))){
      String errMsg = "Cant move " + srcPiece.toString() + " to " + dst;
      throw new IllegalChessMovementException(errMsg);
    }
    if(this.piecesOnBoard.containsKey(dst)){
      // dst contains piece. try capture 
      if(this.canCapture(srcPiece, dstPiece)){
        toCapture = true;
      } else 
        throw new IllegalChessMovementException("cant move to " + dst);
    }
    // check trace
    ArrayList<String> trace = srcPiece.getTrace(BaseCell.createCell(dst));
    for(String cell: trace){
      if(this.piecesOnBoard.containsKey(cell)){
        throw new IllegalChessMovementException("cant move. has other pieces between");
      }
    }

    if(!perform)
      return;
    // at this point the move can be performed
    try {
      if(toCapture)
        dstPiece.kill();
        
      srcPiece.moveTo(BaseCell.createCell(dst));
      this.piecesOnBoard.remove(src);
      this.piecesOnBoard.put(dst, srcPiece);
      this.updateTurn();
    } catch(IllegalChessMovementException e){
      throw e;
    }
  }

  private void updateTurn(){
    this.turn_0 = !(this.turn_0);
    this.verifyCheckMate();
  }

  private void verifyCheckMate(){
    King wking = findKing('w');
    King bking = findKing('b');
    if(wking == null || bking == null)
      return;

    for (Map.Entry<String, BasePiece> entry : this.piecesOnBoard.entrySet()) {
      if(entry.getValue().isW()){
        try {
          this.move(entry.getValue().getPositionName(), bking.getPositionName(), false);
          this.gameOver = true;
        } catch(Exception e) {}
      } else {
        try {
          this.move(entry.getValue().getPositionName(), wking.getPositionName(), false);
          this.gameOver = true;
        } catch(Exception e) {}
      }
    }
  }

  private King findKing(char type){
    for (BasePiece p: this.piecesOnBoard.values()) {
      if(p instanceof WKing && type == 'w')
        return (King)p;
      if(p instanceof BKing && type == 'b')
        return (King)p;
    }

    return null;
  }

  private boolean canCapture(BasePiece src, BasePiece dst){
    return (
        src.canMoveTo(BaseCell.createCell(dst.getPositionName())) && 
        !(src.isSameType(dst))
    );
  }

  private void createBoard() throws InvalidBoardCellPosition {
    this.boardCells = new ArrayList<BaseCell>();

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        try {

          this.boardCells.add(new BlackCell(i, j));

        } catch (InvalidBoardCellPosition e) {

          this.boardCells.add(new WhiteCell(i, j));

        }
      }
    }
  }

  private void createPieces() {
    this.pieces = new ArrayList<BasePiece>();
    this.createPawns();
    this.createKnights();
    this.createBishops();
    this.createRooks();
    
    this.createQueens();
    this.createKings();
  }

  private void setupBoard() {
    assert(this.boardCells.size() == 64);
    assert(this.pieces.size() == 32);
    this.piecesOnBoard = new HashMap<String, BasePiece>();

    for(BasePiece p: this.pieces)
      this.piecesOnBoard.put(p.getPositionName(), p);

    this.turn_0 = true;
    this.gameOver = false;
  }

  public String whatsOnCell(String cell) {
    if(!this.piecesOnBoard.containsKey(cell))
      return "";

    BasePiece piece = this.piecesOnBoard.get(cell);

    if(piece instanceof BPawn)
      return "b_pawn";
    if(piece instanceof WPawn)
      return "w_pawn";
    if(piece instanceof BKnight)
      return "b_knight";
    if(piece instanceof WKnight)
      return "w_knight";
    if(piece instanceof BBishop)
      return "b_bishop";
    if(piece instanceof WBishop)
      return "w_bishop";
    if(piece instanceof BRook)
      return "b_rook";
    if(piece instanceof WRook)
      return "w_rook";
    if(piece instanceof BQueen)
      return "b_queen";
    if(piece instanceof WQueen)
      return "w_queen";
    if(piece instanceof BKing)
      return "b_king";
    else
      return "w_king";
  }

  public Map<String, BasePiece> getPiecesConfig(){
    return this.piecesOnBoard;
  }

  private void createPawns(){
    for(int i=0; i < 8; i++){
      this.pieces.add( new WPawn(this.boardCells.get(8+i)) );
      this.pieces.add( new BPawn(this.boardCells.get(48+i)) );
    }
  }

  private void createKnights(){
    this.pieces.add( new WKnight(this.boardCells.get(1)) );
    this.pieces.add( new WKnight(this.boardCells.get(6)) );

    this.pieces.add( new BKnight(this.boardCells.get(57)) );
    this.pieces.add( new BKnight(this.boardCells.get(62)) );
  }

  private void createBishops(){
    this.pieces.add( new WBishop(this.boardCells.get(2)) );
    this.pieces.add( new WBishop(this.boardCells.get(5)) );

    this.pieces.add( new BBishop(this.boardCells.get(58)) );
    this.pieces.add( new BBishop(this.boardCells.get(61)) );
  }

  private void createRooks(){
    this.pieces.add( new WRook(this.boardCells.get(0)) );
    this.pieces.add( new WRook(this.boardCells.get(7)) );

    this.pieces.add( new BRook(this.boardCells.get(56)) );
    this.pieces.add( new BRook(this.boardCells.get(63)) );
  }

  private void createQueens(){
    this.pieces.add( new WQueen(this.boardCells.get(3)) );
    this.pieces.add( new BQueen(this.boardCells.get(59)) );
  }

  private void createKings(){
    this.pieces.add( new WKing(this.boardCells.get(4)) );
    this.pieces.add( new BKing(this.boardCells.get(60)) );
  }

  // as proximas funcoes podem ser apagadas,
  // apenas para usar nos testes
  public int getBoardSize() {
    return this.boardCells.size();
  }
  public int getTotalPiecesAmount(){
    return this.pieces.size();
  }
  public int getBlackPiecesAmount(){
    return this.countPieces('B');
  }
  public int getWhitePiecesAmount(){
    return this.countPieces('W');
  }

  private int countPieces(char c){
    int total = 0;
    for(BasePiece p: this.pieces){
      if(p.isB() && c == 'B'){
        total++;
        continue;
      }
      if(p.isW() && c == 'W'){
        total++;
      }
    }

    return total;
  }
}
