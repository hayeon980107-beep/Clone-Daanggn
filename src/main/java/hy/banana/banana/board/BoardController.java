package hy.banana.banana.board;

import hy.banana.banana.board.dto.*;
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
    public BoardListResponse getBoards(BoardListRequest request) {
        return boardService.getBoards(request);
    }

    @PatchMapping("/{boardId}")
    public void updateBoard(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long boardId, BoardUpdateRequest request) {
        boardService.updateBoard(userId, boardId, request);
    }

    @PatchMapping("/{boardId}/state")
    public void updateState(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long boardId, BoardUpdateStateRequest request) {
        boardService.changeState(userId, boardId, request);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long boardId) {
        boardService.deleteBoard(userId, boardId);
    }
}
