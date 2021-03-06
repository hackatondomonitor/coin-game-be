package com.hackatonalcoolico.coingame.core.engine.execution;

import com.hackatonalcoolico.coingame.config.common.Constants;
import com.hackatonalcoolico.coingame.config.common.MapObject;
import com.hackatonalcoolico.coingame.core.engine.states.PlayStates;

import java.util.List;
import java.util.Map;

public class SimpleGameActivity extends MapObject implements GameActivity {


    public SimpleGameActivity(Map<String, Object> map) {
        new MapObject(map);
    }


    @Override
    public PlayStates getStates() {
        return PlayStates.valueOf(get(Constants.STATE));
    }

    @Override
    public String getMode() {
        return get(Constants.MODE);
    }

    @Override
    public Map<String, Object> getVotes() {
        return get(Constants.VOTES);
    }

    @Override
    public String getWinner() {
        return get(Constants.WINNER);
    }

    @Override
    public List<String> getPlayersList() {
        return getList(Constants.PLAYERS_LIST, String.class);
    }
}
