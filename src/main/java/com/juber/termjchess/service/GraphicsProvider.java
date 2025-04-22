package com.juber.termjchess.service;

import com.juber.termjchess.model.GameWarning;
import com.juber.termjchess.model.piece.BasePiece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface GraphicsProvider {
  public void startEngine(Map<String, BasePiece> pieces) throws Exception;

  public void drawnNewFrame();

  public void showWarning(GameWarning w);

  public void showCheckmate(String cellName);

  public void updateTurn(boolean isTurn0);

  public void hintCells(ArrayList<String> cells);

  public void stopEngine();
}
