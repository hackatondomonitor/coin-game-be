package com.hackatonalcoolico.coingame.core.engine;

import com.hackatonalcoolico.coingame.core.engine.execution.GameActivity;
import org.springframework.stereotype.Service;

@Service
public interface GameActivityService {

    GameActivity findByGameId(String gameId);

    GameActivity create(GameActivity gameActivity);

    GameActivity merge(GameActivity gameActivity);
}
