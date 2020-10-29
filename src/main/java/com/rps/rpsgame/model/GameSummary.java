package com.rps.rpsgame.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSummary {

  private int totalRounds;
  private int totalWinsPlayerOne;
  private int totalWinsPlayerTwo;
  private int totalDraws;

}
