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

import java.time.LocalDateTime;
import java.util.Date;

/**
 * history
 * 26.03.02 게시글 저장 구현
 * 26.03.03 게시글 조회 - 단건, 다건, 페이징 구현
 * 26.03.07 게시글 수정, 삭제, 상태변경 구현
 * 26.03.07 게시글 조회주 증가 추가
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
                req.title(), req.content(), req.price(), LocalDateTime.now());

        MarketBoard saved = boardRepository.save(board);
        return new BoardCreateResponse(saved.getBoardId());
    }

    // 게시글 단건 조회
    public BoardGetOneResponse getBoard(Long boardId) {
        MarketBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        boardRepository.increaseViewCount(boardId);

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
        PageRequest pageable = PageRequest.of(request.getPage(), request.getSize());

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

    @Transactional
    public void updateBoard(Long userId, Long boardId, BoardUpdateRequest request) {
        MarketBoard board = boardRepository.findById(boardId).orElseThrow();

        if(!board.getUser().getUserId().equals(userId)){
            throw new IllegalStateException("작성자만 수정 가능");
        }

        Category category = categoryRepository.findById(request.categoryId()).orElseThrow();

        board.update(
                request.title(),
                request.content(),
                request.price(),
                category
        );
    }

    @Transactional
    public void changeState(Long userId, Long boardId, BoardUpdateStateRequest request)
    {
        MarketBoard board = boardRepository.findById(boardId).orElseThrow();

        if(!board.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("작성자만 수정 가능");
        }

        /**
         * 도메인 로직이 엔티티에 있음
         * 서비스가 깔끔
         */
        switch (request.state()) {
            case SELLING -> board.markSelling();
            case RESERVED -> board.markReserved();
            case SOLD -> board.markSold();
        }
    }

    @Transactional
    public void deleteBoard(Long userId, Long boardId) {
        MarketBoard board = boardRepository.findById(boardId).orElseThrow();

        if(!board.getUser().getUserId().equals(userId)) {
            throw new IllegalStateException("작성자만 삭제 가능");
        }

        boardRepository.delete(board);
    }
}
