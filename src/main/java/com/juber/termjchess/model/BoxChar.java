package com.juber.termjchess.model;

public final class BoxChar {
  public static final char H_LINE = '─';
  public static final char V_LINE = '│';
  public static final char TL_CORNER = '┌';
  public static final char TR_CORNER = '┐';
  public static final char BL_CORNER = '└';
  public static final char BR_CORNER = '┘';
  public static final char L_TEE = '├';
  public static final char R_TEE = '┤';
  public static final char T_TEE = '┬';
  public static final char B_TEE = '┴';
  public static final char CROSS = '┼';

  public static final String RESET = "\033[0m";
  public static final String RED = "\033[31m";
  public static final String GREEN = "\033[32m";
  public static final String YELLOW = "\033[33m";
  public static final String BLUE = "\033[34m";
  public static final String PURPLE = "\033[35m";
  public static final String BLACK = "\033[30m";
  public static final String CYAN = "\033[36m";
  public static final String WHITE = "\033[37m";

  public static final String BG_RED = "\033[41m";
  public static final String BG_GREEN = "\033[42m";
  public static final String BG_BLACK = "\033[40m";
  public static final String BG_YELLOW = "\033[43m";
  public static final String BG_BLUE = "\033[44m";
  public static final String BG_PURPLE = "\033[45m";
  public static final String BG_CYAN = "\033[46m";
  public static final String BG_WHITE = "\033[47m";

  public static final String BOLD = "\033[1m";
  public static final String UNDERLINE = "\033[4m";
  public static final String INVERT = "\033[7m";



  public static final char BLOCK = '█';


  private BoxChar() {
  }

}
