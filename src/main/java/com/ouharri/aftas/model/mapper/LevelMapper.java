package com.ouharri.aftas.model.mapper;

import com.ouharri.aftas.model.dto.Level.LevelReq;
import com.ouharri.aftas.model.dto.Level.LevelResp;
import com.ouharri.aftas.model.entities.Level;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface LevelMapper {
    Level toEntity(LevelReq level);

    LevelResp toResp(Level level);
}