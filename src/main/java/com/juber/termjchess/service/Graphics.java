package com.juber.termjchess.service;

import com.juber.termjchess.model.piece.BasePiece;

import java.util.HashMap;
import java.util.Map;

public interface Graphics {
  public void setPiecesMap(Map<String, BasePiece> pieces);
  public void drawnNewFrame();
  public void showWarning(String msg, int seconds);
  public void showCheckmate(String cellName);
  public void updateTurn(boolean isTurn0);
}
