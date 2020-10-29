package com.rps.rpsgame.controller;

import com.rps.rpsgame.model.GameSummary;
import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/", "/index"})
@SuppressWarnings("unchecked")
public class MenuController {

  @Autowired
  GameService gameService;

  @GetMapping(value = "/play")
  public String play(HttpServletRequest request) {

    List<SummaryRound> previousRounds =
        (ArrayList<SummaryRound>) request.getSession().getAttribute("rounds") != null
            ? (ArrayList<SummaryRound>) request.getSession().getAttribute("rounds")
            : new ArrayList<SummaryRound>();
    String totalRounds = (String) request.getSession().getAttribute("roundsPlayed");

    List<SummaryRound> lstScored = gameService.playRound(previousRounds);

    int currentRound = totalRounds != null ? (Integer.valueOf(totalRounds)) + 1 : 1;

    request.getSession().setAttribute("roundsPlayed", String.valueOf(currentRound));
    request.getSession().setAttribute("rounds", lstScored);
    return "redirect:/";

  }

  @GetMapping(value = "/reset")
  public String reset(HttpServletRequest request) {

    gameService.saveGameSummary((List<SummaryRound>) request.getSession().getAttribute("rounds"),
        (String) request.getSession().getAttribute("roundsPlayed"));

    request.getSession().setAttribute("rounds", new ArrayList<SummaryRound>());
    request.getSession().setAttribute("roundsPlayed", "0");

    return "redirect:/";

  }

  @GetMapping(value = "/game-summary")
  public String showSummary(HttpServletRequest request) {

    GameSummary gameSummary = gameService.getSummary();

    request.getSession().setAttribute("gameSummary", gameSummary);

    return "summary";

  }

}
