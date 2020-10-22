package com.rps.rpsgame.service;

import java.util.List;

import com.rps.rpsgame.model.Player;

public interface GameService {

    List<Player> playRound (Player p1, Player p2);

}
