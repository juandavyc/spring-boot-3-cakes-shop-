package com.juandavyc.bakery.dto.page.response;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        int totalPages,
        long totalElements,
        boolean first,
        boolean last,
        boolean empty
    ) {
}
