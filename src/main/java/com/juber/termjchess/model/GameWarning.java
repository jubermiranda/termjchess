package com.juber.termjchess.model;

import java.time.LocalDateTime;

public class GameWarning {
  public static final int STD_DURATION = 3;

  public String msg;
  private StopWatch stopWatch;

  public GameWarning(String msg, int duration){
    this.msg = msg;
    this.stopWatch = new StopWatch(duration);
  }
  public GameWarning(String msg){
    this.msg = msg;
    this.stopWatch = new StopWatch(STD_DURATION);
  }

  public boolean isValid(){
    return this.stopWatch.isValid();
  }
    
}
