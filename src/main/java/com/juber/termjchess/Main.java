package com.juber.termjchess;

import com.juber.termjchess.service.TermPrinter;
public class Main {
  public static void main(String[] args) {
    TermPrinter printer = new TermPrinter(4, 0);
    int n = printer.getBoardSize() + 4;
    char[][] board = new char[n][n];
    for(int i=0; i < n; i++)
      for(int j=0; j < n; j++)
        board[i][j] = ' ';

    printer.printBoard(board, 3, 3);
    System.out.println("Board::::::");
    System.out.println("size = " + Integer.toString(n) );
    for(int i = 0; i < board.length; i++){
      for(int j=0; j < board[0].length; j++){
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
}
