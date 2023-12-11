package com.ouharri.aftas.model.mapper;

import com.ouharri.aftas.model.dto.Level.LevelReq;
import com.ouharri.aftas.model.dto.Level.LevelResp;
import com.ouharri.aftas.model.entities.Level;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface LevelMapper {
    Level toEntity(LevelReq level);

    List<Level> toEntity(List<LevelReq> levels);

    LevelResp toResp(Level level);

}