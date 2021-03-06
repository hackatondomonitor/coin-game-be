package com.hackatonalcoolico.coingame.core.engine.lobby;

import com.hackatonalcoolico.coingame.core.engine.GameActivityService;
import com.hackatonalcoolico.coingame.core.engine.configuration.GameConfiguration;
import com.hackatonalcoolico.coingame.core.engine.execution.GameActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyService {

    @Autowired(required = false)
    private GameActivityService gameActivityService;

    public void createLobby(GameConfiguration gameConfiguration, String playerName) {
    }
}
