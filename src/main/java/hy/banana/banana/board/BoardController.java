package hy.banana.banana.board;

import hy.banana.banana.board.dto.BoardCreateRequest;
import hy.banana.banana.board.dto.BoardCreateResponse;
import jakarta.validation.Valid;
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
}
