package com.rps.rpsgame.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.utils.Constants;

@Service
public class GameServiceImpl implements GameService {

  @Override
  public SummaryRounds playRound(SummaryRounds summary) {
    List<SummaryRound> round = new ArrayList();

    Player p1 = Player.builder().name("PLAYER 1").choice(OptionsModel.randomOption()).build();
    Player p2 = Player.builder().name("PLAYER 2").choice(OptionsModel.ROCK)
        .historyMatches(new ArrayList<>()).build();

    if (p1.getChoice().equals(p2.getChoice())) {
      round.add(new SummaryRound(p1.getChoice(), p2.getChoice(), Constants.TIE));
    } else if (p1.getChoice().equals(OptionsModel.PAPER)) {
      round.add(new SummaryRound(p1.getChoice(), p2.getChoice(), p1.getName()));
    } else {
      round.add(new SummaryRound(p1.getChoice(), p2.getChoice(), p2.getName()));
    }

    if (CollectionUtils.isEmpty(summary.getRounds())) {
      summary.setRounds(round);
    } else {
      summary.getRounds().addAll(round);
    }

    return summary;
  }

}
