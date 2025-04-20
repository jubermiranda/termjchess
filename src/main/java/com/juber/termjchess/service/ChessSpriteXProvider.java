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

  private ChessSpriteXProvider() {
  };

  public static final char[][] WPawnSprite() {
    ArrayList<String> lines = readFile(wpawn_file);
    if (lines.size() == 0) {

      char[][] result = new char[2][2];
      result[0][0] = 'X';
      result[0][1] = 'X';
      result[1][0] = 'X';
      result[1][1] = 'X';
      return result;
    }

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
