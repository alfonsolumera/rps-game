package com.rps.rpsgame.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.service.GameService;

@Controller
@RequestMapping({"/", "/index"})
public class MenuController {

  @Autowired
  GameService gameService;

  /*@GetMapping
  public String main(Model model) {
    SummaryRounds rounds = null;
    model.addAttribute("rounds", rounds);
    return "index";
  }*/

  @GetMapping(value = "/play")
  public String play(@ModelAttribute SummaryRounds rounds, Model model, HttpServletRequest request) {

    SummaryRounds previousRounds = (SummaryRounds) request.getSession().getAttribute("scoreBoard") != null ?
            (SummaryRounds) request.getSession().getAttribute("scoreBoard") : new SummaryRounds();
    String totalRounds = (String) request.getSession().getAttribute("roundsPlayed");

    SummaryRounds lstScored = gameService.playRound(previousRounds);

    int currentRound = totalRounds != null ? (Integer.valueOf(totalRounds)) +1 : 1;

    request.getSession().setAttribute("roundsPlayed", String.valueOf(currentRound));
    request.getSession().setAttribute("scoreBoard", lstScored);
    model.addAttribute("rounds", lstScored);
    return "redirect:/";
  }

  @GetMapping(value = "/reset")
  public String reset(@ModelAttribute SummaryRounds rounds, Model model, HttpServletRequest request) {

    request.getSession().setAttribute("roundsPlayed", "0");
    return "redirect:/";
  }

}
