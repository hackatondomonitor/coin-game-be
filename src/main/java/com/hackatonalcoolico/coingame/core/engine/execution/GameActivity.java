package com.hackatonalcoolico.coingame.core.engine.execution;

import com.hackatonalcoolico.coingame.core.engine.states.PlayStates;

import java.util.List;
import java.util.Map;

public interface GameActivity {

    PlayStates getStates();
    String getMode();
    Map<String, Object> getVotes();
    String getWinner();
    List<String> getPlayersList();
}
