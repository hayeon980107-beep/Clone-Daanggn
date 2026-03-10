package hy.banana.banana.category;

import hy.banana.banana.board.BoardService;
import hy.banana.banana.board.dto.BoardCreateRequest;
import hy.banana.banana.board.dto.BoardCreateResponse;
import hy.banana.banana.board.dto.BoardGetOneResponse;
import hy.banana.banana.board.dto.BoardListResponse;
import hy.banana.banana.category.dto.CategoryItemResponse;
import hy.banana.banana.category.dto.CategoryListResponse;
import hy.banana.banana.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryItemResponse>> getCategory(
           @RequestParam CategoryType type
    ) {
        return ApiResponse.success(categoryService.getCategoryList(type));
    }
}
