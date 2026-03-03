package hy.banana.banana.board;

import hy.banana.banana.board.dto.BoardCreateRequest;
import hy.banana.banana.board.dto.BoardCreateResponse;
import hy.banana.banana.category.Category;
import hy.banana.banana.category.CategoryRepository;
import hy.banana.banana.neighborhood.Neighborhood;
import hy.banana.banana.neighborhood.NeighborhoodRepository;
import hy.banana.banana.user.User;
import hy.banana.banana.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MarketBoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    public BoardCreateResponse create(Long userId, BoardCreateRequest req) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        Category category = categoryRepository.findById(req.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리 없음"));

        Neighborhood neighborhood = neighborhoodRepository.findById(req.neiId())
                .orElseThrow(() -> new IllegalArgumentException("동네 없음"));

        MarketBoard board = MarketBoard.create(user, category, neighborhood,
                req.title(), req.content(), req.price());

        MarketBoard saved = boardRepository.save(board);
        return new BoardCreateResponse(saved.getBoardId());
    }
}
