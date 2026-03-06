package hy.banana.banana.category.dto;

import hy.banana.banana.category.Category;

import java.util.List;

public record CategoryListResponse(
        List<CategoryItemResponse> categoryList
) {
}
