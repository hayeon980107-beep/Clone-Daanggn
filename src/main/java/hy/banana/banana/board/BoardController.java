package hy.banana.banana.board;

import hy.banana.banana.board.dto.*;
import hy.banana.banana.common.jwt.CustomUserDetails;
import hy.banana.banana.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ApiResponse<BoardCreateResponse> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody BoardCreateRequest request
    ) {
        return ApiResponse.success(boardService.create(userDetails.getUserId(), request));
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
    public ApiResponse<Void> updateBoard( @AuthenticationPrincipal CustomUserDetails userDetails,
                                          @PathVariable Long boardId,
                                          @Valid @RequestBody BoardUpdateRequest request) {
        boardService.updateBoard(userDetails.getUserId(), boardId, request);
        return ApiResponse.success(null);
    }

    @PatchMapping("/{boardId}/state")
    public ApiResponse<Void> updateState( @AuthenticationPrincipal CustomUserDetails userDetails,
                                          @PathVariable Long boardId,
                                          @Valid @RequestBody BoardUpdateStateRequest request) {
        boardService.changeState(userDetails.getUserId(), boardId, request);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<Void> deleteBoard(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long boardId) {
        boardService.deleteBoard(userDetails.getUserId(), boardId);
        return ApiResponse.success(null);
    }
}
