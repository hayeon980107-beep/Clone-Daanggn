package hy.banana.banana.board;

import hy.banana.banana.board.dto.BoardCreateRequest;
import hy.banana.banana.board.dto.BoardCreateResponse;
import hy.banana.banana.board.dto.BoardGetOneResponse;
import hy.banana.banana.board.dto.BoardListResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public BoardCreateResponse create(
            @RequestHeader("X-USER-ID") Long userId,
            @Valid @RequestBody BoardCreateRequest request
    ) {
        return boardService.create(userId, request);
    }

    @GetMapping("/{boardId}")
    public BoardGetOneResponse getBoard(@PathVariable Long boardId) {
        return boardService.getBoard(boardId);
    }

    @GetMapping
    public BoardListResponse getBoards(
            @RequestParam Long neiId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return boardService.getBoards(neiId, page, size);
    }
}
