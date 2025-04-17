package com.juber.termjchess.model.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
    
  private ArrayList<BaseCell> boardCells;
  private ArrayList<BasePiece> pieces;
  private Map<String, BasePiece> piecesOnBoard;
  private Player p1;
  private Player p2;
  private boolean turn_0;


  public Board(){
    //TODO
  }

  public String whatsOnCell(String cell){
    return "nothing";
  }
}
