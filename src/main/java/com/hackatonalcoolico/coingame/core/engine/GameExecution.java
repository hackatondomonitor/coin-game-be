package com.hackatonalcoolico.coingame.core.engine;

import com.hackatonalcoolico.coingame.core.engine.configuration.GameConfiguration;
import com.hackatonalcoolico.coingame.core.engine.lobby.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class GameExecution {

    @Autowired(required = false)
    private GameConfigurationService gameConfigurationService;

    @Autowired(required = false)
    private LobbyService lobbyService;

    public void startLobby(String gameConfigurationName, String playerName) {
        GameConfiguration gameConfiguration = gameConfigurationService.findByName(gameConfigurationName);
        Assert.notNull(gameConfiguration, "The game configuration ".concat(gameConfigurationName).concat(" has not found"));

    }

    public  void  addPlayer(String playerName) {

    }

    public void startGame(String gameId) {

    }

    public void addVote(String gameId, String  vote) {

    }

}
