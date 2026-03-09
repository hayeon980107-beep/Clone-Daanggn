package hy.banana.banana.board.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardUpdateRequest(
        @NotBlank(message = "제목은 비어 있을 수 없습니다.") String title,
        @NotBlank(message = "내용은 비어 있을 수 없습니다.") String content,
        @Min(value = 0, message = "가격은 0 이상이어야 합니다.") int price,
        @NotNull(message = "카테고리는 필수입니다.") Long categoryId
) {
}
