package com.juber.termjchess;

import com.juber.termjchess.service.TermPrinter;

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
    TermPrinter printer = new TermPrinter(4, 0, 3, 3);
    int n = printer.getBoardSize() + 4;
    char[][] board = new char[n][n];
    for(int i=0; i < n; i++)
      for(int j=0; j < n; j++)
        board[i][j] = ' ';

    printer.printBoard(board);
    System.out.println("Board::::::");
    System.out.println("size = " + Integer.toString(n) );
    printtt(board);
    
    char[][] sprite = {
      {' ', 'X', ' '},
      {' ', ' ', 'X'},
      {'X', 'X', 'X'}
    };

    System.out.println("Board:::::: (with sprite):::::");
    printer.drawSprite(sprite, 0, 0, board);
    printtt(board);
  }
}
