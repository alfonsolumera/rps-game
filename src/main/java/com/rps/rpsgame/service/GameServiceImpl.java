package com.rps.rpsgame.service;

import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.utils.Constants;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

  @Override
  public SummaryRounds playRound(SummaryRounds summary) {
    List<SummaryRound> round = new ArrayList();

    Player p1 = Player.builder().name("PLAYER 1").choise(OptionsModel.randomOption()).build();
    Player p2 = Player.builder().name("PLAYER 2").choise(OptionsModel.PIEDRA)
        .historyMatches(new ArrayList<>()).build();

    if (p1.getChoise().equals(p2.getChoise())) {
      round.add(new SummaryRound(p1.getChoise(), p2.getChoise(), Constants.EMPTY));
    } else if (p1.getChoise().equals(OptionsModel.PAPEL)) {
      round.add(new SummaryRound(p1.getChoise(), p2.getChoise(), p1.getName()));
    } else {
      round.add(new SummaryRound(p1.getChoise(), p2.getChoise(), p2.getName()));
    }

    if (CollectionUtils.isEmpty(summary.getRounds())) {
      summary.setRounds(round);
    } else {
      summary.getRounds().addAll(round);
    }

    return summary;
  }

}
