package com.juber.termjchess;

import com.juber.termjchess.util.TermPrinter;
import com.juber.termjchess.service.ChessSpriteXProvider;

public class Main {
  public static void printtt(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    TermPrinter printer = new TermPrinter(12, 0, 0, 0);
    int n = printer.getBoardSize();
    char[][] board = new char[n][n];
    char[][] sprite = ChessSpriteXProvider.WPawnSprite();

    for(int i=0; i < n; i++)
      for(int j=0; j < n; j++)
        board[i][j] = ' ';

    printer.printBoard(board);
    printer.drawSprite(sprite, 0, 0, board);
    
    System.out.println("size = " + Integer.toString(n) );
    System.out.println("Board:::::: (with sprite):::::");
    printtt(board);
  }
}
