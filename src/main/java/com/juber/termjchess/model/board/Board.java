package com.juber.termjchess.model.board;

import com.juber.termjchess.exception.InvalidBoardCellPosition;

import com.juber.termjchess.model.Player;
import com.juber.termjchess.model.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

  private ArrayList<BaseCell> boardCells;
  private ArrayList<BasePiece> pieces;
  private Map<String, BasePiece> piecesOnBoard;
  private boolean turn_0;

  public Board() throws Exception {
    try {
      this.createBoard();
      this.createPieces();
      this.setupBoard();
    } catch (InvalidBoardCellPosition e){
      throw new Exception("error creating board cells");
    }
  }

  public String whatsOnCell(String cell) {
    return "nothing";
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
    // TODO
  }

  private void createPawns(){
    for(int i=0; i < 8; i++){
      this.pieces.add(BPawn(i));
      this.pieces.add(WPawn(i));
    }
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
