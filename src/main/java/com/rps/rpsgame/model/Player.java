package com.rps.rpsgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Player {

  private String name;
  private OptionsModel choice;

}
