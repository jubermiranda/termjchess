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
  private boolean gameOver;

  public TerminalGraphicsX() {
    this.state = State.CREATED;
    this.turn_0 = true;
    this.gameOver = false;
    this.lastWarning = null;
    this.printer = new TermPrinter(STD_SPRITE_SIZE, 1);

    int[] size = this.printer.getFrameSize();
    this.frame = new char[size[0]][size[1]];
    TermPrinter.clearFrame(this.frame, 0, 0, size[0], size[1]);
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
    TermPrinter.clearScreen();
    TermPrinter.printBuffer(this.frame);
  }

  @Override
  public void stopEngine() {
    if (this.piecesOnBoard != null)
      this.piecesOnBoard.clear();
    this.state = State.STOPPED;
  }

  @Override
  public void showWarning(GameWarning w) {
    this.lastWarning = w;
  }

  @Override
  public void showCheckmate(String cellName) {
    return;
  }

  @Override
  public void updateTurn(boolean isTurn0) {
    this.turn_0 = isTurn0;
  }

  @Override
  public void hintCells(ArrayList<String> cells) {
    this.hintCells(cells, GameWarning.STD_DURATION);
  }

  public void hintCells(ArrayList<String> cells, int duration) {
    this.printer.updateHints(cells);
  }

  public void gameOver(){
    this.gameOver = true;
  }

  //
  //
  //
  // --

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

    if(this.gameOver)
      this.drawnGameOverFrame();
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

  private void drawnGameOverFrame(){
    char[][] gameOverSprite = ChessSpriteXProvider.GameOver;
    int boxStartRow = 30;
    int boxStartCol = 30;
    int spacing = 8;
    int boxEndRow = boxStartRow + gameOverSprite.length + (2*spacing);
    int boxEndCol = boxStartCol + gameOverSprite[0].length + (2*spacing);

    //clear frame around game-over sprite
    TermPrinter.clearFrame(
        this.frame,
        boxStartRow - spacing,
        boxStartCol - spacing,
        boxEndRow + spacing,
        boxEndCol + spacing
    );

    // drawn a box to put game-over sprite inside, with spacing around
    TermPrinter.drawBox(
        this.frame,
        boxStartRow,
        boxStartCol,
        boxEndRow,
        boxEndCol
    );

    // drawn game-over sprite inside box
    TermPrinter.drawSprite(
        gameOverSprite,
        boxStartRow + spacing,
        boxStartCol + spacing,
        this.frame
    );
  }

  void drawnHomeScreen(){
    char[][] homeScreen = new char[25][180];
    char[][] logo = ChessSpriteXProvider.LogoSprite;

    TermPrinter.clearFrame(homeScreen, 0, 0, 24, 179);
    TermPrinter.drawBox(homeScreen, 0, 0, 24, 179);
    TermPrinter.drawSprite(logo, 12, 60, homeScreen);

    TermPrinter.printBuffer(homeScreen);
  }
}
