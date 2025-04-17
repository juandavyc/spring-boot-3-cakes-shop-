package com.juandavyc.bakery.dto.page.response;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        PageInformationResponse pageInformation
    ) {
}
