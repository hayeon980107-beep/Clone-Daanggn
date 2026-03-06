package hy.banana.banana.category;

import hy.banana.banana.board.MarketBoardRepository;
import hy.banana.banana.board.PostLikeRepository;
import hy.banana.banana.board.dto.BoardListItemResponse;
import hy.banana.banana.board.dto.BoardListResponse;
import hy.banana.banana.category.dto.CategoryItemResponse;
import hy.banana.banana.category.dto.CategoryListResponse;
import hy.banana.banana.neighborhood.NeighborhoodRepository;
import hy.banana.banana.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * history
 * 26.03.04 카테고리 목록 조회
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final MarketBoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final PostLikeRepository postLikeRepository;

    public List<CategoryItemResponse> getCategoryList(CategoryType categoryType) {
        System.out.println(categoryRepository.findByType(categoryType));
        return categoryRepository.findByType(categoryType)
                .stream()
                .map(c -> new CategoryItemResponse(
                        c.getCategoryId(),
                        c.getName()
                ))
                .toList();
    }
}
