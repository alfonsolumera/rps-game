package com.rps.rpsgame.service;

import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.utils.Constants;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

  @Override
  public List<Player> playRound(Player p1, Player p2) {

    List<Player> game = new ArrayList<>();
    String scoreP1;
    String scoreP2;
    boolean tie = p1.getOption() == p2.getOption();

    if (!tie) {
      scoreP1 = p1.getOption() == Constants.PAPER ? Constants.WINNER : Constants.LOOSER;
      scoreP2 = Constants.WINNER.equals(scoreP1) ? Constants.LOOSER : Constants.WINNER;
    } else {
      scoreP1 = Constants.TIE;
      scoreP2 = Constants.TIE;
    }

    p1.getHistoryMatches().add(scoreP1);
    p2.getHistoryMatches().add(scoreP2);

    game.add(p1);
    game.add(p2);

    return game;
  }
}
