package com.rps.rpsgame.model;

import java.util.Random;

public enum OptionsModel {


  ROCK, PAPER, SCISSOR;

  public static OptionsModel randomOption() {
    int pick = new Random().nextInt(OptionsModel.values().length);
    return OptionsModel.values()[pick];
  }
}
