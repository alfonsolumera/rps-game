package com.rps.rpsgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

  private String name;
  private OptionsModel choice;
  private int option;
  private List<String> historyMatches;

}
