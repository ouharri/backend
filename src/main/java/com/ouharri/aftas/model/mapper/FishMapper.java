package com.ouharri.aftas.model.mapper;

import com.ouharri.aftas.model.dto.Fish.FishReq;
import com.ouharri.aftas.model.dto.Fish.FishResp;
import com.ouharri.aftas.model.entities.Fish;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface FishMapper {

    Fish toEntity(FishReq fish);

    FishResp toResp(Fish fish);
}