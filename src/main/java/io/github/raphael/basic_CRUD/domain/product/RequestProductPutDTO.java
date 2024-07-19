package io.github.raphael.basic_CRUD.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductPutDTO(
        @NotBlank
        @NotNull
        String id,

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Integer price_in_cents
) {
}
