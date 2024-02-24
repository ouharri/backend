package com.ouharri.aftas.model.dto.App;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * A custom pageable class for RESTful responses, extending Spring Data's PageImpl.
 * This class is designed to be compatible with JSON serialization and deserialization,
 * especially useful when dealing with systems like Redis that require specific serialization formats.
 *
 * @param <T> The type of objects in the page.
 * @author <a href="mailto:ouharri.outman@gmail.com">Ouharri Outman</a>
 */
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"pageable"}
)
public class RestPage<T> extends PageImpl<T> implements Serializable {

    /**
     * Constructs a new RestPage object.
     *
     * @param content A list of objects of type T for this page.
     * @param page    The current page number.
     * @param size    The size of the page.
     * @param total   The total number of elements available.
     */
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPage(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int page,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") long total
    ) {
        super(content, PageRequest.of(page, size), total);
    }

    /**
     * Constructs a new RestPage object based on a Page.
     *
     * @param page The Page object to base this RestPage on.
     */
    public RestPage(Page<T> page) {
        super(page.getContent(), page.getPageable(), page.getTotalElements());
    }
}