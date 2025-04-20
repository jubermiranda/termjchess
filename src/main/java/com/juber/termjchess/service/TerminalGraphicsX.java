package com.juber.termjchess.service;

import com.juber.termjchess.model.piece.BasePiece;
import com.juber.termjchess.model.StopWatch;
import com.juber.termjchess.model.GameWarning;
import com.juber.termjchess.util.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


public class TerminalGraphicsX implements  GraphicsProvider<char[][]> {
  private Map<String, BasePiece> piecesOnBoard;
  private State state;
  private boolean turn_0;
  private GameWarning lastWarning;
  private Set<String> hintCells;
  private StopWatch hintCellsSW;

  public TerminalGraphicsX(){
    this.state = State.CREATED;
    this.turn_0 = true;
    this.lastWarning = null;
    this.hintCells = new HashSet<String>();
  }

  public void startEngine(Map<String, BasePiece> pieces){
    if(pieces != null){
      this.piecesOnBoard = pieces;
      this.state = State.RUNNING;
    }
  }

  public char[][] drawnNewFrame(){
    return new char[0][0];
  }

  public void showWarning(GameWarning w){
    this.lastWarning = w;
  }

  public void showCheckmate(String cellName){
    return;
  }

  public void updateTurn(boolean isTurn0){
    this.turn_0 = isTurn0;
  }

  public void hintCells(ArrayList<String> cells, int duration){
    this.hintCells.clear();
    for(String s: cells){
      this.hintCells.add(s);
    }
  }

  public void hintCells(ArrayList<String> cells){
    this.hintCells(cells, GameWarning.STD_DURATION);
  }

  public void stopEngine(){
    if(this.piecesOnBoard != null)
      this.piecesOnBoard.clear();
    if(this.hintCells != null)
      this.hintCells.clear();
    this.state = State.STOPPED;
  }
}
