package hy.banana.banana.board;

import hy.banana.banana.board.dto.*;
import hy.banana.banana.common.response.ApiResponse;
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
    public ApiResponse<BoardCreateResponse> create(
            @RequestHeader("X-USER-ID") Long userId,
            @Valid @RequestBody BoardCreateRequest request
    ) {
        return ApiResponse.success(boardService.create(userId, request));
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardGetOneResponse> getBoard(@PathVariable Long boardId) {
        return ApiResponse.success(boardService.getBoard(boardId));
    }

    @GetMapping
    public ApiResponse<BoardListResponse> getBoards(BoardListRequest request) {
        return ApiResponse.success(boardService.getBoards(request));
    }

    @PatchMapping("/{boardId}")
    public ApiResponse<Void> updateBoard(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long boardId, BoardUpdateRequest request) {
        boardService.updateBoard(userId, boardId, request);
        return ApiResponse.success(null);
    }

    @PatchMapping("/{boardId}/state")
    public ApiResponse<Void> updateState(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long boardId, BoardUpdateStateRequest request) {
        boardService.changeState(userId, boardId, request);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<Void> deleteBoard(@RequestHeader("X-USER-ID") Long userId, @PathVariable Long boardId) {
        boardService.deleteBoard(userId, boardId);
        return ApiResponse.success(null);
    }
}
