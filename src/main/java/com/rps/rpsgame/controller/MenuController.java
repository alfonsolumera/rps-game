package com.rps.rpsgame.controller;

import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/index"})
public class MenuController {

  @Autowired
  GameService gameService;

  @GetMapping
  public String main(Model model) {
    SummaryRounds rounds = null;
    model.addAttribute("rounds", rounds);
    return "index";
  }

  @PostMapping(value = "/play")
  public String play(@ModelAttribute SummaryRounds rounds, Model model) {

    SummaryRounds lstScored = gameService.playRound(rounds);

    model.addAttribute("rounds", lstScored);
    return "redirect:/index";
  }

  @GetMapping(value = "/reset")
  public String reset(@ModelAttribute SummaryRounds rounds, Model model) {

    model.addAttribute("rounds", new SummaryRounds());
    return "redirect:/index";
  }

}
