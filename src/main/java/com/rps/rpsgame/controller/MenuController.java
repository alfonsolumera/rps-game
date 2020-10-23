package com.rps.rpsgame.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rps.rpsgame.model.Player;
import com.rps.rpsgame.service.GameService;

@Controller
public class MenuController {

    @Autowired
    GameService gameService;

    @RequestMapping(value="/rps-game/play-round")
    public String initView(HttpSession session, Model model) {
        //TODO take from session
        Player p1 = new Player();
        Player p2 = new Player();
        gameService.playRound(p1,p2);
        model.addAttribute("player1",p1);
        model.addAttribute("player2",p2);
        return "rps-game";
    }

}
