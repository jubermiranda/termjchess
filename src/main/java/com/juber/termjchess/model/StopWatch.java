package com.juber.termjchess.model;

import java.time.LocalDateTime;

public class StopWatch {
  private LocalDateTime createdAt;
  private int duration;

  public StopWatch(int duration){
    this.createdAt = LocalDateTime.now();
    this.duration = duration;
  }

  public boolean isValid(){
    LocalDateTime endTime = this.createdAt.plusSeconds(this.duration);
    return endTime.isAfter(LocalDateTime.now());
  }
}
