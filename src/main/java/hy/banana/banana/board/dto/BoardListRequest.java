package hy.banana.banana.board.dto;

import hy.banana.banana.category.CategoryType;

public record BoardListRequest(
        Long neiId,
        Long categoryId,
        Integer page,
        Integer size
) {
}

