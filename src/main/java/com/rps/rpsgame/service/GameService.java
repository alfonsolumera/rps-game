package com.rps.rpsgame.service;

import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.model.SummaryRounds;

public interface GameService {

  SummaryRounds playRound(SummaryRounds summary, Player p1, Player p2);

}
