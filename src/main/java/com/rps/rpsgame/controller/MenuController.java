package com.rps.rpsgame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.service.GameService;

@Controller
@SuppressWarnings("unchecked")
public class MenuController {

  @Autowired
  GameService gameService;

  @GetMapping("/")
  public String init(HttpServletRequest request) {

    List<SummaryRound> previousRounds =
        request.getSession().getAttribute("rounds") != null
            ? (List<SummaryRound>) request.getSession().getAttribute("rounds")
            : new ArrayList<SummaryRound>();

    String totalRounds = request.getSession().getAttribute("roundsPlayed") != null
        ? (String) request.getSession().getAttribute("roundsPlayed")
        : "0";

    request.getSession().setAttribute("roundsPlayed", String.valueOf(totalRounds));

    request.getSession().setAttribute("rounds", previousRounds);

    return "index";
  }

  @GetMapping(value = "/play")
  public String play(HttpServletRequest request) {

    String totalRounds = (String) request.getSession().getAttribute("roundsPlayed");

    List<SummaryRound> lstScored =
        gameService.playRound((List<SummaryRound>) request.getSession().getAttribute("rounds"));

    int currentRound = totalRounds != null ? (Integer.valueOf(totalRounds)) + 1 : 1;

    request.getSession().setAttribute("roundsPlayed", String.valueOf(currentRound));
    request.getSession().setAttribute("rounds", lstScored);
    return "index";

  }

  @GetMapping(value = "/reset")
  public String reset(HttpServletRequest request) {

    request.getSession().setAttribute("rounds", new ArrayList<SummaryRound>());
    request.getSession().setAttribute("roundsPlayed", "0");

    return "index";

  }

  @GetMapping(value = "/game-summary")
  public String showSummary(HttpServletRequest request) {

    GameSummary gameSummary = gameService.getSummary();

    request.getSession().setAttribute("gameSummary", gameSummary);

    return "summary";

  }

}
