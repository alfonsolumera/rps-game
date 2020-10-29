package com.rps.rpsgame.service;

import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.utils.Constants;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

  private GameSummary gameSummary = new GameSummary();

  @Override
  public List<SummaryRound> playRound(List<SummaryRound> summary) {
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

    if (CollectionUtils.isEmpty(summary)) {
      return round;
    }

    summary.addAll(round);
    return summary;


  }

  @Override
  public void saveGameSummary(List<SummaryRound> previousRounds, String totalRounds) {

    Map<SummaryRound, Long> ocurrencias = previousRounds.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    this.gameSummary.setTotalWinsOnePlayer(
        ocurrencias.entrySet().stream().filter(s -> "PLAYER 1".equals(s.getKey().getName()))
            .findFirst().map(Map.Entry::getValue).orElse((long) 0));
    this.gameSummary.setTotalWinsSecondPlayer(
        ocurrencias.entrySet().stream().filter(s -> "PLAYER 2".equals(s.getKey().getName()))
            .findFirst().map(Map.Entry::getValue).orElse((long) 0));
    this.gameSummary.setTotalDraws(
        ocurrencias.entrySet().stream().filter(s -> "TIE".equals(s.getKey().getName())).findFirst()
            .map(Map.Entry::getValue).orElse((long) 0));
    this.gameSummary
        .setTotalRounds(this.gameSummary.getTotalRounds() + Integer.valueOf(totalRounds));

    System.out.println();
  }

  @Override
  public GameSummary getSummary() {
    return this.gameSummary;
  }


}
