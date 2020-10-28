package com.rps.rpsgame.service;

import com.rps.rpsgame.model.SummaryRound;

import java.util.List;

public interface GameService {

  List<SummaryRound> playRound(List<SummaryRound> previousRounds);


}
