package hy.banana.banana.board;

import hy.banana.banana.board.dto.*;
import hy.banana.banana.category.Category;
import hy.banana.banana.category.CategoryRepository;
import hy.banana.banana.category.CategoryType;
import hy.banana.banana.neighborhood.Neighborhood;
import hy.banana.banana.neighborhood.NeighborhoodRepository;
import hy.banana.banana.user.User;
import hy.banana.banana.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * history
 * 26.03.02 게시글 저장
 * 26.03.03 게시글 조회 - 단건, 다건, 페이징
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final MarketBoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final NeighborhoodRepository neighborhoodRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional(readOnly = false)
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

    public BoardGetOneResponse getBoard(Long boardId) {

        MarketBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        return new BoardGetOneResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getPrice(),
                board.getViewCount(),
                board.getUser().getNickName(),
                board.getCategory().getName(),
                board.getNeighborhood().getName()
        );
    }

    public BoardListResponse getBoards(BoardListRequest request) {
        PageRequest pageable = PageRequest.of(request.page(), request.size());

        Page<BoardListItemResponse> result = boardRepository.findList(request.neiId(), pageable, request.categoryId());

        return new BoardListResponse(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.hasNext()
        );
    }
}
