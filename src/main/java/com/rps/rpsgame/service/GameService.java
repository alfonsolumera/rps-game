package com.rps.rpsgame.service;

import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.SummaryRound;

import java.util.List;

public interface GameService {

  List<SummaryRound> playRound(List<SummaryRound> previousRounds);

  void saveGameSummary(List<SummaryRound> previousRounds);

  GameSummary getSummary();



}
