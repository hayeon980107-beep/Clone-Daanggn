package hy.banana.banana.board.dto;

// 목록 조회
public record BoardListItemResponse(
        Long boardId,
        String title,
        Integer price,
        String neighborhoodName,
        String userNickName,
        Integer viewCount,
        Long likeCount
) {}
