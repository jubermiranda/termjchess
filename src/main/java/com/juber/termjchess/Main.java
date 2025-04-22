package com.juber.termjchess;

import com.juber.termjchess.service.TermJChessGame;

public class Main {
  public static void main(String[] args) {
    TermJChessGame.GraphicsType type = TermJChessGame.GraphicsType.TermX;
    TermJChessGame game = new TermJChessGame(type);
    game.runGame();
  }
}
