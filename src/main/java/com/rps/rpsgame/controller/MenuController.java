package com.rps.rpsgame.controller;

import java.util.ArrayList;
import java.util.Random;

import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.service.GameService;
import com.rps.rpsgame.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/rps-game", produces = "application/json")
public class MenuController {

  @Autowired
  GameService gameService;

  @PostMapping(value = "/rps-game/play-round")
  public String initView(HttpSession session, Model model) {

    // TODO take from session
    Player p1 = Player.builder().name("unknow").option(new Random().nextInt(3)+1).build();
    Player p2 = Player.builder().name("computer").option(Constants.ROCK).historyMatches(new ArrayList<>()).build();
    gameService.playRound(p1, p2);
    model.addAttribute("player1", p1);
    model.addAttribute("player2", p2);
    return "rps-game";
  }

}
