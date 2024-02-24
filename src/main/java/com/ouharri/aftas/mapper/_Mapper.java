package com.ouharri.aftas.mapper;

import com.ouharri.aftas.model.dto.requests._Request;
import com.ouharri.aftas.model.dto.responses._Response;
import com.ouharri.aftas.model.entities._Entity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.sql.Timestamp;
import java.util.List;

/**
 * Generic mapper interface for converting between DTOs (Data Transfer Objects) and entities.
 *
 * @param <ID>     The type of the entity's identifier.
 * @param <Req>    The type of the request DTO.
 * @param <Res>    The type of the response DTO.
 * @param <Entity> The type of the entity.
 * @author <a href="mailto:ouharrioutman@gmail.com">Ouharri Outman</a>
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

    List<Res> toResponse(List<Entity> entity);

    /**
     * Applies partial updates from the request DTO to the entity, ignoring null values.
     *
     * @param request Request DTO containing partial updates.
     * @param entity  Entity to be updated.
     * @return Updated entity.
     */
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    Entity applyPartialUpdates(Req request, @MappingTarget Entity entity);

    /**
     * Applies partial updates from the response DTO to the entity, ignoring null values.
     *
     * @param response Response DTO containing partial updates.
     * @param entity   Entity to be updated.
     * @return Updated entity.
     */
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    Entity applyPartialUpdates(Res response, @MappingTarget Entity entity);

    /**
     * Maps a string representing the creation timestamp to a {@link Timestamp} object.
     *
     * @param createdAt String representation of the creation timestamp.
     * @return {@link Timestamp} object representing the creation timestamp.
     */
    default Timestamp mapCreatedAt(String createdAt) {
        if (createdAt == null) {
            return null;
        }
        return Timestamp.valueOf(createdAt);
    }

    /**
     * Maps a string representing the update timestamp to a {@link Timestamp} object.
     *
     * @param updatedAt String representation of the update timestamp.
     * @return {@link Timestamp} object representing the update timestamp.
     */
    default Timestamp mapUpdatedAt(String updatedAt) {
        if (updatedAt == null) {
            return null;
        }
        return Timestamp.valueOf(updatedAt);
    }
}