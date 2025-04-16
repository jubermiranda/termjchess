package controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Printer {
  // Board dimensions
  private static final int BOARD_SIZE = 8;
  private static final int CELL_SIZE = 12;
  private static final int TOTAL_CELL_SIZE = CELL_SIZE + 2;
  private static final int TOTAL_BOARD_SIZE = BOARD_SIZE * TOTAL_CELL_SIZE + 1;

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

  public void printBoard() {
    StringBuilder board = new StringBuilder();

    // Load white pawn piece
    List<String> pawnWhite = null;
    try {
      pawnWhite = loadPiece("pawn_w");
    } catch (IOException e) {
      System.err.println("Error loading pawn_w: " + e.getMessage());
      return;
    }

    for (int row = 0; row < TOTAL_BOARD_SIZE; row++) {
      for (int col = 0; col < TOTAL_BOARD_SIZE; col++) {
        // Outer border
        if (row == 0) {
          if (col == 0) {
            board.append(TL_CORNER);
          } else if (col == TOTAL_BOARD_SIZE - 1) {
            board.append(TR_CORNER);
          } else if (col % TOTAL_CELL_SIZE == 0) {
            board.append(T_TEE);
          } else {
            board.append(H_LINE);
          }
        } else if (row == TOTAL_BOARD_SIZE - 1) {
          if (col == 0) {
            board.append(BL_CORNER);
          } else if (col == TOTAL_BOARD_SIZE - 1) {
            board.append(BR_CORNER);
          } else if (col % TOTAL_CELL_SIZE == 0) {
            board.append(B_TEE);
          } else {
            board.append(H_LINE);
          }
        } else if (row % TOTAL_CELL_SIZE == 0) {
          if (col == 0) {
            board.append(L_TEE);
          } else if (col == TOTAL_BOARD_SIZE - 1) {
            board.append(R_TEE);
          } else if (col % TOTAL_CELL_SIZE == 0) {
            board.append(CROSS);
          } else {
            board.append(H_LINE);
          }
        } else {
          // Vertical borders or cell content
          if (col == 0 || col == TOTAL_BOARD_SIZE - 1 || col % TOTAL_CELL_SIZE == 0) {
            board.append(V_LINE);
          } else {
            // Check if in second row (rank 2, a2-h2)
            if (row >= TOTAL_CELL_SIZE && row < 2 * TOTAL_CELL_SIZE) {
              // Calculate cell position
              int cellRow = row - TOTAL_CELL_SIZE;
              int cellCol = (col - 1) % TOTAL_CELL_SIZE;
              if (cellCol < CELL_SIZE && cellRow < pawnWhite.size() && cellCol < pawnWhite.get(cellRow).length()) {
                board.append(pawnWhite.get(cellRow).charAt(cellCol));
              } else {
                board.append(' ');
              }
            } else {
              board.append(' ');
            }
          }
        }
      }
      board.append('\n');
    }

    // Print the board
    System.out.print(board.toString());
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

}
