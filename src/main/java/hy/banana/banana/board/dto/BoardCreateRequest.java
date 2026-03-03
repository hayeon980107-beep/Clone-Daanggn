package hy.banana.banana.board.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardCreateRequest(
        @NotBlank String title,
        @NotBlank String content,
        @Min(0) int price,
        @NotNull Long categoryId,
        @NotNull Long neiId
) {}
