package controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Printer {
  // Board dimensions
  private static final int BOARD_CELLS = 8;
  private static final int CELL_SIZE = 13;
  private static final int TOTAL_BOARD_SIZE = (CELL_SIZE+1) * BOARD_CELLS + 1;

  // UTF-8 box drawing characters
  private static final char H_LINE = '\u2500';
  private static final char V_LINE = '\u2502';
  private static final char TL_CORNER = '\u250C';
  private static final char TR_CORNER = '\u2510';
  private static final char BL_CORNER = '\u2514';
  private static final char BR_CORNER = '\u2518';
  private static final char L_TEE = '\u251C';
  private static final char R_TEE = '\u2524';
  private static final char T_TEE = '\u252C';
  private static final char B_TEE = '\u2534';
  private static final char CROSS = '\u253C';

  private char[][] board;

  public Printer(){
    this.board = new char[TOTAL_BOARD_SIZE][TOTAL_BOARD_SIZE];
    for(int i=0; i < TOTAL_BOARD_SIZE; i++){
      for(int j=0; j < TOTAL_BOARD_SIZE; j++){
        if(this.isBorderPixel(i, j))
          this.board[i][j] = '*';
        else 
          this.board[i][j] = ' ';
      }
    }
  }

  public void printBoard() {
    for(int i=0; i < TOTAL_BOARD_SIZE; i++){
      for(int j=0; j < TOTAL_BOARD_SIZE; j++){
        System.out.print(this.board[i][j]);
      }
      System.out.println();
    }

  }

  private List<String> loadPiece(String fileName) throws IOException {
    // Try loading from classpath
    String path = "controller/assets/" + fileName;
    var resource = getClass().getClassLoader().getResource(path);
    if (resource == null) {
      System.err.println("Debug: Resource not found at classpath: " + path);
      // Fallback to relative path from project root
      path = "src/controller/assets/" + fileName;
      return Files.readAllLines(Paths.get(path));
    }
    try {
      return Files.readAllLines(Paths.get(resource.toURI()));
    } catch (URISyntaxException e) {
      throw new IOException("Invalid URI for resource: " + path, e);
    }
  }

  private boolean isBorderPixel(int i, int j){
    if(i == 0 || i == TOTAL_BOARD_SIZE-1)
      return true;
    if(j == 0 || j == TOTAL_BOARD_SIZE-1)
      return true;
    if(i % (CELL_SIZE+1) == 0 || j % (CELL_SIZE + 1) == 0)
      return true;

    return false;
  }

}
