package com.ouharri.aftas.model.mapper;

import com.ouharri.aftas.model.dto.requests._Request;
import com.ouharri.aftas.model.dto.responces._Response;
import com.ouharri.aftas.model.entities._Entity;

import java.sql.Timestamp;

/**
 * Generic mapper interface for converting between DTOs (Data Transfer Objects) and entities.
 *
 * @param <ID>     The type of the entity's identifier.
 * @param <Req>    The type of the request DTO.
 * @param <Res>    The type of the response DTO.
 * @param <Entity> The type of the entity.
 */
public interface _Mapper<ID, Req extends _Request, Res extends _Response, Entity extends _Entity<ID>> {

    /**
     * Converts a request DTO to an entity.
     *
     * @param request Request DTO.
     * @return Converted entity.
     */
    Entity toEntityFromRequest(Req request);

    /**
     * Converts a response DTO to an entity.
     *
     * @param response Response DTO.
     * @return Converted entity.
     */
    Entity toEntityFromResponse(Res response);

    /**
     * Converts an entity to a response DTO.
     *
     * @param entity Entity.
     * @return Converted response DTO.
     */
    Res toResponse(Entity entity);

    /**
     * Maps a string representing the creation timestamp to a {@link Timestamp} object.
     *
     * @param createdAt String representation of the creation timestamp.
     * @return {@link Timestamp} object representing the creation timestamp.
     */
    default Timestamp mapCreatedAt(String createdAt) {
        return Timestamp.valueOf(createdAt);
    }
}