package com.juber.termjchess.service;

import com.juber.termjchess.model.BoxChar;
import com.juber.termjchess.model.piece.*;
import com.juber.termjchess.model.board.*;
import com.juber.termjchess.model.StopWatch;
import com.juber.termjchess.model.GameWarning;
import com.juber.termjchess.util.State;
import com.juber.termjchess.util.TermPrinter;
import com.juber.termjchess.service.ChessSpriteXProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class TerminalGraphicsX implements GraphicsProvider {
  private int STD_SPRITE_SIZE = 11;

  private Map<String, BasePiece> piecesOnBoard;
  private State state;
  private boolean turn_0;
  private GameWarning lastWarning;
  private char[][] frame;
  private TermPrinter printer;

  public TerminalGraphicsX() {
    this.state = State.CREATED;
    this.turn_0 = true;
    this.lastWarning = null;
    this.printer = new TermPrinter(STD_SPRITE_SIZE, 1, 0, 0);

    int size = this.printer.getBoardSize();
    this.frame = new char[size][size];
    TermPrinter.clearFrame(this.frame, 0, 0, size, size);
    this.printer.printBoard(this.frame);
  }

  @Override
  public void startEngine(Map<String, BasePiece> pieces) throws Exception {
    if (pieces != null) {
      if (pieces.size() != 32)
        throw new Exception("invalid pieces num start");
      this.piecesOnBoard = pieces;
      this.state = State.RUNNING;
      this.playAnimationStart();
    }
  }

  @Override
  public void drawnNewFrame() {
    if (this.state != State.RUNNING)
      return;

    this.updateFrame();

    this.printer.printBuffer(this.frame);
  }

  public void showWarning(GameWarning w) {
    this.lastWarning = w;
  }

  public void showCheckmate(String cellName) {
    return;
  }

  public void updateTurn(boolean isTurn0) {
    this.turn_0 = isTurn0;
  }

  public void hintCells(ArrayList<String> cells, int duration) {
    this.printer.updateHints(cells);
  }

  public void hintCells(ArrayList<String> cells) {
    this.hintCells(cells, GameWarning.STD_DURATION);
  }

  public void stopEngine() {
    if (this.piecesOnBoard != null)
      this.piecesOnBoard.clear();
    this.state = State.STOPPED;
  }

  private void updateFrame() {
    for(int i=0; i < 8; i++){
      for(int j=0; j < 8; j++){

        String key = BaseCell.cellName(i, j);
        BasePiece value = this.piecesOnBoard.get(key);
        char [][] sprite = this.chooseSprite(value);

        int row = BaseCell.getRowFromName(key);
        int col = BaseCell.getColFromName(key);

        this.printer.drawPiece(
            sprite,
            row,
            col,
            this.frame
        );
      }
    }
  }

  private char[][] chooseSprite(BasePiece value){
    char[][] sprite;

    if (value == null)
      sprite = ChessSpriteXProvider.EmptySprite(STD_SPRITE_SIZE, STD_SPRITE_SIZE);
    else if (value instanceof BPawn)
      sprite = ChessSpriteXProvider.BPawnSprite;
    else if (value instanceof WPawn)
      sprite = ChessSpriteXProvider.WPawnSprite;
    else if (value instanceof BKnight)
      sprite = ChessSpriteXProvider.BKnightSprite;
    else if (value instanceof WKnight)
      sprite = ChessSpriteXProvider.WKnightSprite;
    else if (value instanceof BBishop)
      sprite = ChessSpriteXProvider.BBishopSprite;
    else if (value instanceof WBishop)
      sprite = ChessSpriteXProvider.WBishopSprite;
    else if (value instanceof BRook)
      sprite = ChessSpriteXProvider.BRookSprite;
    else if (value instanceof WRook)
      sprite = ChessSpriteXProvider.WRookSprite;
    else if (value instanceof BQueen)
      sprite = ChessSpriteXProvider.BQueenSprite;
    else if (value instanceof WQueen)
      sprite = ChessSpriteXProvider.WQueenSprite;
    else if (value instanceof BKing)
      sprite = ChessSpriteXProvider.BKingSprite;
    else
      sprite = ChessSpriteXProvider.WKingSprite;
    return sprite;
  }

  private void playAnimationStart() {
    TermPrinter.clearScreen();
    this.drawnHomeScreen();

    //TermPrinter.printProgressBar();
    TermPrinter.clearScreen();
  }

  void drawnHomeScreen(){
    char[][] homeScreen = new char[25][100];
    char[][] logo = ChessSpriteXProvider.LogoSprite;

    TermPrinter.clearFrame(homeScreen, 0, 0, 24, 99);
    TermPrinter.drawBox(homeScreen, 0, 0, 24, 99);
    TermPrinter.drawSprite(logo, 9, 9, homeScreen);

    this.printer.printBuffer(homeScreen);
  }

  

  
}
