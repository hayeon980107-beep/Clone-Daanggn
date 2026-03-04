package hy.banana.banana.board.dto;

// 게시글 단건 조회
public record BoardGetOneResponse(
        Long boardId,
        String title,
        String content,
        int price,
        int viewCount,

        String userNickName,
        String categoryName,
        String neighborhoodName
) {
}
