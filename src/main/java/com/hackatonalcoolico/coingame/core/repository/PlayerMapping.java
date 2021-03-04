package com.hackatonalcoolico.coingame.core.repository;

import com.hackatonalcoolico.coingame.config.utils.EntityMapper;
import com.hackatonalcoolico.coingame.core.models.dto.PlayerDTO;
import com.hackatonalcoolico.coingame.core.models.entities.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapping extends EntityMapper<PlayerDTO, Player> {
}
