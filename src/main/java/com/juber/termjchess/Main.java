package com.juber.termjchess;

import com.juber.termjchess.util.TermPrinter;
import com.juber.termjchess.service.TerminalGraphicsX;
import com.juber.termjchess.model.board.Board;

public class Main {
    public static void main(String[] args) {
      Board board; 
      try {
        board = new Board();
        TerminalGraphicsX graphics = new TerminalGraphicsX();
        graphics.startEngine(board.getPiecesConfig());
        graphics.drawnNewFrame();

      } catch (Exception e){
        System.out.println("Err: " + e.getMessage());
        return;
      }
  }
}
