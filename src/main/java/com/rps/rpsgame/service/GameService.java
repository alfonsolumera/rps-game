package com.rps.rpsgame.service;

import java.util.List;

import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.SummaryRound;

public interface GameService {

  List<SummaryRound> playRound(List<SummaryRound> previousRounds);

  GameSummary getSummary();



}
