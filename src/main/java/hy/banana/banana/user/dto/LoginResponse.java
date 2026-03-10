package hy.banana.banana.user.dto;

public record LoginResponse(
        Long userId,
        String nickName,
        String email,
        String accessToken
) {
}
