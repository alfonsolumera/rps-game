package com.rps.rpsgame.controller;

import com.rps.rpsgame.model.OptionsModel;
import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.model.SummaryRounds;
import com.rps.rpsgame.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

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

  @PostMapping
  public String save(@ModelAttribute SummaryRounds rounds, Model model) {
    Player p1 = Player.builder().name("PLAYER 1").choise(OptionsModel.randomOption()).build();
    Player p2 = Player.builder().name("PLAYER 2").choise(OptionsModel.PIEDRA)
        .historyMatches(new ArrayList<>()).build();

    SummaryRounds lstScored = gameService.playRound(rounds, p1, p2);

    model.addAttribute("rounds", lstScored);
    return "index";
  }
}
