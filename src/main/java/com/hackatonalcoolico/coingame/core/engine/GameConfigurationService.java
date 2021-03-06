package com.hackatonalcoolico.coingame.core.engine;

import com.hackatonalcoolico.coingame.core.engine.configuration.GameConfiguration;

public interface GameConfigurationService {

    GameConfiguration findByName(String name);
}
