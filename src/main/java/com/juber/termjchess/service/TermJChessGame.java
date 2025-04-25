package com.juber.termjchess.service;

import com.juber.termjchess.model.GameWarning;
import com.juber.termjchess.model.board.Board;
import com.juber.termjchess.service.GraphicsProvider;
import com.juber.termjchess.exception.IllegalChessMovementException;

import java.util.Scanner;
import java.util.ArrayList;

public class TermJChessGame{
  public enum GraphicsType {
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
      if(userIn.equals("exit"))
        break;
      if(userIn.equals("game over")){
        ((TerminalGraphicsX) this.graphics).gameOver();
        continue;
      }

      ArrayList<String> fromTo = new ArrayList<String>();
      try {

        fromTo = validateFromTo(userIn);
        this.board.move(fromTo.get(0), fromTo.get(1), true);

        if(this.board.gameOver())
          ((TerminalGraphicsX) this.graphics).gameOver();

      }  catch (IllegalChessMovementException e){
        // user type valid command (two cells)
        // but this movement is not allowed in chess
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

  private void createApropriateGraphicsProvider(GraphicsType type){
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

  private ArrayList<String> validateFromTo(String in) 
      throws IllegalArgumentException, IllegalChessMovementException{

    ArrayList<String> result = new ArrayList<String>();
    if(
        in.length() != 5 ||
        (in.charAt(0) < 'a' || in.charAt(0) > 'h')||
        (in.charAt(1) < '1' || in.charAt(1) > '8')||
        (in.charAt(2) != ' ') ||
        (in.charAt(3) < 'a' || in.charAt(3) > 'h')||
        (in.charAt(4) < '1' || in.charAt(4) > '8')
      ){
      throw new IllegalArgumentException("len: " + Integer.toString(in.length()));
    }

    result.add(in.substring(0, 2));
    result.add(in.substring(3));
    return result;
  }

}
