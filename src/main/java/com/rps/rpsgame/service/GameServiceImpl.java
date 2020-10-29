package com.rps.rpsgame.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.utils.Constants;

@Service
public class GameServiceImpl implements GameService {

  private GameSummary gameSummary = GameSummary.builder().totalRounds(0).totalDraws(0).totalWinsPlayerOne(0).totalWinsPlayerTwo(0).build();

  @Override
  public List<SummaryRound> playRound(List<SummaryRound> summary) {

    List<SummaryRound> round = new ArrayList<>();

    Player p1 = Player.builder().name(Constants.PLAYER_ONE).choice(OptionsModel.randomOption()).build();
    Player p2 = Player.builder().name(Constants.PLAYER_TWO).choice(OptionsModel.ROCK).build();

    if (p1.getChoice().equals(p2.getChoice())) {
      round.add(new SummaryRound(p1.getChoice(), p2.getChoice(), Constants.TIE));
      gameSummary.setTotalDraws(gameSummary.getTotalDraws() + 1);
    } else if (p1.getChoice().equals(OptionsModel.PAPER)) {
      round.add(new SummaryRound(p1.getChoice(), p2.getChoice(), p1.getName()));
      gameSummary.setTotalWinsPlayerOne(gameSummary.getTotalWinsPlayerOne() + 1);
    } else {
      round.add(new SummaryRound(p1.getChoice(), p2.getChoice(), p2.getName()));
      gameSummary.setTotalWinsPlayerTwo(gameSummary.getTotalWinsPlayerTwo() + 1);
    }

    gameSummary.setTotalRounds(gameSummary.getTotalRounds() + 1);

    if (CollectionUtils.isEmpty(summary)) {
      return round;
    }

    summary.addAll(round);

    return summary;

  }

  @Override
  public GameSummary getSummary() {
    return this.gameSummary;
  }


}
