package com.juber.termjchess.service;

import com.juber.termjchess.model.GameWarning;
import com.juber.termjchess.service.GraphicsProvider;
import com.juber.termjchess.exception.IllegalChessMovementException;

import java.util.Scanner;

public class TermJChessGame{
  enum GraphicsType {
    TermX,
    TermLight,
    GraphicalInterface
  }

  private Board board;
  private GraphicsProvider graphics;

  public TermJChessGame(GraphicsType type){
    try {
      this.board = new Board();
      this.createApropriateGraphicsProvider(type);
      graphics.startEngine(board.getPiecesConfig());

    } catch (Exception e){
      System.out.println("Err: " + e.getMessage());
    }
    
  }

  public void runGame(){
    if(this.board == null || this.graphics == null)
      return;

    Scanner scanner = new Scanner(System.in);
    String userIn;

    while(!this.board.gameOver()){
      this.graphics.drawnNewFrame();
      System.out.print("[MOVE]> ");
      userIn = scanner.nextLine();
      if(userIn.equal("exit"))
        break;

      String [] fromTo;
      try {

        fromTo = validateFromTo(userIn);
        this.board.move(fromTo[0], fromTo[1]);

      }  catch (IllegalChessMovementException e){
        // user type valid command (two cells)
        // but this movement is not allowed in chess
        // lets hint cells
        this.graphics.hintCells(fromTo);

      }  catch (IllegalArgumentException e){
        // user type invalid comand
        GameWarning warning = new GameWarning("Invalid command. user: [src cell] [dst cell]", 3);
        this.graphics.showWarning(warning);
      }
    }
  }

  //
  //
  //
  // priave

  private createApropriateGraphicsProvider(GraphicsType type){
    switch(type){
      case TermX:
        this.graphics = new TerminalGraphicsX();
      break;
      case TermLight:
        //TODO
      break;
      case GraphicalInterface:
        //TODO
      break;
    }
  }

}
