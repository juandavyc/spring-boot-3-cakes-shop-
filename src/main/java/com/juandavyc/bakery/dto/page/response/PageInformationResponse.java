package com.juandavyc.bakery.dto.page.response;

public record PageInformationResponse(
        int page,
        int size,
        int totalPages,
        long totalElements,
        boolean first,
        boolean last,
        boolean empty
) {
}
