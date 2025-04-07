package com.juandavyc.bakery.mapper.page;

import com.juandavyc.bakery.dto.page.response.PageResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface PageMapper {

  default <D,E> PageResponse<D> toPageResponse(Page<E> page, Function<E, D> mapper) {

        return new PageResponse<>(
                page.getContent().stream().map(mapper).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty()
        );
    }

}
