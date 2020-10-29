package com.rps.rpsgame.model;

import lombok.Data;

@Data
public class GameSummary {

  private long totalRounds;
  private long totalWinsOnePlayer;
  private long totalWinsSecondPlayer;
  private long totalDraws;

}
