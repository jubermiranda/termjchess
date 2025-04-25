package com.juber.termjchess.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ChessSpriteXProvider {
  private static final String wpawn_file = "pawn_w";
  private static final String bpawn_file = "pawn_b";
  private static final String wbishop_file = "bishop_w";
  private static final String bbishop_file = "bishop_b";
  private static final String wking_file = "king_w";
  private static final String bking_file = "king_b";
  private static final String wknight_file = "knight_w";
  private static final String bknight_file = "knight_b";
  private static final String wrook_file = "rook_w";
  private static final String brook_file = "rook_b";
  private static final String wqueen_file = "queen_w";
  private static final String bqueen_file = "queen_b";
  private static final String logo_file = "logo";
  private static final String game_over_file = "game_over";

  private static final int LABEL_ROW_SIZE = 5;
  private static final int LABEL_COL_SIZE = 8;

  public static final char[][] WPawnSprite = loadSprite(wpawn_file);
  public static final char[][] BPawnSprite = loadSprite(bpawn_file);

  public static final char[][] WBishopSprite = loadSprite(wbishop_file);
  public static final char[][] BBishopSprite = loadSprite(bbishop_file);

  public static final char[][] WKingSprite = loadSprite(wking_file);
  public static final char[][] BKingSprite = loadSprite(bking_file);

  public static final char[][] WKnightSprite = loadSprite(wknight_file);
  public static final char[][] BKnightSprite = loadSprite(bknight_file);

  public static final char[][] WRookSprite = loadSprite(wrook_file);
  public static final char[][] BRookSprite = loadSprite(brook_file);

  public static final char[][] WQueenSprite = loadSprite(wqueen_file);
  public static final char[][] BQueenSprite = loadSprite(bqueen_file);

  public static final char[][] LogoSprite = loadSprite(logo_file);

  public static final char[][] GameOver = loadSprite(game_over_file);

  public static char[][] EmptySprite(int rows, int cols){
    if(rows <=0 || rows <= 0)
      return new char[0][0];

    char[][] result = new char[rows][cols];
    for(int i=0; i < rows; i++)
      for(int j=0; j < rows; j++)
        result[i][j] = ' ';

    return result;
  }

  public static char[][] LabelSprite(char type, int n){
    if(n < 0 || n > 7 || (type != 'r' && type != 'c'))
      return new char[0][0];

    String spriteName = "" + type + n;
    return loadSprite(spriteName);
  }


  private ChessSpriteXProvider() {
  };

  private static char[][] loadSprite(String name){
    ArrayList<String> lines = readFile(name);
    if (lines.size() == 0)
      return new char[0][0];

    int rows = lines.size();
    int cols = lines.get(0).length();

    for (String line : lines) {
      if (line.length() != cols)
        return new char[0][0];
    }

    char[][] resutl = new char[rows][cols];
    try {
      copyContents(resutl, lines);
    } catch (IllegalArgumentException e) {
      return new char[0][0];
    }
    return resutl;
  }

  private static ArrayList<String> readFile(String name) {
    ArrayList<String> lines = new ArrayList<>();

    try (InputStream inputStream = ChessSpriteXProvider.class.getClassLoader().getResourceAsStream(name)) {
      if (inputStream == null) {
        return lines;
      }
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = reader.readLine()) != null) {
          lines.add(line);
        }
      }
    } catch(IOException e){
        return new ArrayList<String>();
    }

    return lines;
  }

  private static void copyContents(char[][] resutl, ArrayList<String> lines) {
    int rows = lines.size();
    int cols = lines.get(0).length();

    for (int row = 0; row < rows; row++) {
      String line = lines.get(row);
      for (int col = 0; col < cols; col++) {
        resutl[row][col] = line.charAt(col);
      }
    }
  }
}
