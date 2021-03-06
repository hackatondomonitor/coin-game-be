package com.hackatonalcoolico.coingame.core.engine.configuration;

import com.hackatonalcoolico.coingame.config.common.Constants;
import com.hackatonalcoolico.coingame.config.common.MapObject;
import org.apache.tomcat.util.bcel.Const;
import org.hibernate.mapping.Map;

public class GameConfiguration extends MapObject {

    public String getName() {
        return get(Constants.NAME);
    }

}
