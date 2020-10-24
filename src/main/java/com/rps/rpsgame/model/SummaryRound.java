package com.rps.rpsgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryRound {

  private OptionsModel player1;
  private OptionsModel player2;
  private String name;

}
