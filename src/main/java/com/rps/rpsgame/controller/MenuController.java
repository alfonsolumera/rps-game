package com.rps.rpsgame.controller;

import com.rps.rpsgame.model.SummaryRound;
import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/", "/index"})
public class MenuController {

  @Autowired
  GameService gameService;

  @GetMapping(value = "/play")
  public String play(@ModelAttribute SummaryRounds rounds, Model model,
      HttpServletRequest request) {

    List<SummaryRound> previousRounds =
        (ArrayList) request.getSession().getAttribute("rounds") != null
            ? (ArrayList) request.getSession().getAttribute("rounds")
            : new ArrayList();
    String totalRounds = (String) request.getSession().getAttribute("roundsPlayed");

    List<SummaryRound> lstScored = gameService.playRound(previousRounds);

    int currentRound = totalRounds != null ? (Integer.valueOf(totalRounds)) + 1 : 1;

    request.getSession().setAttribute("roundsPlayed", String.valueOf(currentRound));
    request.getSession().setAttribute("rounds", lstScored);
    return "redirect:/";
  }

  @GetMapping(value = "/reset")
  public String reset(HttpServletRequest request) {

    request.getSession().setAttribute("rounds", new ArrayList());
    request.getSession().setAttribute("roundsPlayed", "0");
    return "redirect:/";
  }

}
