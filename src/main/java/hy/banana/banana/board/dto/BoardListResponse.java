package hy.banana.banana.board.dto;

import java.util.List;

// 게시글 목록 조회 return값
public record BoardListResponse(
        List<BoardListItemResponse> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
)  {
}
