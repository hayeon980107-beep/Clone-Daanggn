package hy.banana.banana.board.dto;

import hy.banana.banana.category.CategoryType;

public record BoardListRequest(
        Long neiId,
        Long categoryId,
        Integer page,
        Integer size
) {
    public int getPage() {
        return page == null ? 0 : page;
    }

    public int getSize() {
        return size == null ? 20 : size;
    }
}

